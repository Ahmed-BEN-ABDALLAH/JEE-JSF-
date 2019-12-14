package SessionBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;


/**
 * Session Bean implementation class DepartementService
 */
@Stateless
@LocalBean
public class DepartementService implements DepartementServiceRemote, DepartementServiceLocal {

    /**
     * Default constructor. 
     */
    public DepartementService() {
    
    	}
	public String ConsommationHematologie(){
		
		System.out.println("slt");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:33430/api/Json/Gethematologie");
	Response response = target.request().get();
	String resultat = response.readEntity(String.class);
	return(resultat);
    }

public String ConsommationRadio(){
		
		System.out.println("slt");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:33430/api/Json/Getradio");
	Response response = target.request().get();
	String resultat = response.readEntity(String.class);
	return(resultat);
    }
public String ConsommationDermatology(){
	
	System.out.println("slt");
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:33430/api/Json/Getdermatology");
Response response = target.request().get();
String resultat = response.readEntity(String.class);
return(resultat);
}
public String ConsommationChirurigie(){
	
	System.out.println("slt");
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:33430/api/Json/GetChirurigie");
Response response = target.request().get();
String resultat = response.readEntity(String.class);
return(resultat);
}
public String ConsommationOphtalmo(){
	
	System.out.println("slt");
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:33430/api/Json/Getophtalmo");
Response response = target.request().get();
String resultat = response.readEntity(String.class);
return(resultat);
}
public String ConsommationDentaire(){
	
	System.out.println("slt");
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:33430/api/Json/GetDentaire");
Response response = target.request().get();
String resultat = response.readEntity(String.class);
return(resultat);
}
public String ConsommationAnestesie(){
	
	System.out.println("slt");
	Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:33430/api/Json/Getanestesie");
Response response = target.request().get();
String resultat = response.readEntity(String.class);
return(resultat);
}
}
