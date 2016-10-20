import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class Test {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String response = target.path("rest").
                path("hello/test").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(Response.class)
                .toString();


        String plainAnswer =
                target.path("rest").path("hello/test").request().accept(MediaType.APPLICATION_JSON).get(String.class);

        System.out.println(response);
        System.out.println(plainAnswer);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8888/PiService/").build();
    }
}
