package Services;

import DAO.AuthDAO;
import DAO.BeingFollowedDAO;
import DAO.FollowingDAO;
import DAO.ManipulationDAO;
import junit.framework.TestCase;
import model.Response.FollowManipulationResult;
import model.request.FollowManipulationRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowManipulationServiceTest extends TestCase {

    public void testManipulateFollows() {
        AuthDAO mockAuth = mock(AuthDAO.class);
        ManipulationDAO dao = mock(ManipulationDAO.class);

        FollowManipulationRequest in = new FollowManipulationRequest();
        in.setAddFollow(true);
        in.setAuthToken("1234");
        FollowManipulationResult out = new FollowManipulationResult(true,true);


        when(mockAuth.isAuthorized(null,"1234")).thenReturn(true);
        when(dao.manipulate(in)).thenReturn(out);

        FollowManipulationService serv = new FollowManipulationService(mockAuth,dao);




        assertEquals(out,serv.manipulateFollows(in));

    }
}