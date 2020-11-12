package Services;

import DAO.StoryDAO;
import junit.framework.TestCase;
import model.Response.StoryResponse;
import model.request.StoryRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StoryServiceTest extends TestCase {

    public void testGetStory() {
        StoryDAO mock = mock(StoryDAO.class);

        StoryRequest in = new StoryRequest();
        in.setMaxToGet(55);


        StoryResponse out = new StoryResponse(null,true);

        when(mock.getStory(in)).thenReturn(out);
        StoryService toTest = new StoryService(mock);

        assertEquals(out,toTest.getStory(in));
    }
}