package Handlers;

import DAO.BeingFollowedDAO;
import DAO.util.ConnectionHolder;
import DAO.FeedDAO;
import Services.AddToStatusService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.google.gson.Gson;
import model.request.SQSIncoming;

public class SQSHandler implements RequestHandler<SQSEvent,Void> {

    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        Gson gson = ConnectionHolder.getGson();
        AddToStatusService service = new AddToStatusService(new BeingFollowedDAO(),new FeedDAO());

        for(SQSEvent.SQSMessage message : sqsEvent.getRecords()){
             String body = message.getBody();
             System.out.println(body);
             SQSIncoming incoming = gson.fromJson(body,SQSIncoming.class);
             service.handleIncoming(incoming);



        }
        return null;
    }

}

