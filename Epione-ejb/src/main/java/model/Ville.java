package model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity


public class Ville implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int idVille;


	private double longitude;

	private double latitude;
	private String nomVille;
	
	

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public int getIdVille() {
		return idVille;
	}

	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Ville(int idVille, double longitude, double latitude) {
		super();
		this.idVille = idVille;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Ville() {
		super();
	}

	@OneToMany(mappedBy="VilleId",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
   private List<User> doctors;

	
	
	
}
