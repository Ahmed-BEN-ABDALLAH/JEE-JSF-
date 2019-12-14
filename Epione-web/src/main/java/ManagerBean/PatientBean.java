package ManagerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@RequestScoped
@ManagedBean(name="patient")
public class PatientBean {

	
	@ManagedProperty(value="#{identity}")
	public UserManagement UserManagement ;

	public UserManagement getUserManagement() {
		return UserManagement;
	}

	public void setUserManagement(UserManagement userManagement) {
		UserManagement = userManagement;
	}

	public PatientBean() {
		super();
	}

	public PatientBean(ManagerBean.UserManagement userManagement) {
		super();
		UserManagement = userManagement;
	}
	
	
}
