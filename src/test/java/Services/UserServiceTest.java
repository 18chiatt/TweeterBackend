package Services;

import DAO.UserDAO;
import DAO.UserStatsDAO;
import junit.framework.TestCase;
import model.Response.UserResponse;
import model.request.UserRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest extends TestCase {

    public void testGetUser() {
        UserDAO mock = mock(UserDAO.class);
        UserRequest in = new UserRequest();
        in.setAlias("username");

        UserResponse out = new UserResponse(null,true);

        when(mock.getUser(in)).thenReturn(out);
        UserService test = new UserService(mock);

        assertEquals(out,test.getUser(in));
    }
}