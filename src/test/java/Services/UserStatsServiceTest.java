package Services;

import DAO.UserStatsDAO;
import junit.framework.TestCase;
import model.Response.UserStatsResponse;
import model.domain.User;
import model.request.UserStatsRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserStatsServiceTest extends TestCase {

    public void testGetUserStats() {
        UserStatsDAO mock = mock(UserStatsDAO.class);

        UserStatsRequest in = new UserStatsRequest();
        UserStatsResponse out = new UserStatsResponse(35,43);
        when(mock.getUserStats(in)).thenReturn(out);
        UserStatsService serv = new UserStatsService(mock);
        assertEquals(out,serv.getUserStats(in));
    }
}