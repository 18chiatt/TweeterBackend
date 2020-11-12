package Handlers;

import DAO.FeedDAO;
import Services.FeedService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.Response.FeedResponse;
import model.domain.Status;
import model.domain.User;
import model.request.FeedRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FeedHandler implements RequestHandler<FeedRequest, FeedResponse> {

    @Override
    public FeedResponse handleRequest(FeedRequest feedRequest, Context context) {

        return new FeedService(new FeedDAO()).getFeed(feedRequest);
    }
}
