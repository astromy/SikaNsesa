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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recipienttransaction", schema = "ace")
public class RecipeintTransaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRecipientTransaction")
	private Long idRecipientTransaction;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "sender")
	private String sender;
	@Column(name = "dateTime")
	private LocalDateTime dateTime;

	@ManyToOne
	@JoinColumn(name = "recipientCountry")
	@JsonIgnore
	private OperatingCountries recipientCountry;

	@ManyToOne
	@JoinColumn(name = "status")
	@JsonIgnore
	private Status status;

	@ManyToOne
	@JoinColumn(name = "transactionId")
	@JsonIgnore
	private Transaction transaction;

	@ManyToOne
	@JoinColumn(name = "sendertransactionBank")
	@JsonIgnore
	private Bank bank;

	public RecipeintTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeintTransaction(Long idRecipientTransaction, Double amount, String sender, LocalDateTime dateTime,
			OperatingCountries recipientCountry, Status status, Transaction transaction, Bank bank) {
		super();
		this.idRecipientTransaction = idRecipientTransaction;
		this.amount = amount;
		this.sender = sender;
		this.dateTime = dateTime;
		this.recipientCountry = recipientCountry;
		this.status = status;
		this.transaction = transaction;
		this.bank = bank;
	}



	public Long getIdRecipientTransaction() {
		return idRecipientTransaction;
	}

	public void setIdRecipientTransaction(Long idRecipientTransaction) {
		this.idRecipientTransaction = idRecipientTransaction;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime localDateTime) {
		this.dateTime = localDateTime;
	}

	public OperatingCountries getRecipientCountry() {
		return recipientCountry;
	}

	public void setRecipientCountry(OperatingCountries recipientCountry) {
		this.recipientCountry = recipientCountry;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
}
