import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.servlet.ServletException;
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;


/**
 * Created by Gen on 5/27/2017.
 */
public class orderSuccess extends HttpServlet{

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        //WOULD THIS INFO COME FROM A GET METHOD BASED ON CUSTOMER ID?
      /*  String firstName=request.getParameter("firstname");
        String lastName=request.getParameter("lastname");
        String email=request.getParameter("email");
        String phoneNumber=request.getParameter("phonenumber");
      */
//        //Change these all from request to response and hopefully that works with the POST call
//        String shipping=request.getParameter("shippingMethod");
//        String address=request.getParameter("address");
//        String city=request.getParameter("city");
//        String state=request.getParameter("state");
//        String country=request.getParameter("country");
//        String totalCost=request.getParameter("totalCostInput");
//        String card=request.getParameter("cardnumber");
//        String cvc=request.getParameter("cvc");
//        String expDate=request.getParameter("expdate");
//        String zipCode=request.getParameter("zipcode");
//
//        String id=request.getParameter("orderId");

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        Order order = objectMapper.readValue(response, new TypeReference<Order>(){});//Only return one product



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

        out.print(order.getCartInfo());

//        try{
//            Statement nameStatement = productConnection.createStatement();
//            String sql;
//            sql = "SELECT * FROM order_info_new INNER JOIN customer_info ON order_info_new.customer_id = customer_info.customer_id WHERE order_info_new.customer_id='"+id+"'";
//            System.out.println(sql);
//            ResultSet nameResult = nameStatement.executeQuery(sql);
//            nameResult.next();
//
//            String cartInfoString = nameResult.getString("cart_info");
//            String[] cartArray = cartInfoString.split(",");
//
//            productConnection.close();
//            nameStatement.close();
//            nameResult.close();
//
//            for (int i = 0; i < cartArray.length; i++){
//                String[] order = cartArray[i].split(":");
//                String product = order[0];
//                String quantity = order[1];
//                out.print("<li><h3>"+product+" X "+quantity+"</h3></li>\n");
//
//            }
//
//
//
//
//        }catch (SQLException sqlException) {
//            System.out.println("SQL Exception Error : " + sqlException.getMessage());
//        }
//
//        out.print("<li><h2>Shipping To:</h2></li>\n"+
//                "<li><h3>Name: "+firstName+" "+lastName+"</h3></li>\n"+
//                "<li><h3>Address: "+address+"</h3></li>\n"+
//                "<li><h3>City: "+city+"</h3></li>\n"+
//                "<li><h3>State: "+state+"</h3></li>\n"+
//                "<li><h3>Zipcode: "+zipCode+"</h3></li>\n"+
//                "<li><h3>Country: "+country+"</h3></li>\n"+
//                "<li><h3>Shipping Method: "+shipping+"</h3></li>\n"+
//                "<li><h3>Total Cost: "+totalCost+"</h3></li>\n"+
//                "<li><h2>Thank you for shopping at a Coffee & Stuff!</h2></li>\n"+
//                "</ul>\n"+
//                "</div>\n"+
//                "</body>\n"+
//                "</html>\n");
        session.removeAttribute("cart");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }


}
