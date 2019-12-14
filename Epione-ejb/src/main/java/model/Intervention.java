package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the Interventions database table.
 * 
 */
@Entity
@Table(name="Interventions")
@NamedQuery(name="Intervention.findAll", query="SELECT i FROM Intervention i")
public class Intervention implements Serializable {
	public Intervention(int interventionId, String description, int idU, User user) {
		super();
		this.interventionId = interventionId;
		this.description = description;
		this.idU = idU;
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int interventionId;

	@Column(name="Description")
	private String description;

	private int idU;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="DoctorId")
	private User user;
	public Intervention() {
	}

	public int getInterventionId() {
		return this.interventionId;
	}

	public void setInterventionId(int interventionId) {
		this.interventionId = interventionId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdU() {
		return this.idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}