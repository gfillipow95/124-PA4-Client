/**
 * Created by Gen on 6/12/2017.
 */

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;


public class Session extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        LinkedList<String> recentlyVisited = new LinkedList<String>();

        PrintWriter out = response.getWriter();

        if(session.isNew()){
            session.setAttribute("recentProducts", recentlyVisited);
        }else{
            recentlyVisited = (LinkedList<String>)session.getAttribute("recentProducts");

            if(recentlyVisited.size() >= 1){
                out.print("<div id='recent' class='recent section'>\n"+
                        "<div class='section-title'>\n"+
                        "<h2>Recently Viewed</h2>\n"+
                        "</div>\n"+
                        "<table class='product-table'>\n"+
                        "<tr>\n");
                for(int i = 0; i < recentlyVisited.size(); i++){
                    String jsonResponse =
                            target.path("v1").path("api").path("products").path(recentlyVisited.get(i)).
                                    request(). //send a request
                                    accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                                    get(String.class); // use the get method and return the response as a string

                    ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

                    Product product = objectMapper.readValue(jsonResponse, new TypeReference<Product>(){});//Only return one product

                    String name = product.getProductName();
                    Integer pid = product.getId();
                    String pic = product.getPic();
                    String description = product.getDescription();
                    Double price = product.getPrice();

                    out.print("<td class='product-description'>\n"+
                            "<a href='productPage?id="+pid.toString()+"'>\n"+
                            "<div class='image-container'>\n"+
                            "<img class='recent-image product-image' src='"+pic+"'/>\n"+
                            "</div>\n"+
                            "<div class='desc-container'>\n"+
                            "<h3>"+description+"</h3>\n"+
                            "<h3>"+name+"</h3>\n"+
                            "<h4>" + price + "</h4>\n"+
                            "</div>\n"+
                            "</a>\n"+
                            "</td>\n");

                }
                out.print("</tr>\n"+
                        "</table>\n"+
                        "</div>\n");
            }

        }

    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://andromeda-3.ics.uci.edu:5130/jerseyrest").build();
    }

}
