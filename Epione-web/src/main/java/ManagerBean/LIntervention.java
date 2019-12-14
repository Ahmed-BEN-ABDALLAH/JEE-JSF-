package ManagerBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.convert.DateTimeConverter;
import javax.faces.model.SelectItem;

import SessionBean.AppointementService;

@ManagedBean(name = "Intervention")
@ApplicationScoped
public class LIntervention  implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{AppointmentM}")
	private AppointmentManagement doctor;
	@EJB
	private AppointementService appS;
	
	public AppointmentManagement getDoctor() {
		return doctor;
	}

	public void setDoctor(AppointmentManagement doctor) {
		this.doctor = doctor;
	}

	public AppointementService getAppS() {
		return appS;
	}

	public void setAppS(AppointementService appS) {
		this.appS = appS;
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

	private List<model.Intervention> listreasons = new ArrayList<model.Intervention>();
	private List<SelectItem> reasons = new ArrayList<SelectItem>();

	public LIntervention() {
		super();
	}

//	@PostConstruct
//	public void getListIemsAutos() {
//		for (model.Intervention val : dolistReason()) {
//			this.reasons.add(new SelectItem(val.getDescription()));
//		}
//	}

//	public List<model.Intervention> dolistReason() {
//		listreasons.clear();
//		listreasons = appS.ListReason(doctor);
//		return listreasons;
//	}
}
