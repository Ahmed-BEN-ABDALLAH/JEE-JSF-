package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Role")
@DiscriminatorValue(value="true")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="AccessFailedCount")
	private int accessFailedCount;

	@Column(name="Address")
	private String address;

	private int age;

	private int cin;

	@Column(name="Email")
	private String email;

	@Column(name="EmailConfirmed")
	private boolean emailConfirmed;

	private String firstName;

	private String gender;

	private String imagePath;

	private String lastName;

	@Column(name="LockoutEnabled")
	private boolean lockoutEnabled;

	@Column(name="LockoutEndDateUtc")
	private String lockoutEndDateUtc;

	@Column(name="PasswordHash")
	private String passwordHash;
	
	private String password2;

	@Column(name="PhoneNumber")
	private String phoneNumber;

	@Column(name="PhoneNumberConfirmed")
	private boolean phoneNumberConfirmed;


	@Column(name="RoleUser")
	private String roleUser;

	@Column(name="SecurityStamp")
	private String securityStamp;

	@Column(name="TwoFactorEnabled")
	private boolean twoFactorEnabled;

	@Column(name="UserName")
	private String userName;



	

	//bi-directional many-to-one association to Availability
	@OneToMany(mappedBy="user")
	private List<Availability> availabilities;

	//bi-directional many-to-one association to Chat
	@OneToMany(mappedBy="user1")
	private List<Chat> chats1;

	//bi-directional many-to-one association to Chat
	@OneToMany(mappedBy="user2")
	private List<Chat> chats2;

	//bi-directional many-to-one association to CustomLogin
	@OneToMany(mappedBy="user")
	private List<CustomLogin> customLogins;

	//bi-directional many-to-one association to CustomUserClaim
	@OneToMany(mappedBy="user")
	private List<CustomUserClaim> customUserClaims;

	//bi-directional many-to-one association to CustomUserRole
	@OneToMany(mappedBy="user")
	private List<CustomUserRole> customUserRoles;

	//bi-directional many-to-one association to Intervention
	@OneToMany(mappedBy="user")
	private List<Intervention> interventions;
	@ManyToOne
	@JoinColumn(name="VilleId")
	private Speciality VilleId;

	//bi-directional many-to-one association to Speciality
	@ManyToOne
	@JoinColumn(name="SpecialityId")
	private Speciality speciality;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccessFailedCount() {
		return this.accessFailedCount;
	}

	public void setAccessFailedCount(int accessFailedCount) {
		this.accessFailedCount = accessFailedCount;
	}
	public String getPassword() {
		return password2;
	}

	public void setPassword(String password2) {
		this.password2 = password2;
	}
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCin() {
		return this.cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEmailConfirmed() {
		return this.emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Object getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getLockoutEnabled() {
		return this.lockoutEnabled;
	}

	public void setLockoutEnabled(boolean lockoutEnabled) {
		this.lockoutEnabled = lockoutEnabled;
	}

	public String getLockoutEndDateUtc() {
		return this.lockoutEndDateUtc;
	}

	public void setLockoutEndDateUtc(String lockoutEndDateUtc) {
		this.lockoutEndDateUtc = lockoutEndDateUtc;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Object getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getPhoneNumberConfirmed() {
		return this.phoneNumberConfirmed;
	}

	public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
		this.phoneNumberConfirmed = phoneNumberConfirmed;
	}

	

	public String getRoleUser() {
		return this.roleUser;
	}

	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}

	public String getSecurityStamp() {
		return this.securityStamp;
	}

	public void setSecurityStamp(String securityStamp) {
		this.securityStamp = securityStamp;
	}

	public boolean getTwoFactorEnabled() {
		return this.twoFactorEnabled;
	}

	public void setTwoFactorEnabled(boolean twoFactorEnabled) {
		this.twoFactorEnabled = twoFactorEnabled;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	

	
	

	public Speciality getVilleId() {
		return VilleId;
	}

	public void setVilleId(Speciality villeId) {
		VilleId = villeId;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Availability> getAvailabilities() {
		return this.availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	

	public List<Chat> getChats1() {
		return this.chats1;
	}

	public void setChats1(List<Chat> chats1) {
		this.chats1 = chats1;
	}

	

	public List<Chat> getChats2() {
		return this.chats2;
	}

	public void setChats2(List<Chat> chats2) {
		this.chats2 = chats2;
	}

	
	public List<CustomLogin> getCustomLogins() {
		return this.customLogins;
	}

	public void setCustomLogins(List<CustomLogin> customLogins) {
		this.customLogins = customLogins;
	}

	public CustomLogin addCustomLogin(CustomLogin customLogin) {
		getCustomLogins().add(customLogin);
		customLogin.setUser(this);

		return customLogin;
	}

	public CustomLogin removeCustomLogin(CustomLogin customLogin) {
		getCustomLogins().remove(customLogin);
		customLogin.setUser(null);

		return customLogin;
	}

	public List<CustomUserClaim> getCustomUserClaims() {
		return this.customUserClaims;
	}

	public void setCustomUserClaims(List<CustomUserClaim> customUserClaims) {
		this.customUserClaims = customUserClaims;
	}

	public CustomUserClaim addCustomUserClaim(CustomUserClaim customUserClaim) {
		getCustomUserClaims().add(customUserClaim);
		customUserClaim.setUser(this);

		return customUserClaim;
	}

	public CustomUserClaim removeCustomUserClaim(CustomUserClaim customUserClaim) {
		getCustomUserClaims().remove(customUserClaim);
		customUserClaim.setUser(null);

		return customUserClaim;
	}

	public List<CustomUserRole> getCustomUserRoles() {
		return this.customUserRoles;
	}

	public void setCustomUserRoles(List<CustomUserRole> customUserRoles) {
		this.customUserRoles = customUserRoles;
	}

	public CustomUserRole addCustomUserRole(CustomUserRole customUserRole) {
		getCustomUserRoles().add(customUserRole);
		customUserRole.setUser(this);

		return customUserRole;
	}

	public CustomUserRole removeCustomUserRole(CustomUserRole customUserRole) {
		getCustomUserRoles().remove(customUserRole);
		customUserRole.setUser(null);

		return customUserRole;
	}

	public List<Intervention> getInterventions() {
		return this.interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}

	public Intervention addIntervention(Intervention intervention) {
		getInterventions().add(intervention);
		intervention.setUser(this);

		return intervention;
	}

	public Intervention removeIntervention(Intervention intervention) {
		getInterventions().remove(intervention);
		intervention.setUser(null);

		return intervention;
	}

	public Speciality getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", cin=" + cin + ", email=" + email + ", emailConfirmed=" + emailConfirmed
				+ ", firstName=" + firstName + ", gender=" + gender + "]";
	}

	public User(int id, String address, String firstName, String lastName, String phoneNumber) {
		super();
		this.id = id;
		this.address = address;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	

}