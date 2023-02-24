package com.monitobot.search;

import com.monitobot.configuration.MonitobotCrawlerConfiguration;
import com.monitobot.search.google.GoogleSearchService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@gmail.com)
 */
@Path("/v1/search")
public class SearchResource {

    MonitobotCrawlerConfiguration configuration;
    GoogleSearchService googleSearchService;

    TrackService trackService;

    public SearchResource(MonitobotCrawlerConfiguration configuration, GoogleSearchService googleSearchService, TrackService trackService) {
        this.configuration = configuration;
        this.googleSearchService = googleSearchService;
        this.trackService = trackService;
    }

    @POST
    @Path("/google/test")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public InternetSearchResult testGoogleSearch(SearchCriteria searchCriteria) {
        return googleSearchService.search(searchCriteria);
    }

    @GET
    @Path("/tracks/{track-public-id}/new-results")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TrackResult getNewResults(@PathParam("track-public-id") String trackPublicId) {
        return trackService.getNewResults(trackPublicId);
    }

}
