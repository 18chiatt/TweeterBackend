package DAO;

import junit.framework.TestCase;
import model.Response.LoginResponse;
import model.request.LoginRequest;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTest extends TestCase {
    UserDAO toTest = new UserDAO();



    @Test
    public void testGetUser() {

         assert(toTest.isFree("asdfasdfasdf"));
         //assert(!toTest.isFree("18chiatt"));


    }

    public void testLogin() {
        LoginRequest req = new LoginRequest();
        req.setUserName("18chiatt");
        LoginResponse resp =  toTest.login(req);
        //assert(resp.getLoggedInAs().getUserName().equals("18chiatt"));
        //assert(resp.isSuccess());
        req.setUserName("asdfasdfasdfasdf");
        resp = toTest.login(req);
        //assert(!resp.isSuccess());



    }

    public void testRegister() {
    }

    public void testInitializeDB() {
    }

    public void testIsFree() {
    }
}