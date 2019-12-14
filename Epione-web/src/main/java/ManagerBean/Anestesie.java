package ManagerBean;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import SessionBean.AppointementServiceLocal;
import SessionBean.DepartementServiceLocal;

import model.Doctor;


@RequestScoped
@ManagedBean(name="Anestesie")
public class Anestesie {
	
	@EJB
	private DepartementServiceLocal depS;

	private List<Doctor> Dep = new ArrayList<Doctor>();
	
	@EJB
	private AppointementServiceLocal appL;
	
	
	
	
	

	
	public void doListeDepAnestesie() {

		String response = depS.ConsommationAnestesie();
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
			
			Dep.add(d);

		}
		jsonReader.close();
	}


	public Anestesie() {
		super();
	}


	public DepartementServiceLocal getDepS() {
		return depS;
	}


	public void setDepS(DepartementServiceLocal depS) {
		this.depS = depS;
	}


	public List<Doctor> getDep() {
		return Dep;
	}


	public void setDep(List<Doctor> dep) {
		Dep = dep;
	}


	public AppointementServiceLocal getAppL() {
		return appL;
	}


	public void setAppL(AppointementServiceLocal appL) {
		this.appL = appL;
	}
	


}
