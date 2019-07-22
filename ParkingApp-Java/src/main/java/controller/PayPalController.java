package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import other.PayPalClient;

@Path("/paypal")
public class PayPalController {
	
	@Autowired
	private  PayPalClient payPalClient=new PayPalClient();
    

    @Path("/make/payment/{sum}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Map<String, Object> makePayment(@PathParam("sum") String sum){
        return payPalClient.createPayment(sum);
    }
    
    @Path("/complete/payment")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Object> completePayment(@Context HttpServletRequest request){
        List<Object> list=new ArrayList<Object>();
        Object status=this.payPalClient.completePayment(request).get("status");
        list.add(status);
        return list;
    }
}
