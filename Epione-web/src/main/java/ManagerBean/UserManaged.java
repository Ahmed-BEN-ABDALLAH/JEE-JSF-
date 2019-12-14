package ManagerBean;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import SessionBean.AppointementServiceLocal;
import SessionBean.DepartementServiceLocal;
import SessionBean.UserServiceLocal;
import model.Appointment;
import model.Doctor;
import model.Patient;
import model.User;

@SessionScoped
@ManagedBean(name="UserManaged")
public class UserManaged {
	@EJB
	private DepartementServiceLocal depS;

	private List<Doctor> DepHematologie = new ArrayList<Doctor>();
	
	public UserManaged() {
		super();
	}

	public DepartementServiceLocal getDepS() {
		return depS;
	}

	public void setDepS(DepartementServiceLocal depS) {
		this.depS = depS;
	}

	public AppointementServiceLocal getAppL() {
		return appL;
	}

	public void setAppL(AppointementServiceLocal appL) {
		this.appL = appL;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	@EJB
	private AppointementServiceLocal appL;
	@EJB
	private UserServiceLocal userlocal ;
	private Boolean loggedInAsAgent = false;
	private User user = new User();
	private User user2 = new User();

//	private User patient = new Patient();
	//private User doctor = new Doctor();
	private boolean isLogged = false;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Doctor> getDepHematologie() {
		return DepHematologie;
	}

	public void setDepHematologie(List<Doctor> depHematologie) {
		DepHematologie = depHematologie;
	}

	public UserServiceLocal getUserlocal() {
		return userlocal;
	}

	public void setUserlocal(UserServiceLocal userlocal) {
		this.userlocal = userlocal;
	}

	public Boolean getLoggedInAsAgent() {
		return loggedInAsAgent;
	}

	public void setLoggedInAsAgent(Boolean loggedInAsAgent) {
		this.loggedInAsAgent = loggedInAsAgent;
	}

	

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	
		

	
	public String logout() {
		String navigateTo = "";
		isLogged = false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		navigateTo = "/TemplatePatient/Login?faces-redirect=true";
		return navigateTo;
	}

	public String doLogin() {

		String navigateTo = "";
		User userLoggedIn = userlocal.loginUser(user.getEmail(), user.getPassword2());
		if (userLoggedIn != null) {
			isLogged = true;
			user = userLoggedIn;
			if (userLoggedIn instanceof Doctor) {
				navigateTo = "/TemplatePatient/Home?faces-redirect=true";
			} else if (userLoggedIn instanceof Patient) {
				navigateTo = "/TemplatePatient/HomePatient?faces-redirect=true";
			} else {
				loggedInAsAgent = true;
				navigateTo = "/TemplatePatient/Home?faces-redirect=true";
			}
		} else {
			System.err.println("not");
		}

		return navigateTo;
	}
	

	
	@PostConstruct
	public void init() {
		doListeDepHematologie();
	   	}

	public void doListeDepHematologie() {

		String response = depS.ConsommationHematologie();
		StringReader myStringReader = new StringReader(response.toString());

		JsonReader jsonReader = Json.createReader(myStringReader);
		System.out.println("Received JSON : " + response);

		JsonArray mcsJSON = jsonReader.readArray();
		System.out.println("TOTAL : " + mcsJSON.size());
		for (int i = 0; i < mcsJSON.size(); i++) {
			Doctor d = new Doctor();

			JsonObject medicalC = mcsJSON.getJsonObject(i);

			d.setFirstName(medicalC.getString("firstName"));
			d.setLastName(medicalC.getString("lastName"));
			d.setEmail(medicalC.getString("Email"));
			d.setPhoneNumber(medicalC.getString("PhoneNumber"));
			d.setAddress(medicalC.getString("Address"));
			System.out.println("amal");
			DepHematologie.add(d);

		}
		jsonReader.close();
	}
	
}
