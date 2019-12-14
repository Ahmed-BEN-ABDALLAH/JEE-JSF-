package SessionBean;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import model.*;
import utils.SendConfirmationMail;

/**
 * Session Bean implementation class AppointementService
 */
@Stateless
@LocalBean
public class AppointementService implements AppointementServiceRemote, AppointementServiceLocal {

	@PersistenceContext(name = "Epione-ejb")
	EntityManager entityManager;

	public AppointementService() 
	{
		// TODO Auto-generated constructor stub
	}
	@Override
	public void Add(Appointment A) {
		entityManager.merge(A);
	}
	
	@Override
	public Appointment MAxApp(User u) {
		String req ="select u from Appointment u where  appointmentId=(select MAX(appointmentId) FROM Appointment u where patient.id=:us)";
		Query query = entityManager.createQuery(req);
		query.setParameter("us", u.getId());
	    return  (Appointment) query.getSingleResult() ;
	}
	
	@Override
	public void confirmAccount(int id) {
		User u = entityManager.find(User.class,id);

		String mail_content = "votre rendez vous a été envoyé avec succés Merci pour votre confiance ";
		
		entityManager.merge(u);
		boolean res	= SendConfirmationMail.sendMail("amal.bensassi@esprit.tn", "23300908", u.getEmail(), "confirmation",
					mail_content);
		if (res = true){
			System.out.println("email envoyer");
		}
		
		else {
			System.out.println("email non envoyer");

		}
		
	}
	@Override
	public void Update(Appointment a) {
	
		entityManager.merge(a);
	        
	    }
	@Override
	public List<User> SetSearchAdresse(String nomVille) {
		String req ="select u from Ville p ,User u where p.nomVille  LIKE CONCAT('%',:nomVille,'%') and u.roleUser='Doctor' and p.idVille=u.VilleId";
		Query query = entityManager.createQuery(req);
		query.setParameter("nomVille", nomVille);
                return  query.getResultList();
	}
	@Override
	public List<Appointment> SetSearchApp(String date , User u) {

		String req ="SELECT a FROM Appointment a  WHERE a.state Like 'Invalide' and a.dateAppointmentJEE=:dateR and patient.id=:us";
		Query query = entityManager.createQuery(req);
		query.setParameter("dateR", date);
		query.setParameter("us", u.getId());
                return  query.getResultList();
	}
	@Override
public List<User> Doctors(){
		
		String req ="SELECT a FROM User a  WHERE a.roleUser='Doctor'";
			
		Query query = entityManager.createQuery(req);
	

                return  query.getResultList();
    }
	@Override
	public List<User> SetSearchDoc(String firstName , String lastName) {

		String req ="SELECT a FROM User a  WHERE ((a.firstName=:firstName and a.roleUser='Doctor') or (a.lastName=:lastName and a.roleUser='Doctor') or"
				+ "(a.lastName=:lastName and a.roleUser='Doctor' and a.firstName=:firstName )) ";
		Query query = entityManager.createQuery(req);
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);

                return  query.getResultList();
	}
	@Override
	public List<Appointment> SetSearchVisit(String date , User u) {

		String req ="SELECT a FROM Appointment a  WHERE a.state Like 'valide' and a.dateAppointmentJEE=:dateR and patient.id=:us";
		Query query = entityManager.createQuery(req);
		query.setParameter("dateR", date);
		query.setParameter("us", u.getId());
                return  query.getResultList();
	}
	@Override
	 public List<Intervention> ListReason(User u) {
	        // TODO Auto-generated method stub
	        String req ="select r from Intervention r where user.id=:us";
			Query query = entityManager.createQuery(req);
			query.setParameter("us", u.getId());
	        @SuppressWarnings("unchecked")
	        List<Intervention> listeau = query.getResultList();
	        return listeau;
	    }
	@Override
	 public List<Availability> HourList(String date , User u) {
	        // TODO Auto-generated method stub
		 String req ="select r from Availability r where CONVERT(nvarchar(30), startDate, 104)=:d and user.id=:us";
		 Query query = entityManager.createQuery(req);
			query.setParameter("d", date);
			query.setParameter("us", u.getId());

	        @SuppressWarnings("unchecked")
	        List<Availability> listeau = query.getResultList();
	        return listeau;
	    }
	
	@Override
public String ConsommationMyAppointemeny(int id){
		System.out.println("slt");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:33430/api/IdentityExposition/GetMyApp/"+id);
	Response response = target.request().get();
	String resultat = response.readEntity(String.class);
	return(resultat);
    }
	
	
	
	
	@Override
public String ConsommationMyVisit(int id){
		
		System.out.println("slt");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:33430/api/IdentityExposition/GetVisit/"+id);
	Response response = target.request().get();
	String resultat = response.readEntity(String.class);
	return(resultat);
    }
	@Override
	public String ConsommationDelete(int id){
			
			System.out.println("slt");
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:33430/api/IdentityExposition/GetDelete/"+id);
		Response response = target.request().get();
		String resultat = response.readEntity(String.class);
		return(resultat);
	    }
	
	@Override
	public String deleteApp(int id) {
		System.out.println("slt");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:33430/api/Json/GetDeleteRDVJ/"+id);
		System.out.println("bay");
	Response response = target.request().get();
	String resultat = response.readEntity(String.class);
	return(resultat);
	
	}
	@Override
	public String deleteToutApp(int id) {
		System.out.println("slt");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:33430/api/IdentityExposition/GetDeleteToutRDV/"+id);
		System.out.println("bay");
	Response response = target.request().get();
	String resultat = response.readEntity(String.class);
	return(resultat);
	
	}

	 
}
