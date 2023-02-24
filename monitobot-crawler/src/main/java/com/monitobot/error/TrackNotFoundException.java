package com.monitobot.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
public class TrackNotFoundException extends WebApplicationException {

    public TrackNotFoundException(String trackPublicId) {
        super("Track with given ID not found: " + trackPublicId, Response.Status.NOT_FOUND);
    }
}
