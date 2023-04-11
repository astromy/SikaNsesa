package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction", schema = "ace")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idTransaction;
	@Column(name = "sender")
	private String sender;
	@Column(name = "senderCurrency")
	private String senderCurrency;
	@Column(name = "senderAmount")
	private Double senderAmount;
	@Column(name = "recipient")
	private String recipient;
	@Column(name = "recipientBank")
	private String recipientBank;
	@Column(name = "transactionDate")
	private Date transactionDate;
	@Column(name = "recipientCurrency")
	private String recipientCurrency;
	@Column(name = "recipientAmount")
	private Double recipientAmount;

	@ManyToOne
	@JoinColumn(name = "senderCountry")
	private OperatingCountries senderCountries;

	@ManyToOne
	@JoinColumn(name = "destinationCountry")
	private OperatingCountries destinationCountry;

	@ManyToOne
	@JoinColumn(name = "status")
	private Status status;


	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Transaction(Long idTransaction, String sender, String senderCurrency, Double senderAmount, String recipient,
			String recipientBank, Date transactionDate, String recipientCurrency, Double recipientAmount,
			OperatingCountries senderCountries, OperatingCountries destinationCountry, Status status) {
		super();
		this.idTransaction = idTransaction;
		this.sender = sender;
		this.senderCurrency = senderCurrency;
		this.senderAmount = senderAmount;
		this.recipient = recipient;
		this.recipientBank = recipientBank;
		this.transactionDate = transactionDate;
		this.recipientCurrency = recipientCurrency;
		this.recipientAmount = recipientAmount;
		this.senderCountries = senderCountries;
		this.destinationCountry = destinationCountry;
		this.status = status;
	}


	public Long getIdTransaction() {
		return idTransaction;
	}


	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getRecipientCurrency() {
		return recipientCurrency;
	}

	public void setRecipientCurrency(String recipientCurrency) {
		this.recipientCurrency = recipientCurrency;
	}

	public Double getRecipientAmount() {
		return recipientAmount;
	}

	public void setRecipientAmount(Double recipientAmount) {
		this.recipientAmount = recipientAmount;
	}

	public OperatingCountries getSenderCountries() {
		return senderCountries;
	}

	public void setSenderCountries(OperatingCountries senderCountries) {
		this.senderCountries = senderCountries;
	}


	public OperatingCountries getDestinationCountry() {
		return destinationCountry;
	}


	public void setDestinationCountry(OperatingCountries destinationCountry) {
		this.destinationCountry = destinationCountry;
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

	public String getRecipientBank() {
		return recipientBank;
	}

	public void setRecipientBank(String recipientBank) {
		this.recipientBank = recipientBank;
	}
}
