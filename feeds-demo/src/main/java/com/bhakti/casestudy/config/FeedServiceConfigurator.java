package com.bhakti.casestudy.config;

import com.bhakti.casestudy.healthcheck.TemplateHealthCheck;
import com.bhakti.casestudy.impl.FeedsServiceImpl;
import com.bhakti.casestudy.impl.UserServiceImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;


/**
 * This is the class which will be used for the DropWizard services
 *
 * @author Bhakti Mehta
 */
public class FeedServiceConfigurator extends Service<ServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new FeedServiceConfigurator().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.setName("feed");
    }

    @Override
    public void run(ServiceConfiguration configuration,
                    Environment environment) {
        final String template = configuration.getTemplate();

        Injector injector = createInjector(configuration);
        environment.addResource(injector.getInstance(FeedsServiceImpl.class));
        environment.addResource(injector.getInstance(UserServiceImpl.class));

        environment.addHealthCheck(new TemplateHealthCheck(template));
    }

    private Injector createInjector(final ServiceConfiguration conf) {
        return Guice.createInjector( new ServiceModule());
    }

}
