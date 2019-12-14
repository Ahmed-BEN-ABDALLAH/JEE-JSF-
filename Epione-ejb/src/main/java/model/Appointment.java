package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the Appointments database table.
 * 
 */
@Entity
@Table(name = "Appointments")
@NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentId;
	private String dateAppointmentJEE;

    @Temporal(TemporalType.DATE)
  private Date datePriseRDV=new Date();

	@Column(name = "HourAppointment")
	private String hourAppointment;

	private String message;

	private int note;

	private float price;

	private String reason;

	private String state;

	// bi-directional many-to-one association to Path
	@ManyToOne(optional=false , cascade=CascadeType.ALL)
	private Path path;

	// bi-directional many-to-one association to User
	@ManyToOne(optional=false , cascade=CascadeType.ALL)
	@JoinColumn(name = "Iddoctor")
	private User doctor;
	
	// bi-directional many-to-one association to User
	
	@ManyToOne(optional=false , cascade=CascadeType.ALL)
	@JoinColumn(name="IdPatient" )
	private User patient;
	// bi-directional one-to-one association to Raiting
	@OneToOne(mappedBy = "appointment" , cascade=CascadeType.ALL)
	private Raiting raiting;

	public Appointment() {
	}

	public int getAppointmentId() {
		return this.appointmentId;
	}

	public String getDateAppointmentJEE() {
		return dateAppointmentJEE;
	}

	public void setDateAppointmentJEE(String dateAppointmentJEE) {
		this.dateAppointmentJEE = dateAppointmentJEE;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}


	public Date getDatePriseRDV() {
		return this.datePriseRDV;
	}

	public void setDatePriseRDV(Date datePriseRDV) {
		this.datePriseRDV = datePriseRDV;
	}

	public String getHourAppointment() {
		return this.hourAppointment;
	}

	public void setHourAppointment(String hourAppointment) {
		this.hourAppointment = hourAppointment;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getNote() {
		return this.note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Object getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Path getPath() {
		return this.path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	


	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		public Raiting getRaiting() {
		return this.raiting;
	}

	public void setRaiting(Raiting raiting) {
		this.raiting = raiting;
	}

	
	
	

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	
	

}