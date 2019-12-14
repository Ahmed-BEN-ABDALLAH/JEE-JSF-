package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Chats database table.
 * 
 */
@Entity
@Table(name="Chats")
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	private String objectC;

	private String receiver;

	private String sender;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Doctor_Id")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Patient_Id")
	private User user2;

	public Chat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObjectC() {
		return this.objectC;
	}

	public void setObjectC(String objectC) {
		this.objectC = objectC;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}