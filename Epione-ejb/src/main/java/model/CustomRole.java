package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CustomRoles database table.
 * 
 */
@Entity
@Table(name="CustomRoles")
@NamedQuery(name="CustomRole.findAll", query="SELECT c FROM CustomRole c")
public class CustomRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-one association to CustomUserRole
	@OneToMany(mappedBy="customRole")
	private List<CustomUserRole> customUserRoles;

	public CustomRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CustomUserRole> getCustomUserRoles() {
		return this.customUserRoles;
	}

	public void setCustomUserRoles(List<CustomUserRole> customUserRoles) {
		this.customUserRoles = customUserRoles;
	}

	public CustomUserRole addCustomUserRole(CustomUserRole customUserRole) {
		getCustomUserRoles().add(customUserRole);
		customUserRole.setCustomRole(this);

		return customUserRole;
	}

	public CustomUserRole removeCustomUserRole(CustomUserRole customUserRole) {
		getCustomUserRoles().remove(customUserRole);
		customUserRole.setCustomRole(null);

		return customUserRole;
	}

}