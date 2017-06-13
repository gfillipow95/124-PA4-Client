import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;

public class productTables extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());


        String jsonResponse =
                target.path("v1").path("api").path("products").
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string

        System.out.println(jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        List<Product> productList = objectMapper.readValue(jsonResponse, new TypeReference<List<Product>>(){});

        PrintWriter out = response.getWriter();

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/session");
//        dispatcher.include(request, response);
        int i = 1;
        for(Product product : productList){
            if(product.getProductId() == 1){
                if(i == 1){
                    out.print("<div id='coffee' class='coffee section'>\n"+
                            "<div class='section-title'>\n"+
                            "<h2>Coffee</h2>\n"+
                            "</div>\n"+
                            "<table class='product-table'>\n"+
                            "<tr>\n");
                    i++;
                }
                String coffeeName = product.getProductName();
                Integer coffeeId = product.getId();
                String coffeePic = product.getPic();
                String coffeeDescription = product.getDescription();
                Double coffeePrice = product.getPrice();

                out.print("<td class='product-description'>\n"+
                        "<a href='productPage?id="+coffeeId.toString()+"'>\n"+
                        "<div class='image-container'>\n"+
                        "<img class='product-image' src='"+coffeePic+"'/>\n"+
                        "</div>\n"+
                        "<div class='desc-container'>\n"+
                        "<h3>"+coffeeDescription+"</h3>\n"+
                        "<h3>"+coffeeName+"</h3>\n"+
                        "<h4>" + coffeePrice.toString() + "</h4>\n"+
                        "</div>\n"+
                        "</a>\n"+
                        "</td>\n");
            }

            if(product.getProductId() == 2){
                if(i == 2){
                    out.print("</tr>\n"+
                            "</table>\n"+
                            "</div>\n");
                    out.print("<div id='mugs' class='mugs section'>\n"+
                            "<div class='section-title'>\n"+
                            "<h2>Mugs</h2>\n"+
                            "</div>\n"+
                            "<table class='product-table'>\n"+
                            "<tr>\n");
                    i++;
                }
                String mugName = product.getProductName();
                Integer mugId = product.getId();
                String mugPic = product.getPic();
                String mugDescription = product.getDescription();
                Double mugPrice = product.getPrice();

                out.print("<td class='product-description'>\n"+
                        "<a href='productPage?id="+mugId.toString()+"'>\n"+
                        "<div class='image-container'>\n"+
                        "<img class='product-image' src='"+mugPic+"'/>\n"+
                        "</div>\n"+
                        "<div class='desc-container'>\n"+
                        "<h3>"+mugDescription+"</h3>\n"+
                        "<h3>"+mugName+"</h3>\n"+
                        "<h4>" + mugPrice.toString() + "</h4>\n"+
                        "</div>\n"+
                        "</a>\n"+
                        "</td>\n");
            }

            if(product.getProductId() == 3){
                if(i == 3){
                    out.print("</tr>\n"+
                            "</table>\n"+
                            "</div>\n");
                    out.print("<div id='makers' class='makers section'>\n"+
                            "<div class='section-title'>\n"+
                            "<h2>Coffee Makers</h2>\n"+
                            "</div>\n"+
                            "<table class='product-table'>\n"+
                            "<tr>\n");
                    i++;
                }
                String makerName = product.getProductName();
                Integer makerId = product.getId();
                String makerPic = product.getPic();
                String makerDescription = product.getDescription();
                Double makerPrice = product.getPrice();

                out.print("<td class='product-description'>\n"+
                        "<a href='productPage?id="+makerId.toString()+"'>\n"+
                        "<div class='image-container'>\n"+
                        "<img class='product-image' src='"+makerPic+"'/>\n"+
                        "</div>\n"+
                        "<div class='desc-container'>\n"+
                        "<h3>"+makerDescription+"</h3>\n"+
                        "<h3>"+makerName+"</h3>\n"+
                        "<h4>" + makerPrice.toString() + "</h4>\n"+
                        "</div>\n"+
                        "</a>\n"+
                        "</td>\n");
            }
        }
        out.print("</tr>\n"+
                "</table>\n"+
                "</div>\n");

    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://andromeda-3.ics.uci.edu:5130/jerseyrest").build();
    }
}