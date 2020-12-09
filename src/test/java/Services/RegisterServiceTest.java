package Services;

import DAO.AuthDAO;
import DAO.ImageDAO;
import DAO.UserDAO;
import junit.framework.TestCase;
import model.Response.RegisterResponse;
import model.request.RegisterRequest;

import java.util.Base64;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterServiceTest extends TestCase {

    public void testRegister() {

        UserDAO mock = mock(UserDAO.class);
        ImageDAO mockImage = mock(ImageDAO.class);
        AuthDAO authMock = mock(AuthDAO.class);

        RegisterRequest in =  new RegisterRequest();
        in.setFirstName("Chase");
        in.setUsername("username");
        in.setImage("ABCD");
        byte[] input = Base64.getDecoder().decode("ABCD");

        RegisterResponse out = new RegisterResponse(true);

        when(mock.register(in,null)).thenReturn(out);
        when(mock.isFree("username")).thenReturn(true);
        when(mockImage.uploadImage(input,"Chase")).thenReturn("abcd");
        //when(authMock.register(in)).thenReturn(new RegisterResponse(true));



        RegisterService serv = new RegisterService(mock,mockImage,authMock);
        RegisterResponse serverResponse = serv.register(in);

        assertEquals(out,serverResponse);



    }
}