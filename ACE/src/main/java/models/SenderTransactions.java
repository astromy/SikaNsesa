package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.api.client.util.DateTime;

@Entity
@Table(name = "senderTransaction", schema = "ace")
public class SenderTransactions implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSenderTransaction")
	private Long idSenderTransaction;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "sender")
	private String sender;
	@Column(name = "dateTime")
	private DateTime dateTime;

	@ManyToOne
	@JoinColumn(name = "senderCountry")
	@JsonIgnore
	private OperatingCountries senderCountry;

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

	public SenderTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SenderTransactions(Long idSenderTransaction, Double amount, String sender, DateTime dateTime,
			OperatingCountries senderCountry, Status status, Transaction transaction) {
		super();
		this.idSenderTransaction = idSenderTransaction;
		this.amount = amount;
		this.sender = sender;
		this.dateTime = dateTime;
		this.senderCountry = senderCountry;
		this.status = status;
		this.transaction = transaction;
	}

	public Long getIdSenderTransaction() {
		return idSenderTransaction;
	}

	public void setIdSenderTransaction(Long idSenderTransaction) {
		this.idSenderTransaction = idSenderTransaction;
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

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public OperatingCountries getSenderCountry() {
		return senderCountry;
	}

	public void setSenderCountry(OperatingCountries senderCountry) {
		this.senderCountry = senderCountry;
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
}
