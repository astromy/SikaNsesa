package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status", schema = "ace")
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idstatus")
	private int statusid;
	@Column(name = "type")
	private String status;
	
	public int getStatusid() {
		return statusid;
	}
	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Status(int statusid, String status) {
		super();
		this.statusid = statusid;
		this.status = status;
	}
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*@OneToMany(mappedBy ="staffStatusIdfk")
	private List<AdminUser> adminUser;*/
	
	
}
