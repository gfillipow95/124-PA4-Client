import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

public class productPage extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String id = request.getParameter("id");

        String jsonResponse =
                target.path("v1").path("api").path("products").path(id).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string

        System.out.println(jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        Product product = objectMapper.readValue(jsonResponse, new TypeReference<Product>(){});//Only return one product

        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>\n"+
                "<html lang=\"en\">\n"+
                "<head>\n"+
                "<meta charset=\"UTF-8\">\n"+
                "<link rel='stylesheet' href='stylesheets/productPage.css' />\n"+
                "<link href=\"https://fonts.googleapis.com/css?family=Lusitana\" rel=\"stylesheet\">\n");


        String name = product.getProductName();
        Integer pid = product.getId();
        String pic = product.getPic();
        String description = product.getDescription();
        Double price = product.getPrice();

        out.print("<title>"+name+"</title>\n");

        out.print("</head>\n"+
                "<body>\n"+
                "<div class=\"nav-bar-container\">\n"+
                "<ul class=\"nav-bar\">\n"+
                "<li><a id=\"navbar-home\" href=\"/RESTClient\">Back to Home</a></li>\n"+
                "</ul>\n"+
                "</div>\n"+
                "<div class=\"product-page-container\">\n"+
                "<div class=\"product-info-container\">\n"+
                "<div class='product-image-container'>\n"+
                "<img class='product-page-image' onmousemove='zoomIn()' onmouseout='zoomOut()' src='"+pic+"'/>\n"+
                "</div>\n"+
                "<div class='product-description-container'>\n"+
                "<h3>"+description+"</h3>\n"+
                "<h3>"+name+"</h3>\n"+
                "<h4 id='price' data-price='"+price.toString()+"'>$"+price.toString()+"</h4>\n"+
                "</div>\n"+
                "<a href='addToCart?id="+pid.toString()+"'>Add to Cart</a>\n"+ //add cart functionality here
                "</div>\n"+
                "</body>\n"+
                "<script src=\"javascript/detailedPage.js\"></script>\n"+
                "<script src=\"javascript/orderForm.js\"></script>\n"+
                "</html>");


            HttpSession session = request.getSession();
            LinkedList<String> recentlyVisited = new LinkedList<String>();
            recentlyVisited = (LinkedList<String>)session.getAttribute("recentProducts");

            if(!recentlyVisited.contains(id)){
                if(recentlyVisited.size() >= 5){
                    recentlyVisited.removeLast();
                }
                recentlyVisited.addFirst(id);
                session.setAttribute("recentProducts", recentlyVisited);
            }

    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://andromeda-3.ics.uci.edu:5130/jerseyrest").build();
    }


}