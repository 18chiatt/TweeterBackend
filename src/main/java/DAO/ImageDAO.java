package DAO;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ImageDAO {
    private final AmazonS3 client;
    public ImageDAO(){
        this.client =  AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

    }

    public String uploadImage(byte[] bytes, String username){

        if(bytes == null){
            System.out.println("bytes are null!");
            return null;
        }

        if(username == null){
            System.out.println("Username is null!");
            return null;
        }
        InputStream stream = new ByteArrayInputStream(bytes);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(bytes.length);
        meta.setContentType("image/png");
        String objectName = username+".png";

        PutObjectResult res = client.putObject(new PutObjectRequest(
                "chasehiattbucket/images", objectName, stream, meta)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        System.out.println("Hopefully stored image");

        String imageURL = "https://s3.amazonaws.com/chasehiattbucket/images/" + username + ".png";
        System.out.println(imageURL + "Is the location of image, Hopefully!");



        return imageURL;
    }
}
