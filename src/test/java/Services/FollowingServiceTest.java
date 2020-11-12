package Services;

import DAO.FollowingDAO;
import junit.framework.TestCase;
import model.Response.FollowingResponse;
import model.request.FollowingRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingServiceTest extends TestCase {

    public void testGetFollowing() {

        FollowingDAO mock = mock(FollowingDAO.class);

        FollowingRequest in = new FollowingRequest();
        in.setLimit(69);
        FollowingResponse resp = new FollowingResponse(null,true);


        FollowingService theService = new FollowingService(mock);

        when(mock.getFollowing(in)).thenReturn(resp);

        assertEquals(resp,theService.getFollowing(in));

    }
}