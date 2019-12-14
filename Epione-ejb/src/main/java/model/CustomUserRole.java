package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CustomUserRoles database table.
 * 
 */
@Entity
@Table(name="CustomUserRoles")
@NamedQuery(name="CustomUserRole.findAll", query="SELECT c FROM CustomUserRole c")
public class CustomUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="RoleId")
	private int roleId;

	//bi-directional many-to-one association to CustomRole
	@ManyToOne
	@JoinColumn(name="CustomRole_Id")
	private CustomRole customRole;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;

	public CustomUserRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public CustomRole getCustomRole() {
		return this.customRole;
	}

	public void setCustomRole(CustomRole customRole) {
		this.customRole = customRole;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}