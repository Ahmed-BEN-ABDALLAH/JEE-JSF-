package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CustomUserClaims database table.
 * 
 */
@Entity
@Table(name="CustomUserClaims")
@NamedQuery(name="CustomUserClaim.findAll", query="SELECT c FROM CustomUserClaim c")
public class CustomUserClaim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="ClaimType")
	private String claimType;

	@Column(name="ClaimValue")
	private String claimValue;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;

	public CustomUserClaim() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClaimType() {
		return this.claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimValue() {
		return this.claimValue;
	}

	public void setClaimValue(String claimValue) {
		this.claimValue = claimValue;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}