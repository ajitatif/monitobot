package com.monitobot.error;

import io.netty.handler.codec.http.HttpResponseStatus;

import javax.ws.rs.WebApplicationException;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
public class DownstreamServiceException extends WebApplicationException {

    private static final int statusCode = HttpResponseStatus.SERVICE_UNAVAILABLE.code();

    public DownstreamServiceException(String message, Throwable cause) {
        super(message, cause, statusCode);
    }

    public DownstreamServiceException(String message) {
        super(message, statusCode);
    }
}
