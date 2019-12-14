package ManagerBean;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import SessionBean.AppointementService;
import SessionBean.AppointementServiceLocal;
import SessionBean.DepartementServiceLocal;
import model.Doctor;

@SessionScoped
@ManagedBean(name="hematologie")
public class hematologie {
	@EJB
	private DepartementServiceLocal depS;

	private List<Doctor> Dep = new ArrayList<Doctor>();
	
	@EJB
	private AppointementService appL;
	
	
	public hematologie() {
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

	


	public AppointementService getAppL() {
		return appL;
	}



	public void setAppL(AppointementService appL) {
		this.appL = appL;
	}


    @PostConstruct
	public void doListeDepRadio() {

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
			d.setId(medicalC.getInt("Id"));
			Dep.add(d);
System.out.println(d);
		}
		jsonReader.close();
	}
   
}
