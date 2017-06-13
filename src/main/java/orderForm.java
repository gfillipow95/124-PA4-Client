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
                "<div class='product-page-container'>\n"+
                "<div class=\"form-container\">\n"+
                "<form id=\"order\" action=\"andromeda-3.ics.uci.edu:5130/jerseyrest/v1/api/orders\" method=\"post\" novalidate>\n"+
                "<h2 id=\"title\" style=\"text-align: center\">Order Form</h2>\n"+
                "<p style=\"text-align: center\">Required fields are followed by <strong><abbr title=\"required\">*</abbr></strong>.</p>\n"+
                "<section class=\"shipping\">\n"+
                "<h2>Shipping Information</h2>\n"+
                "<p>\n"+
                "<span>Shipping Method: </span>\n"+
                "<select id=\"shippingMethod\" name=\"shippingMethod\" onchange=\"addShipping()\">\n"+
                "<option value=\"0\" selected>Standard Shipping($0)</option>\n"+
                "<option value=\"4\">Express Shipping($4)</option>\n"+
                "<option value=\"10\">2-Day Shipping($10)</option>\n"+
                "<option value=\"15\">Overnight Shipping($15)</option>\n"+
                "</select>\n"+
                "</p>\n"+
                "<p>\n"+
                "<label for=\"zipcode\">\n"+
                "<span>Zip Code: </span>\n"+
                "<input type=\"number\" id=\"zipcode\" name=\"zipcode\" onblur=\"getPlaceAndTax(this.value)\">\n"+
                "<div class=\"error\" id=\"zipError\"></div>\n"+
                "</label>\n"+
                "<label for=\"address\">\n"+
                "<span>Address: </span>\n"+
                "<input type=\"text\" id=\"address\" name=\"address\">\n"+
                "<div class=\"error\" id=\"addressError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                "<p>\n"+
                "<label for=\"city\">\n"+
                "<span>City: </span>\n"+
                "<input type=\"text\" id=\"city\" name=\"city\">\n"+
                "<div class=\"error\" id=\"cityError\"></div>\n"+
                "</label>\n"+
                "<label for=\"state\">\n"+
                "<span>State/Province: </span>\n"+
                "<input type=\"text\" id=\"state\" name=\"state\">\n"+
                "<div class=\"error\" id=\"stateError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                "<p>\n"+
                "<label for=\"country\">\n"+
                "<span>Country: </span>\n"+
                "<input type=\"text\" id=\"country\" name=\"country\">\n"+
                "<div class=\"error\" id=\"countryError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                //"<p id=\"totalCost\" data-total=\"0\"><strong id=\"total\">Total Cost: </strong>\n"+
                //"</p>\n"+
                "<input type=\"hidden\" value=\""+total+"\" name=\"totalCostInput\" id=\"totalCostInput\" />\n"+
                "</section>\n"+
                "<section class=\"payment\">\n"+
                "<h2>Payment Information</h2>\n"+
                "<p>\n"+
                "<label for=\"cardnumber\">\n"+
                "<span>Credit Card Number: </span>\n"+
                "<input type=\"number\" id=\"cardnumber\" name=\"cardnumber\">\n"+
                "<div class=\"error\" id=\"cardError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                "<p>\n"+
                "<label for=\"cvc\">\n"+
                "<span>CVC: </span>\n"+
                "<input type=\"number\" id=\"cvc\" name=\"cvc\">\n"+
                "<div class=\"error\" id=\"cvcError\"></div>\n"+
                "</label>\n"+
                "<label for=\"expdate\">\n"+
                "<span>Expiration Date: </span>\n"+
                "<input type=\"text\" id=\"expdate\" name=\"expdate\" placeholder=\"mm/yy\">\n"+
                "<div class=\"error\" id=\"expError\"></div>\n"+
                "</label>\n"+
                "</p>\n"+
                "</section>\n"+
                "<input name=\"submit\" id=\"submit\" type=\"submit\" value=\"Submit\" />\n"+
                "</form>\n"+
                "</div>\n"+
                "</div>\n"+
                "</body>\n"+
                "<script src=\"resources/javascript/detailedPage.js\"></script>\n"+
                "<script src=\"resources/javascript/orderForm.js\"></script>\n"+
                "</html>");

            RequestDispatcher rd=request.getRequestDispatcher("orderSuccess?orderId=''"); //ADD ID SOMEHOW GET FROM POST?
            rd.forward(request, response);


    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://andromeda-3.ics.uci.edu:5130/jerseyrest").build();
    }
}
