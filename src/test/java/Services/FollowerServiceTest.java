package Services;

import DAO.BeingFollowedDAO;
import DAO.FollowingDAO;
import junit.framework.TestCase;
import model.Response.FollowerResponse;
import model.request.FollowerRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowerServiceTest extends TestCase {

    public void testGetFollower() {

        BeingFollowedDAO mock = mock(BeingFollowedDAO.class);
        FollowerRequest in = new FollowerRequest();
        in.setMaxToGet(65);

        FollowerResponse resp = new FollowerResponse(null,true);

        when(mock.getFollower(in)).thenReturn(resp);
        FollowerService service = new FollowerService(mock);

        assertEquals(resp,service.getFollower(in));

    }
}