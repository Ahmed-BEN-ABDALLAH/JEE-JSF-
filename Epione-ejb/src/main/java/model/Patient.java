package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Patient extends User {

	// bi-directional many-to-one association to Appointment

	// @OneToMany(mappedBy = "patientId", cascade = CascadeType.PERSIST, fetch =
	// FetchType.EAGER)
	// private List<Appointment> Appointments;

	@OneToMany(mappedBy = "patient",cascade=CascadeType.ALL,fetch=FetchType.EAGER)

	private List<Appointment> appointments;

	public Patient() {
		super();
	}

}
