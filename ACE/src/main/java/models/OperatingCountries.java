package models;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country", schema = "ace")
public class OperatingCountries {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcountry")
	private Long idCountry;
	@Column(name = "countryName")
	private String countryName;
	@Column(name = "countryCode")
	private String countryCode;
	@Column(name = "currencyName")
	private String currencyName;
	@Column(name = "currencySymbol")
	private String currencySymbol;
	@Column(name = "flag")
	private Blob flag;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "clientstatusIdfk")
	 * 
	 * @JsonIgnore private Status Status;
	 * 
	 * @OneToMany(mappedBy ="idClientSubscription") private
	 * List<ClientSubscription> subcriptions;
	 */

	public OperatingCountries() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperatingCountries(Long idCountry, String countryName, String countryCode, String currencyName,
			String currencySymbol, Blob flag) {
		super();
		this.idCountry = idCountry;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.currencyName = currencyName;
		this.currencySymbol = currencySymbol;
		this.flag = flag;
	}

	public Long getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public Blob getFlag() {
		return flag;
	}

	public void setFlag(Blob flag) {
		this.flag = flag;
	}

}
