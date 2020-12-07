package Services;

import DAO.AuthDAO;
import DAO.UserDAO;
import junit.framework.TestCase;
import model.Response.LoginResponse;
import model.request.LoginRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest extends TestCase {

    public void testLogin() {

        UserDAO mock = mock(UserDAO.class);


        LoginRequest in = new LoginRequest();
        in.setPassword("1234");
        in.setUserName("12345");

        LoginResponse resp = new LoginResponse(null,"1234",true);


        when(mock.login(in)).thenReturn(resp);

        LoginService toTest = new LoginService(mock, new AuthDAO());

        assertEquals(resp,toTest.login(in));
    }
}