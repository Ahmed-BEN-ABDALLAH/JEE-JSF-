package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CustomLogins database table.
 * 
 */
@Entity
@Table(name="CustomLogins")
@NamedQuery(name="CustomLogin.findAll", query="SELECT c FROM CustomLogin c")
public class CustomLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="LoginProvider")
	private String loginProvider;

	@Column(name="ProviderKey")
	private String providerKey;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;

	public CustomLogin() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginProvider() {
		return this.loginProvider;
	}

	public void setLoginProvider(String loginProvider) {
		this.loginProvider = loginProvider;
	}

	public String getProviderKey() {
		return this.providerKey;
	}

	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}