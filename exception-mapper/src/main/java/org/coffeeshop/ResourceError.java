package org.coffeeshop;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is a user defined class which will contain
 * the status code and response message when an error occurs
 * @author Bhakti Mehta
 */
@XmlRootElement
public class ResourceError {

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResourceError(){

    }
}
