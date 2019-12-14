package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the Availabilities database table.
 * 
 */
@Entity
@Table(name="Availabilities")
@NamedQuery(name="Availability.findAll", query="SELECT a FROM Availability a")
public class Availability implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String dateDisponobilite;

	@Column(name="Description")
	private String description;
	private String dateAvailable;
	private Date startDate;
	private Date endDate;
	private int endHour;

	@Column(name="Etat")
	private String etat;

	private int idU;

	@Column(name="IsfullDay")
	private boolean isfullDay;

	private int startHour;

	@Column(name="Subject")
	private String subject;

	@Column(name="ThemeColor")
	private String themeColor;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="DoctorId")
	private User user;

	public Availability() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDateDisponobilite() {
		return this.dateDisponobilite;
	}

	public void setDateDisponobilite(String dateDisponobilite) {
		this.dateDisponobilite = dateDisponobilite;
	}

	public String getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(String dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public Object getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEndHour() {
		return this.endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getIdU() {
		return this.idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}

	public boolean getIsfullDay() {
		return this.isfullDay;
	}

	public void setIsfullDay(boolean isfullDay) {
		this.isfullDay = isfullDay;
	}

	public int getStartHour() {
		return this.startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Object getThemeColor() {
		return this.themeColor;
	}

	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}