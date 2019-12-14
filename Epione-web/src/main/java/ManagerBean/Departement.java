package ManagerBean;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import SessionBean.*;
import model.*;
@RequestScoped
@ManagedBean(name = "d")
public class Departement implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private DepartementService depS;

	private List<Doctor> DepHematologie = new ArrayList<Doctor>();

	public DepartementService getDepS() {
		return depS;
	}

	public void setDepS(DepartementService depS) {
		depS = depS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Doctor> getDepHematologie() {
		return DepHematologie;
	}

	@PostConstruct
	public void init() {
		//doListeDepHematologie();
	}

//	public void doListeDepHematologie() {
//
//		String response = depS.ConsommationHematologie();
//		StringReader myStringReader = new StringReader(response.toString());
//
//		JsonReader jsonReader = Json.createReader(myStringReader);
//		System.out.println("Received JSON : " + response);
//
//		JsonArray mcsJSON = jsonReader.readArray();
//		System.out.println("TOTAL : " + mcsJSON.size());
//		for (int i = 0; i < mcsJSON.size(); i++) {
//			Doctor d = new Doctor();
//
//			JsonObject medicalC = mcsJSON.getJsonObject(i);
//
//			d.setFirstName(medicalC.getString("firstName"));
//			d.setLastName(medicalC.getString("lastName"));
//			DepHematologie.add(d);
//
//		}
//		jsonReader.close();
//	}
//	 
	   
}
