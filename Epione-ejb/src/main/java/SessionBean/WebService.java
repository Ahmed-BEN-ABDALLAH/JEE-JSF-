package SessionBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;



import model.User;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
@Stateless
@LocalBean
public class WebService implements WebServiceRemote, WebServiceLocal {

    /**
     * Default constructor. 
     */
    public WebService() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public List<User> getUsers() {
		
    		
    		Client client = ClientBuilder.newClient();
    		
    		WebTarget target = client.target("http://localhost:33430/api/IdentityExposition/GetUsers");
    		
    		Response response = target.request().get();
    		
    		String result = response.readEntity(String.class);
    		
    		List<String> list = new ArrayList<String>();
    		while(result.contains("{")) {
    			String usr = result.substring(result.indexOf("{")+1, result.indexOf("}"));
    			result = result.replace("{"+usr+"}","");
    			list.add("{"+usr+"}");
    		}
    		
    		List<User> users = new ArrayList<User>();
    		Gson gson = new Gson();
    		for(String item : list) {
    			User user = gson.fromJson(item , User.class);
    			users.add(user);
    		}
    		
    		return users;
    	}

   
}
