package Generators;

import DAO.ManipulationDAO;
import DAO.UserDAO;
import model.domain.User;
import model.request.FollowManipulationRequest;
import model.request.RegisterRequest;

import java.util.ArrayList;
import java.util.List;

public class Popularizer {

    public static void main(String[] args){

        User toPopularize = new User("Chase","Hiatt","18chiatt","https://s3.amazonaws.com/chasehiattbucket/images/18chiatt.png");

        int numPeopleToAdd = 1000;
        String theirProfilePic = "https://s3.amazonaws.com/chasehiattbucket/images/bill.png";
        ManipulationDAO manipDao = new ManipulationDAO();
        UserDAO uDao = new UserDAO();



        for(int i=0; i< numPeopleToAdd; i++){
            User singleToRegister = UserGenerator.getUser();
            RegisterRequest toRegister = new RegisterRequest(singleToRegister.getUserName(),"password",singleToRegister.getFirstName(),singleToRegister.getLastName(),"LOL");





            uDao.register(toRegister,theirProfilePic);

            FollowManipulationRequest manip = new FollowManipulationRequest(singleToRegister,toPopularize,true,"LMAO");
            manipDao.manipulate(manip);

            System.out.println(singleToRegister.toString() +" is now following " + toPopularize.toString());


        }


    }
}
