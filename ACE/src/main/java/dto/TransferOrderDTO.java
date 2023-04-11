package dto;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class TransferOrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sender;
	private String senderCurrency;
	private Double senderAmount;
	private String recipient;
	private String recipientCurrency;
	private String senderCountries;
	private String bank;
	private String emial;
	private String destinationCountry;


	public TransferOrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TransferOrderDTO(String sender, String senderCurrency, Double senderAmount, String recipient,
			String recipientCurrency, String senderCountries, String bank, String emial, String destinationCountry) {
		super();
		this.sender = sender;
		this.senderCurrency = senderCurrency;
		this.senderAmount = senderAmount;
		this.recipient = recipient;
		this.recipientCurrency = recipientCurrency;
		this.senderCountries = senderCountries;
		this.bank = bank;
		this.emial = emial;
		this.destinationCountry = destinationCountry;
	}

	public String getEmial() {
		return emial;
	}

	public void setEmial(String emial) {
		this.emial = emial;
	}


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderCurrency() {
		return senderCurrency;
	}

	public void setSenderCurrency(String senderCurrency) {
		this.senderCurrency = senderCurrency;
	}

	public Double getSenderAmount() {
		return senderAmount;
	}

	public void setSenderAmount(Double senderAmount) {
		this.senderAmount = senderAmount;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipientCurrency() {
		return recipientCurrency;
	}

	public void setRecipientCurrency(String recipientCurrency) {
		this.recipientCurrency = recipientCurrency;
	}

	public String getSenderCountries() {
		return senderCountries;
	}

	public void setSenderCountries(String senderCountries) {
		this.senderCountries = senderCountries;
	}


	public String getDestinationCountry() {
		return destinationCountry;
	}


	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
}
