import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by marc on 16.11.16.
 */
public class TestTestService {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        client.target(UriBuilder.fromUri("http://localhost:8888/PiService/").build());
        WebTarget target = client.target(getBaseURI());


        TestTestService.testGetMsg(target);





    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8888/PiService/").build();
    }

    /**
     * Rest Call to http://localhost:8888/PiService/rest/test/msg/hallo
     */
    public static void testGetMsg(WebTarget target){
        String response = target.path("rest/test/").
                path("msg/Hallo").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(Response.class)
                .toString();
        System.out.println(response);

    }


}
