package org.coffeeshop;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is a rate limiting filter
 * This checks for the IPAddress of the client
 * Gets the request count
 * Checks if the request has exceeded the limit imposed
 * @author Bhakti Mehta
 */
@WebFilter(filterName = "RateLimiter",
        urlPatterns = {"/*"}
)
public class RateLimiter implements Filter {

    private static final int REQ_LIMIT = 3;
    private static final int TIME_LIMIT = 600000;
    private static AccessCounter accessCounter = AccessCounter.getInstance();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //accessCounter = AccessCounter.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String ipAddress = getIpAddress(httpServletRequest);
        if (accessCounter.contains(ipAddress)) {
            if (!requestLimitExceeded(ipAddress)) {
                accessCounter.increment(ipAddress);
                accessCounter.getAccessDetails(ipAddress).setLastUpdated(System.currentTimeMillis());

            } else {

                httpServletResponse.addIntHeader("Retry-After",TIME_LIMIT);
                httpServletResponse.sendError(429);

            }
        } else {
            accessCounter.add(ipAddress);

        }
        filterChain.doFilter(servletRequest, servletResponse);


    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * This method checks if the request limit has exceeded the max number of
     * requests a client can make
     * @param ipAddress
     * @return true if the number of requests has exceeded the LIMIT
     *         false if the number requests has not exceeded the limit
     */
    private boolean requestLimitExceeded(String ipAddress) {

        AccessData data = accessCounter.getAccessDetails(ipAddress);

       // int count = data.getCount() + 1;
        int count = data.incrementCount();
        if (count > REQ_LIMIT) {
            // Check the elapsed time else reset the access count
            if (System.currentTimeMillis() - data.getLastUpdated() > TIME_LIMIT) {
                System.out.println("Time between access" + (System.currentTimeMillis() - data.getLastUpdated()));
                data.setCount(new AtomicInteger(0));
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }


    }

    @Override
    public void destroy() {

    }




}
