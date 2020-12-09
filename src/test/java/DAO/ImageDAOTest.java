package DAO;

import Generators.SaltMines;
import Generators.UserGenerator;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDAOTest {

    @Test
    public void uploadImage() {
        String pretendImage = SaltMines.getSalt(300);
        byte[] pretendImageBytes = pretendImage.getBytes();
        String fakeUserName = UserGenerator.getUser().getUserName();

        ImageDAO toTest = new ImageDAO();

        String url = toTest.uploadImage(pretendImageBytes,fakeUserName);
        StringBuilder builder = new StringBuilder();
        try {
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                builder.append(inputLine);
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(builder.toString().equals(pretendImage));


    }
}