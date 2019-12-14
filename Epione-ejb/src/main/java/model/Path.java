package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Paths database table.
 * 
 */
@Entity
@Table(name="Paths")
@NamedQuery(name="Path.findAll", query="SELECT p FROM Path p")
public class Path implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pathId;

	private String comment;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="path")
	private List<Appointment> appointments;

	public Path() {
	}

	public int getPathId() {
		return this.pathId;
	}

	public void setPathId(int pathId) {
		this.pathId = pathId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	

}