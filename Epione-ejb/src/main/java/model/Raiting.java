package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Raitings database table.
 * 
 */
@Entity
@Table(name="Raitings")
@NamedQuery(name="Raiting.findAll", query="SELECT r FROM Raiting r")
public class Raiting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private float rating;

	//bi-directional one-to-one association to Appointment
	@OneToOne
	@JoinColumn(name="id")
	private Appointment appointment;

	public Raiting() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

}