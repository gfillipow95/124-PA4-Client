/**
 * Created by Gen on 6/13/2017.
 */
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.HashMap;
public class addToCart extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String jsonResponse =
                target.path("v1").path("api").path("products").path(request.getParameter("id")).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        Product product = objectMapper.readValue(jsonResponse, new TypeReference<Product>(){});//Only return one product

        Integer Id = product.getId();

        Integer quantity = new Integer(0);


        HttpSession session = request.getSession();
        HashMap<Integer, Integer> updateCart = (HashMap<Integer, Integer>)session.getAttribute("cart");
        if(updateCart == null){
            updateCart = new HashMap<Integer, Integer>();
            updateCart.put(Id, 1);
        }else{
            quantity = updateCart.get(product);
            if(quantity == null){
                updateCart.put(Id, 1);
            }else{
                updateCart.put(Id, ++quantity);
            }
        }
        session.setAttribute("cart", updateCart);
//                  if(session.isNew()){
//                      cart.put(product, 1);
//                      session.setAttribute("cart", cart);
//                      session.setAttribute(nameResult.getString("product_name"), 1);
//                  }else{
//                      HashMap<String, Integer> updateCart = (HashMap<String, Integer>)session.getAttribute("cart");
//                      quantity = updateCart.get(product);
//                      new_quantity = quantity + 1;
//                      updateCart.put(product, new_quantity);
//                      session.setAttribute("cart", updateCart);
//                      quantity = (Integer)session.getAttribute(nameResult.getString("product_name"));
//                      new_quantity = quantity + 1;
//                      session.setAttribute(nameResult.getString("product_name"), new_quantity);
//                  }

//                  response.setContentType("text/html");
//                  PrintWriter out = response.getWriter();

        System.out.println(session.getAttribute("cart"));

        response.sendRedirect(request.getContextPath());
        return;

    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://andromeda-3.ics.uci.edu:5130/jerseyrest").build();
    }
}
