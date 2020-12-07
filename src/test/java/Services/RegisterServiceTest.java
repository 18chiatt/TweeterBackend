package Services;

import DAO.ImageDAO;
import DAO.UserDAO;
import junit.framework.TestCase;
import model.Response.RegisterResponse;
import model.request.RegisterRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterServiceTest extends TestCase {

    public void testRegister() {

        UserDAO mock = mock(UserDAO.class);
        RegisterRequest in =  new RegisterRequest();
        in.setFirstName("Chase");
        in.setUsername("username");

        RegisterResponse out = new RegisterResponse(true);

        when(mock.register(in,"")).thenReturn(out);
        RegisterService serv = new RegisterService(mock,new ImageDAO());

        //assertEquals(out,serv.register(in));  FIXME make this test pass



    }
}