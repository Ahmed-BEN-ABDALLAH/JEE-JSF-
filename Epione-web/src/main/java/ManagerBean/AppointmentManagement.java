package ManagerBean;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.model.SelectItem;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

import SessionBean.AppointementService;
import SessionBean.AppointementServiceLocal;
import model.*;

@ApplicationScoped
@ManagedBean(name = "AppointmentM")
public class AppointmentManagement extends DateTimeConverter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean afficheform = false;
	@ManagedProperty(value = "#{identity}")
	private UserManagement userbean;
	private User u = new User();
	private Appointment DrApp = new Appointment();
	private List<Appointment> MyApp = new ArrayList<Appointment>();
	private List<User> ListDoc = new ArrayList<User>();
private int idDelete ;
	@EJB
	private AppointementServiceLocal appL;
	@EJB
	private AppointementService appS;
	private String d;
	private String firstNameRecherhce;
	private String lastNameRecherhce;
	private String villeRecherhce;

	private String EtatListeHour =" Merci de choisir l'horaire de votre rendez vous ";

	private List<model.Intervention> listreasons = new ArrayList<model.Intervention>();
	private List<SelectItem> reasons = new ArrayList<SelectItem>();
	private List<model.Availability> listhours = new ArrayList<model.Availability>();
	private List<model.Appointment> ListMyapp = new ArrayList<model.Appointment>();
	private List<model.Appointment> Test = new ArrayList<model.Appointment>();
	private Appointment a = new Appointment();
	private List<SelectItem> hour = new ArrayList<SelectItem>();

	public List<model.Availability> getListhours() {
		return listhours;
	}

	public void setListhours(List<model.Availability> listhours) {
		this.listhours = listhours;
	}

	public Boolean getAfficheform() {
		return afficheform;
	}

	public void setAfficheform(Boolean afficheform) {
		this.afficheform = afficheform;
	}

	public List<SelectItem> getHour() {
		return hour;
	}

	public void setHour(List<SelectItem> hour) {
		this.hour = hour;
	}

	public List<Appointment> getMyApp() {
		return MyApp;
	}

	public String getVilleRecherhce() {
		return villeRecherhce;
	}

	public void setVilleRecherhce(String villeRecherhce) {
		this.villeRecherhce = villeRecherhce;
	}

	public void setMyApp(List<Appointment> myApp) {
		MyApp = myApp;
	}

	public UserManagement getUserbean() {
		return userbean;
	}

	public void setUserbean(UserManagement userbean) {
		this.userbean = userbean;
	}

	public AppointmentManagement() {

		super();
	}

	public Appointment getA() {
		return a;
	}

	public List<User> getListDoc() {
		return ListDoc;
	}

	public void setListDoc(List<User> listDoc) {
		ListDoc = listDoc;
	}

	public void setA(Appointment a) {
		this.a = a;
	}

	public AppointementService getAppS() {
		return appS;
	}

	public void setAppS(AppointementService appS) {
		this.appS = appS;
	}
	
	public String getEtatListeHour() {
		return EtatListeHour;
	}

	public void setEtatListeHour(String etatListeHour) {
		EtatListeHour = etatListeHour;
	}

	@PostConstruct
public void init(){
	 doListeMyApp() ;
	 getListIemsAutos();
	 RecherchePardateApp(d);
	 doListeMyVisit();
	 Doctors();

}

	
	public void doListeMyApp() {
		this.setAfficheform(true);

		String response = appL.ConsommationMyAppointemeny(userbean.getUser().getId());
		StringReader myStringReader = new StringReader(response.toString());

		JsonReader jsonReader = Json.createReader(myStringReader);
		System.out.println("Received JSON : " + response);

		JsonArray mcsJSON = jsonReader.readArray();
		System.out.println("TOTAL : " + mcsJSON.size());
		for (int i = 0; i < mcsJSON.size(); i++) {
			Appointment d = new Appointment();
			User doc = new User();
			Speciality S = new Speciality();

			JsonObject medicalC = mcsJSON.getJsonObject(i);
			JsonObject docteur = medicalC.getJsonObject("Doctor");
			JsonObject spec = docteur.getJsonObject("specialite");
            d.setAppointmentId(medicalC.getInt("appointmentId"));
			d.setDateAppointmentJEE(medicalC.getString("dateAppointmentJEE"));
			d.setReason(medicalC.getString("reason"));
			d.setState(medicalC.getString("state"));
			doc.setFirstName(docteur.getString("firstName"));
			doc.setFirstName(docteur.getString("lastName"));

			S.setNom(spec.getString("nom"));
			doc.setSpeciality(S);
             d.setDoctor(doc);
			MyApp.add(d);

		}
		jsonReader.close();
	}
	public void doListeMyVisit() {
		this.setAfficheform(true);
		String response = appL.ConsommationMyVisit(userbean.getUser().getId());
		StringReader myStringReader = new StringReader(response.toString());

		JsonReader jsonReader = Json.createReader(myStringReader);
		System.out.println("Received JSON : " + response);

		JsonArray mcsJSON = jsonReader.readArray();
		System.out.println("TOTAL : " + mcsJSON.size());
		for (int i = 0; i < mcsJSON.size(); i++) {
			Appointment d = new Appointment();
			User doc = new User();
			Speciality S = new Speciality();

			JsonObject medicalC = mcsJSON.getJsonObject(i);
			JsonObject docteur = medicalC.getJsonObject("Doctor");
			JsonObject spec = docteur.getJsonObject("specialite");

			d.setDateAppointmentJEE(medicalC.getString("dateAppointmentJEE"));
			d.setReason(medicalC.getString("reason"));
			d.setState(medicalC.getString("state"));
			doc.setFirstName(docteur.getString("firstName"));
			doc.setFirstName(docteur.getString("lastName"));
			S.setNom(spec.getString("nom"));
			doc.setSpeciality(S);
             d.setDoctor(doc);
			MyApp.add(d);
		}
		jsonReader.close();
	}
	public String GetIdDoctor(User doctor) {
		this.setAfficheform(false);

		u = doctor;
		String navigateTo = "";
		System.out.println("***************na7nou hounaaaa**********************");
		a.setDoctor(doctor);
		System.out.println(doctor);
		navigateTo = "/TemplatePatient/AjoutAppointement?faces-redirect=true";
		listreasons.removeAll(listreasons);

		listreasons = appS.ListReason(doctor);

		for (model.Intervention val : listreasons) {
			this.reasons.add(new SelectItem(val.getDescription()));
		}
		for (Intervention intervention : listreasons) {
			System.out.println("************************" + intervention.getDescription());
			// System.out.println("reasons******************" + reasons.get(1));
		}

		return navigateTo;

	}

	public String GetHour(String date) {
		String navigateTo = "";
		System.out.println("***************na7nou hounaaaa**********************");
		navigateTo = "/TemplatePatient/ConfirmationMail?faces-redirect=true";
		System.out.println(date);
		System.out.println(u);
		System.out.println("fin");
		a.setDateAppointmentJEE(date);
		listhours.clear();
		listhours = appS.HourList(date, u);
		if (listhours.isEmpty()==true)
		{
			EtatListeHour="cette date occupée";
		}
		
		System.out.println("test1");
		System.out.println(listhours);
		for (model.Availability val : listhours) {
			this.hour.add(new SelectItem(val.getStartHour()));
		}
		for (Availability Availability : listhours) {
			System.out.println("************************" + Availability.getStartHour());
			// System.out.println("reasons******************" + reasons.get(1));
		}

		return navigateTo;

	}

	public void getListIemsAutos() {
		for (model.Intervention val : listreasons) {
			this.reasons.add(new SelectItem(val.getDescription()));
		}

	}

	public String doadd(String date) {

		String navigateTo = "";
		a.setPatient(userbean.getUser());
		a.setState("Invalide");

		appS.Add(a);
		System.out.println(date);
		System.out.println(u);
		System.out.println("fin");
		listreasons.clear();
		listhours.clear();
		listhours = appS.HourList(date, u);
		System.out.println("test1");
		System.out.println(listhours);

		for (model.Availability val : listhours) {
			this.hour.add(new SelectItem(val.getStartDate().getHours()));
		}
//		for (Availability Availability : listhours) {
//			System.out.println("************************" + Availability.getStartHour());
//			 System.out.println("reasons******************" + reasons.get(1));
//		}
		System.out.println(appS.MAxApp(userbean.getUser()));
		System.out.println("Ajouté");
		navigateTo = "/TemplatePatient/ConfirmationMail?faces-redirect=true";
		System.out.println(this.a.getAppointmentId());
		return navigateTo;
	}

	

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public void RechercheParAdresse(String firstName , String lastName) {

		this.setAfficheform(true);
		ListDoc = appS.SetSearchDoc(firstName, lastName);
		System.out.println("******************");
		System.out.println(ListDoc);
		System.out.println("******************");


	}
	public void RechercheParVille(String ville) {

		this.setAfficheform(true);
		ListDoc = appS.SetSearchAdresse(ville);
		System.out.println("******************");
		System.out.println(ListDoc);
		System.out.println("******************");


	}
	

	public void Doctors() {

		this.setAfficheform(true);
		ListDoc = appS.Doctors();
		System.out.println("******************");
		System.out.println(ListDoc);
		System.out.println("******************");


	}
	public void RecherchePardateApp(String date) {
		System.out.println("date*************************");

		System.out.println(date);
		this.setAfficheform(true);
		MyApp = appS.SetSearchApp(date, userbean.getUser());

		System.out.println(appS.SetSearchApp(date, userbean.getUser()));

	}
	public void RecherchePardateVisit(String date) {
		System.out.println("date*************************");

		System.out.println(date);
		this.setAfficheform(true);
		MyApp = appS.SetSearchVisit(date, userbean.getUser());

		System.out.println(appS.SetSearchApp(date, userbean.getUser()));

	}

	public String redirectToAjoutM() {
		String navigateTo = "";

		navigateTo = "/TemplatePatient/AjoutAppointement?faces-redirect=true";

		return navigateTo;

	}

	public AppointmentManagement(Appointment a, AppointementService appS, List<model.Intervention> listreasons,
			List<SelectItem> reasons) {
		super();
		this.a = a;
		this.appS = appS;
		this.listreasons = listreasons;
		this.reasons = reasons;
	}

	
	public String getFirstNameRecherhce() {
		return firstNameRecherhce;
	}

	public void setFirstNameRecherhce(String firstNameRecherhce) {
		this.firstNameRecherhce = firstNameRecherhce;
	}

	public String getLastNameRecherhce() {
		return lastNameRecherhce;
	}

	public void setLastNameRecherhce(String lastNameRecherhce) {
		this.lastNameRecherhce = lastNameRecherhce;
	}

	public List<model.Intervention> getListreasons() {
		return listreasons;
	}

	public void setListreasons(List<model.Intervention> listreasons) {
		this.listreasons = listreasons;
	}

	public List<SelectItem> getReasons() {
		return reasons;
	}

	public void setReasons(List<SelectItem> reasons) {
		this.reasons = reasons;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AppointmentManagement(Appointment a, AppointementService appS) {
		super();
		this.a = a;
		this.appS = appS;
	}

	public String Delete() {

		String navigateTo = "";
		System.out.println("Begin delete");

		appS.ConsommationDelete(userbean.getUser().getId());
		System.out.println("delete");
		navigateTo = "/TemplatePatient/HomePatient?faces-redirect=true";

		return navigateTo;
	}
	 public String DeleteApp(int ida) {
		 String navigateTo = "";
			System.out.println("Begin delete");
System.out.println(ida);
			appS.deleteApp(ida);
			System.out.println("delete");
			
			navigateTo = "/TemplatePatient/HomePatient?faces-redirect=true";
			return navigateTo;

		}
	  
	 public void handleClose(CloseEvent event) {
	        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
	    }
	     
	    public void handleMove(MoveEvent event) {
	        addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
	    }
	     
	    public void destroyWorld() {
	        addMessage("System Error", "Please try again later.");
	    }
	     
	    public void addMessage(String summary, String detail) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	    private String date ;
	    private String firstName ;
	    private String lastName ;

	    public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String updateApp() {
	    	 FacesContext context = FacesContext.getCurrentInstance();
	         
		        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + "") );
		        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
	    	appS.confirmAccount(userbean.getUser().getId());

			String navigateTo = "";
			addMessage("System Error", "Please try again later.");
			appS.Update(appS.MAxApp(userbean.getUser()));
			navigateTo = "/TemplatePatient/HomePatient?faces-redirect=true";

			return navigateTo;

		}
	    
	    
	    
	 
	
		
	    
	    
	    
}
