package ManagerBean;



import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import model.Doctor;
import model.Appointment;
import model.Patient;
import model.User;
import SessionBean.AppointementService;
import SessionBean.AppointementServiceLocal;
import SessionBean.DepartementService;
import SessionBean.DepartementServiceLocal;
import SessionBean.UserServiceLocal;


@ApplicationScoped
@ManagedBean(name="identity")
public class UserManagement implements Serializable {

	private static final long serialVersionUID = 1L;

	
	public UserManagement() {
		super();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@EJB
	private DepartementServiceLocal depS;

	private List<Doctor> DepHematologie = new ArrayList<Doctor>();

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
		navigateTo = "/TemplatePatient/Home?faces-redirect=true";
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

	
	
	  public String updatePatient() {
			String navigateTo = "";

	        userlocal.Update(user);
			navigateTo = "/TemplatePatient/HomePatient?faces-redirect=true";

	        return navigateTo ;

	    }
	  @EJB
		private AppointementService appS;
	  public String DeletePatient() {
			String navigateTo = "";
	        
			appS.deleteToutApp(user.getId());
			userlocal.delete(user);
			navigateTo = "/TemplatePatient/Home?faces-redirect=true";

			return navigateTo;

		}
	  
	  
	  
}

