package Services;

import DAO.AuthDAO;
import DAO.FollowingDAO;
import junit.framework.TestCase;
import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowManipulationServiceTest extends TestCase {

    public void testManipulateFollows() {
        AuthDAO mockAuth = mock(AuthDAO.class);
        FollowingDAO mockFollowing = mock(FollowingDAO.class);

        FollowManipulationRequest in = new FollowManipulationRequest();
        in.setAddFollow(true);
        in.setAuthToken("1234");
        FollowManipulationResult out = new FollowManipulationResult(true,true);

        when(mockFollowing.manipulateFollows(in)).thenReturn(out);
        when(mockAuth.isAuthorized(null,"1234")).thenReturn(true);

        FollowManipulationService serv = new FollowManipulationService(mockAuth,mockFollowing);




        assertEquals(out,serv.manipulateFollows(in));

    }
}