/**
 * Created by Gen on 6/13/2017.
 */

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.Iterator;
import java.util.HashMap;
public class displayCart extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>\n"+
                "<html lang=\"en\">\n"+
                "<head>\n"+
                "<meta charset=\"UTF-8\">\n"+
                "<link rel='stylesheet' href='stylesheets/productPage.css' />\n"+
                "<link href=\"https://fonts.googleapis.com/css?family=Lusitana\" rel=\"stylesheet\">\n");

        HttpSession session = request.getSession(true);

        out.print("</head>\n"+
                "<body>\n"+
                "<div class=\"nav-bar-container\">\n"+
                "<ul class=\"nav-bar\">\n"+
                "<li><a id=\"navbar-home\" href=\"/RESTClient\">Back to Home</a></li>\n"+
                "</ul>\n"+
                "</div>\n"+
                "<div class='product-page-container'>\n" +
                "<div class=\"product-info-container\">\n" +
                "<table>\n"+
                "<tr>\n"+
                "<th>Product</th>\n"+
                "<th>Price</th>\n"+
                "<th>Quantity</th>\n"+
                "</tr>");

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());


        HashMap<Integer, Integer> cartInfo = (HashMap<Integer, Integer>)session.getAttribute("cart");

        Iterator it = cartInfo.entrySet().iterator();

        float total= 0;
        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)it.next();
            Integer productId = (Integer)pair.getKey();
            Integer productQuantity = (Integer)pair.getValue();

            String jsonResponse =
                    target.path("v1").path("api").path("products").path(productId.toString()).
                            request(). //send a request
                            accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                            get(String.class); // use the get method and return the response as a string

            System.out.println(jsonResponse);

            ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

            Product product = objectMapper.readValue(jsonResponse, new TypeReference<Product>(){});//Only return one product

            String name = product.getProductName();
            String pic = product.getPic();
            Double price = product.getPrice();

            total += price*productQuantity;
            out.print("<tr>\n"+
                    "<td><img style='max-width: 20%;' src='"+pic+"' />"+name+"</td>\n"+
                    "<td>"+productQuantity+"</td>\n"+
                    "<td>"+price+"</td>\n"+
                    "</tr>");

        }


        out.print("<tr>\n"+
                "<td colspan='2'>Total Price:</td>\n"+
                "<td>"+total+"</td>\n"+
                "</tr>\n"+
                "</table>\n"+
                "</div>\n"+
                "<div class=\"form-container\">\n"+
                "<form id=\"order\" action=\"andromeda-3.ics.uci.edu:5130/jerseyrest/v1/api/customers\" method=\"post\" novalidate>\n"+
                "<h2 id=\"title\" style=\"text-align: center\">Order Form</h2>\n"+
                "<p style=\"text-align: center\">Required fields are followed by <strong><abbr title=\"required\">*</abbr></strong>.</p>\n"+
                "<section class=\"contact\">\n"+
                "<h2>Contact Information</h2>\n"+
                "<p>\n"+
                "<label for=\"firstname\">\n"+
                "<span>First Name: </span>\n"+
                "<strong><abbr title=\"required\">*</abbr></strong>\n"+
                "<input type=\"text\" id=\"firstname\" name=\"firstname\">\n"+
                "<div class=\"error\" id=\"firstNameError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                "<p>\n"+
                "<label for=\"lastname\">\n"+
                "<span>Last Name: </span>\n"+
                "<strong><abbr title=\"required\">*</abbr></strong>\n"+
                "<input type=\"text\" id=\"lastname\" name=\"lastname\">\n"+
                "<div class=\"error\" id=\"lastNameError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                "<p>\n"+
                "<label for=\"email\">\n"+
                "<span>Email: </span>\n"+
                "<strong><abbr title=\"required\">*</abbr></strong>\n"+
                "<input type=\"email\" id=\"email\" name=\"email\">\n"+
                "<div class=\"error\" id=\"emailError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                "<p>\n"+
                "<label for=\"phonenumber\">\n"+
                "<span>Phone Number: </span>\n"+
                "<input type=\"text\" id=\"phonenumber\" name=\"phonenumber\" placeholder=\"###-###-####\">\n"+
                "<div class=\"error\" id=\"phoneError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                "</section>\n"+
                "<input type=\"hidden\" value=\""+total+"\" name=\"totalCostInput\" id=\"totalCostInput\" />\n"+
                "<input name=\"submit\" id=\"submit\" type=\"submit\" value=\"Submit\" />\n"+
                "</form>\n"+
                "</div>\n"+
                "</div>\n"+
                "</body>\n"+
                "<script src=\"resources/javascript/detailedPage.js\"></script>\n"+
                "<script src=\"resources/javascript/orderForm.js\"></script>\n"+
                "</html>");

            RequestDispatcher rd=request.getRequestDispatcher("orderForm?orderId=''"); //ADD ID SOMEHOW. GET FROM POST? 
            rd.forward(request, response);


    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://andromeda-3.ics.uci.edu:5130/jerseyrest").build();
    }
}
