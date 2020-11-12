package Services;

import DAO.FollowingDAO;
import junit.framework.TestCase;
import model.Response.FollowingStatusResponse;
import model.domain.User;
import model.request.FollowingStatusRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingStatusServiceTest extends TestCase {

    public void testGetFollowingStatus() {

        FollowingDAO mock = mock(FollowingDAO.class);
        FollowingStatusRequest in = new FollowingStatusRequest();
        in.setPersonWhoFollowsMaybe(new User("chase","hiatt","username","google.com"));

        FollowingStatusResponse resp = new FollowingStatusResponse(true);

        when(mock.getFollowingStatus(in)).thenReturn(resp);


        FollowingStatusService serv = new FollowingStatusService(mock);

        assertEquals(resp,serv.getFollowingStatus(in));
    }
}