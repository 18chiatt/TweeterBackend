package Services;

import DAO.AuthDAO;
import DAO.UserDAO;
import junit.framework.TestCase;
import model.Response.UserStatsResponse;
import model.domain.User;
import model.request.UserStatsRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserStatsServiceTest extends TestCase {

    public void testGetUserStats() {
        UserDAO mock = mock(UserDAO.class);
        AuthDAO mockAuth = mock(AuthDAO.class);

        UserStatsRequest in = new UserStatsRequest();
        in.setAuthToken("1234");
        User whoAksed = new User(null,null,"Chase",null);
        in.setWhoAsked(null);
        UserStatsResponse out = new UserStatsResponse(35,43);
        when(mock.getUserStats(in)).thenReturn(out);

        UserStatsService serv = new UserStatsService(mock,mockAuth);
        assertEquals(out,serv.getUserStats(in));
    }
}