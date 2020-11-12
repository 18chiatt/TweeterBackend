package DAO.Fake;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ServerFakeFactory {
    private static final String sourceBucket = "chasehiattbucket";
    private static final String sourceKEY = "fakeJSON.txt";
    private static ServerFake instance;


    public static ServerFake getInstance(){
        if(instance == null) {
            AmazonS3 client = AmazonS3ClientBuilder.defaultClient();
            System.out.println(client.getRegionName());
            System.out.println(client.getS3AccountOwner().getDisplayName());
            System.out.println(client.toString());
            S3Object constFile = client.getObject(new GetObjectRequest(sourceBucket, sourceKEY));
            System.out.println(constFile.getObjectMetadata().getContentLength());
            System.out.println(constFile.toString());
            InputStream data = constFile.getObjectContent();
            System.out.println(data.toString());

            Reader theReader = new InputStreamReader(data);
            System.out.println("Got file hopefully!");
            instance = new GsonBuilder().create().fromJson(theReader, ServerFake.class);
        }

        return instance;

    }





}
