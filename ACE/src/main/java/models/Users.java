package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "ace")
public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
	private Integer userId;
	@Column(name = "username")
	private String username;
	@Column(name = "name")
	private String userfulllame;
	@Column(name = "password")
	private String userPassword;
	@Column(name = "userstatusfkid")
	private int usersStatus;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(Integer userId, String username, String userfulllame, String userPassword, int usersStatus) {
		super();
		this.userId = userId;
		this.username = username;
		this.userfulllame = userfulllame;
		this.userPassword = userPassword;
		this.usersStatus = usersStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserfulllame() {
		return userfulllame;
	}

	public void setUserfulllame(String userfulllame) {
		this.userfulllame = userfulllame;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUsersStatus() {
		return usersStatus;
	}

	public void setUsersStatus(int usersStatus) {
		this.usersStatus = usersStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
