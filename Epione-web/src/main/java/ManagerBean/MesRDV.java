package ManagerBean;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import SessionBean.AppointementServiceLocal;
import model.Appointment;
import model.Doctor;

@ApplicationScoped
@ManagedBean(name="mesRdv")
public class MesRDV {
	private List<Appointment> MyApp = new ArrayList<Appointment>();
	private List<Appointment> MyVisit = new ArrayList<Appointment>();
	private Boolean afficheform=false;

	@ManagedProperty(value = "#{identity}")
	private UserManagement userbean;
	@EJB
	private AppointementServiceLocal appL;
	
	public Boolean getAfficheform() {
		return afficheform;
	}

	public void setAfficheform(Boolean afficheform) {
		this.afficheform = afficheform;
	}

	public List<Appointment> getMyVisit() {
		return MyVisit;
	}

	public void setMyVisit(List<Appointment> myVisit) {
		MyVisit = myVisit;
	}

	public List<Appointment> getMyApp() {
		return MyApp;
	}

	public void setMyApp(List<Appointment> myApp) {
		MyApp = myApp;
	}

	public AppointementServiceLocal getAppL() {
		return appL;
	}

	public void setAppL(AppointementServiceLocal appL) {
		this.appL = appL;
	}

	public UserManagement getUserbean() {
		return userbean;
	}

	public void setUserbean(UserManagement userbean) {
		this.userbean = userbean;
	}

	@PostConstruct
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

			JsonObject medicalC = mcsJSON.getJsonObject(i);

			d.setDateAppointmentJEE(medicalC.getString("dateAppointmentJEE"));
d.setReason(medicalC.getString("reason"));
d.setState(medicalC.getString("state"));
//d.setDoctor(medicalC.);

			MyApp.add(d);

		}
		jsonReader.close();
	}
	
}
