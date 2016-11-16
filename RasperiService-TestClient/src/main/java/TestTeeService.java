import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class TestTeeService {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        client.target(UriBuilder.fromUri("http://localhost:8888/PiService/").build());
        WebTarget target = client.target(getBaseURI());


        //TestTeeService.testGetMsg(target);





    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8888/PiService/").build();
    }




    /**
     * Rest Call to http://localhost:8888/PiService/rest/hello/getWeather/London
     */
    public void testGetWeather(){

    }

    public void testGetTemprature(){

    }

    public void testChangeHomeLocation(){

    }

    public void testMakeTee(){

    }


}
