package dto;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class BankTransactionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bank;
	private String recieptNumber;
	private String currency;
	private Double amount;
	private String country;


	public BankTransactionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BankTransactionDTO(String bank, String recieptNumber, String currency, Double amount, String country) {
		super();
		this.bank = bank;
		this.recieptNumber = recieptNumber;
		this.currency = currency;
		this.amount = amount;
		this.country = country;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getRecieptNumber() {
		return recieptNumber;
	}

	public void setRecieptNumber(String recieptNumber) {
		this.recieptNumber = recieptNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
