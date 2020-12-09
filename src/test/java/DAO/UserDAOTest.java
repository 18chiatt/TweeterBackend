package DAO;

import Generators.UserGenerator;
import junit.framework.TestCase;
import model.Response.LoginResponse;
import model.Response.UserStatsResponse;
import model.domain.User;
import model.request.LoginRequest;
import model.request.RegisterRequest;
import model.request.UserRequest;
import model.request.UserStatsRequest;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTest extends TestCase {
    UserDAO toTest = new UserDAO();
    User testUser = new User("Test","User","Chase","https://s3.amazonaws.com/chasehiattbucket/images/Chase.png");
    User bill = new User("Bill","Science","bill","https://s3.amazonaws.com/chasehiattbucket/images/bill.png");
    User bimboJoe = new User("Joe","Bimbo","bimboJoe","https://s3.amazonaws.com/chasehiattbucket/images/bimboJoe.png");
    User followedUser = new User("Followed","User","Followed","https://s3.amazonaws.com/chasehiattbucket/images/Followed.png");
    String authToken;
    @Before
    public void getAuth(){

        authToken = new AuthDAO().getCreateAuthToken( new LoginRequest("Chase","password"));
    }

    @Test
    public void testGetUser() {
        UserRequest req = new UserRequest();
        req.setAlias("Chase");

        assertEquals(toTest.getUser(req).getToRespondWith(),testUser);
        req.setAlias("ASDFASDFASDF");
        assert(!toTest.getUser(req).isSuccess());



    }

    public void testLogin() {
        LoginRequest req = new LoginRequest();
        req.setUserName("Chase");
        LoginResponse resp =  toTest.login(req);
        //assert(resp.getLoggedInAs().getUserName().equals("18chiatt"));
        //assert(resp.isSuccess());
        req.setUserName("asdfasdfasdfasdf");
        resp = toTest.login(req);
        assert(!resp.isSuccess());



    }

    public void testRegister() {
        User toRegister = UserGenerator.getUser();
        RegisterRequest req = new RegisterRequest(toRegister.getUserName(),"password",toRegister.getFirstName(),toRegister.getLastName(),null);
        toTest.register(req,"https://chasehiattbucket.s3.amazonaws.com/images/bill.png");
        UserRequest userRequest = new UserRequest();
        userRequest.setAlias(toRegister.getUserName());
        assertEquals(toTest.getUser(userRequest).getToRespondWith(),toRegister);
    }



    public void testIsFree() {
        assert(toTest.isFree("asdfasdfasdf"));
        assert(!toTest.isFree("18chiatt"));
        assert(!toTest.isFree("Chase"));
    }

    public void testGetUserStats(){
        UserStatsRequest req = new UserStatsRequest();
        req.setWhoAsked(testUser);
        req.setToFindOf(testUser);
        req.setAuthToken(authToken);
        UserStatsResponse resp = toTest.getUserStats(req);
        assert(resp.getNumFollowers() == 2);
        assert(resp.getNumPeopleTheyAreFollowing() == 1);
    }


}