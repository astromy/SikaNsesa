package models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "banktransaction", schema = "ace")
public class BankTransaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbanktransaction")
	private Long idbanktransaction;
	@Column(name = "banktransactionType")
	private String banktransactionType;
	@Column(name = "banktransactionCurrency")
	private String banktransactionCurrency;
	@Column(name = "banktransactionAmount")
	private Double banktransactionAmount;
	@Column(name = "banktransactionReciept")
	private String banktransactionReciept;
	@Column(name = "banktransactionCommentary")
	private String banktransactionCommentary;
	@Column(name = "banktransactionDate")
	private LocalDateTime banktransactionDate;
	@Column(name = "banktransactionBalance")
	private Double banktransactionBalance;

	@ManyToOne
	@JoinColumn(name = "banktransactionBankfk")
	private Bank bank;

	@ManyToOne
	@JoinColumn(name = "banktransactionCountry")
	private OperatingCountries banktransactionCountry;

	@ManyToOne
	@JoinColumn(name = "banktransactionStatus")
	private Status status;


	public BankTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BankTransaction(Long idbanktransaction, String banktransactionType, String banktransactionCurrency,
			Double banktransactionAmount, String banktransactionReciept, String banktransactionCommentary,
			LocalDateTime banktransactionDate, String recipientCurrency, Double banktransactionBalance, Bank bank,
			OperatingCountries banktransactionCountry, Status status) {
		super();
		this.idbanktransaction = idbanktransaction;
		this.banktransactionType = banktransactionType;
		this.banktransactionCurrency = banktransactionCurrency;
		this.banktransactionAmount = banktransactionAmount;
		this.banktransactionReciept = banktransactionReciept;
		this.banktransactionCommentary = banktransactionCommentary;
		this.banktransactionDate = banktransactionDate;
		this.banktransactionBalance = banktransactionBalance;
		this.bank = bank;
		this.banktransactionCountry = banktransactionCountry;
		this.status = status;
	}


	public Long getIdbanktransaction() {
		return idbanktransaction;
	}


	public void setIdbanktransaction(Long idbanktransaction) {
		this.idbanktransaction = idbanktransaction;
	}


	public String getBanktransactionType() {
		return banktransactionType;
	}


	public void setBanktransactionType(String banktransactionType) {
		this.banktransactionType = banktransactionType;
	}


	public String getBanktransactionCurrency() {
		return banktransactionCurrency;
	}


	public void setBanktransactionCurrency(String banktransactionCurrency) {
		this.banktransactionCurrency = banktransactionCurrency;
	}


	public Double getBanktransactionAmount() {
		return banktransactionAmount;
	}


	public void setBanktransactionAmount(Double banktransactionAmount) {
		this.banktransactionAmount = banktransactionAmount;
	}


	public String getBanktransactionReciept() {
		return banktransactionReciept;
	}


	public void setBanktransactionReciept(String banktransactionReciept) {
		this.banktransactionReciept = banktransactionReciept;
	}


	public String getBanktransactionCommentary() {
		return banktransactionCommentary;
	}


	public void setBanktransactionCommentary(String banktransactionCommentary) {
		this.banktransactionCommentary = banktransactionCommentary;
	}


	public LocalDateTime getBanktransactionDate() {
		return banktransactionDate;
	}


	public void setBanktransactionDate(LocalDateTime banktransactionDate) {
		this.banktransactionDate = banktransactionDate;
	}


	public Double getBanktransactionBalance() {
		return banktransactionBalance;
	}


	public void setBanktransactionBalance(Double banktransactionBalance) {
		this.banktransactionBalance = banktransactionBalance;
	}


	public Bank getBank() {
		return bank;
	}


	public void setBank(Bank bank) {
		this.bank = bank;
	}


	public OperatingCountries getBanktransactionCountry() {
		return banktransactionCountry;
	}


	public void setBanktransactionCountry(OperatingCountries banktransactionCountry) {
		this.banktransactionCountry = banktransactionCountry;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
