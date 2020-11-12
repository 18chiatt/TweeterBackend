package Services;

import DAO.AuthDAO;
import DAO.StatusDAO;
import junit.framework.TestCase;
import model.Response.PostStatusResponse;
import model.domain.Status;
import model.request.PostStatusRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostStatusServiceTest extends TestCase {

    public void testPostStatus() {
        AuthDAO mock = mock(AuthDAO.class);
        StatusDAO mockStatus = mock(StatusDAO.class);

        PostStatusRequest in = new PostStatusRequest();
        in.setAuthToken("1234");
        Status theStatus = new Status();

        in.setTheStatus(theStatus);

        PostStatusResponse out = new PostStatusResponse(true);

        when(mockStatus.postStatus(in)).thenReturn(out);
        when(mock.isAuthorized(null,"1234")).thenReturn(true);

        PostStatusService serv = new PostStatusService(mock,mockStatus);
        assertEquals(out,serv.postStatus(in));

    }
}