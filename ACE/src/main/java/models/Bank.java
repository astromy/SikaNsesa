package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "banks", schema = "ace")
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbanks")
	private int idbanks;
	@Column(name = "bankName")
	private String bankName;
	@Column(name = "bankCode")
	private String bankCode;
	@Column(name = "banksHook")
	private String banksHook;
	@Column(name = "bankBalance")
	private Double bankBalance;
	
	@ManyToOne
	@JoinColumn(name = "banksCountryFK")
	@JsonIgnore
	private OperatingCountries bankCountry;

	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bank(int idbanks, String bankName, String bankCode, String banksHook, Double bankBalance,
			OperatingCountries bankCountry) {
		super();
		this.idbanks = idbanks;
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.banksHook = banksHook;
		this.bankBalance = bankBalance;
		this.bankCountry = bankCountry;
	}

	public int getIdbanks() {
		return idbanks;
	}

	public void setIdbanks(int idbanks) {
		this.idbanks = idbanks;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBanksHook() {
		return banksHook;
	}

	public void setBanksHook(String banksHook) {
		this.banksHook = banksHook;
	}

	public Double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(Double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public OperatingCountries getBankCountry() {
		return bankCountry;
	}

	public void setBankCountry(OperatingCountries bankCountry) {
		this.bankCountry = bankCountry;
	}
	
	

	/*@OneToMany(mappedBy ="staffStatusIdfk")
	private List<AdminUser> adminUser;*/
	
	
}
