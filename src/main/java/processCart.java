import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.sql.PreparedStatement;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import javax.ws.rs.client.Entity;



/**
 * Created by Gen on 5/27/2017.
 */
public class processCart extends HttpServlet{

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String firstName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        String email=request.getParameter("email");
        String phoneNumber=request.getParameter("phonenumber");
        String shipping=request.getParameter("shippingMethod");
        String address=request.getParameter("address");
        String city=request.getParameter("city");
        String state=request.getParameter("state");
        String country=request.getParameter("country");
        String totalCost=request.getParameter("totalCostInput");
        String cartInfo=request.getParameter("cart_info");
        String card=request.getParameter("cardnumber");
        String cvc=request.getParameter("cvc");
        String expDate=request.getParameter("expdate");
        String zipCode=request.getParameter("zipcode");
        System.out.println("First Name: " + firstName);

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        Order order = new Order();
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setEmail(email);
        order.setPhone(phoneNumber);
        order.setCartInfo(cartInfo);
        order.setShipping(shipping);
        order.setAddress(address);
        order.setCity(city);
        order.setState(state);
        order.setZipCode(Integer.parseInt(zipCode));
        order.setCountry(country);
        order.setCard(card);
        order.setCvc(Integer.parseInt(cvc));
        order.setExpDate(expDate);
        order.setTotalCost(Double.parseDouble(totalCost));

        String jsonResponse =
                target.path("v1").path("api").path("orders").
                request().
                accept(MediaType.APPLICATION_JSON).
                post(Entity.entity(order, MediaType.APPLICATION_JSON), String.class);

        System.out.println(jsonResponse);

      //  Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
      //  Response resp = invocationBuilder.post(Entity.entity(order, MediaType.APPLICATION_JSON));

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        Order order_conf = objectMapper.readValue(jsonResponse, new TypeReference<Order>(){});//Only return one product

        out.print("<!DOCTYPE html>\n"+
                "<html lang=\"en\">\n"+
                "<head>\n"+
                "<meta charset=\"UTF-8\">\n"+
                "<link rel='stylesheet' href='resources/stylesheets/productPage.css' />\n"+
                "<link href=\"https://fonts.googleapis.com/css?family=Lusitana\" rel=\"stylesheet\">\n"+
                "</head>\n"+
                "<body>\n"+
                "<div class=\"nav-bar-container\">\n"+
                "<ul class=\"nav-bar\">\n"+
                "<li><a id=\"navbar-home\" href=\"index#home\">Back to Home</a></li>\n"+
                "</ul>\n"+
                "</div>\n"+
                "<h1 style=\"text-align: center\">Your Order has been processed!</h1>\n"+
                "<div id='order-confirmation'>\n"+
                "<ul>\n"+
                "<li><h2>Order Details: </h2></li>\n");

        String[] cartArray = cartInfo.split(",");

        for (int i = 0; i < cartArray.length; i++){
            String[] order_details = cartArray[i].split(":");
            String product = order_details[0];
            String quantity = order_details[1];
            out.print("<li><h3>"+product+" X "+quantity+"</h3></li>\n");
        }

        out.print("<li><h2>Shipping To:</h2></li>\n"+
                "<li><h3>Name: "+firstName+" "+lastName+"</h3></li>\n"+
                "<li><h3>Address: "+address+"</h3></li>\n"+
                "<li><h3>City: "+city+"</h3></li>\n"+
                "<li><h3>State: "+state+"</h3></li>\n"+
                "<li><h3>Zipcode: "+zipCode+"</h3></li>\n"+
                "<li><h3>Country: "+country+"</h3></li>\n"+
                "<li><h3>Shipping Method: "+shipping+"</h3></li>\n"+
                "<li><h3>Total Cost: "+totalCost+"</h3></li>\n"+
                "<li><h2>Thank you for shopping at a Coffee & Stuff!</h2></li>\n"+
                "</ul>\n"+
                "</div>\n"+
                "</body>\n"+
                "</html>\n");


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
