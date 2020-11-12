package Services;

import DAO.FeedDAO;
import junit.framework.TestCase;
import model.Response.FeedResponse;
import model.request.FeedRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeedServiceTest extends TestCase {

    public void testGetFeed() {
        FeedDAO mock = mock(FeedDAO.class);
        FeedRequest in = new FeedRequest();
        FeedResponse out = new FeedResponse();
        out.setHasMore(false);
        when(mock.getFeed(in)).thenReturn(out);
        FeedService toUse = new FeedService(mock);
        assert(out.equals(toUse.getFeed(in)));

    }
}