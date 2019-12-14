package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Doctor extends User {
	@OneToMany(mappedBy = "doctor",cascade=CascadeType.ALL,fetch=FetchType.EAGER)

	private List<Appointment> appointments;

	public Doctor() {
		super();
		
	}
}
