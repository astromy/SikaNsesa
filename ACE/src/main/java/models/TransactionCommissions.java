package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "transctionCommision", schema = "ace")
public class TransactionCommissions implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtransactionCommision")
	private Long idtransactionCommision;
	@Column(name = "commisionAmount")
	private Double commisionAmount;
	@Column(name = "dateTime")
	private LocalDateTime transCommisionDate;

	@ManyToOne
	@JoinColumn(name = "transaction")
	@JsonIgnore
	private Transaction transaction;

	@ManyToOne
	@JoinColumn(name = "senderCountry")
	@JsonIgnore
	private OperatingCountries senderCountry;

	public TransactionCommissions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionCommissions(Long idtransactionCommision, Double commisionAmount, LocalDateTime transCommisionDate,
			Transaction transaction, OperatingCountries senderCountry) {
		super();
		this.idtransactionCommision = idtransactionCommision;
		this.commisionAmount = commisionAmount;
		this.transCommisionDate = transCommisionDate;
		this.transaction = transaction;
		this.senderCountry = senderCountry;
	}

	public Long getIdtransactionCommision() {
		return idtransactionCommision;
	}

	public void setIdtransactionCommision(Long idtransactionCommision) {
		this.idtransactionCommision = idtransactionCommision;
	}

	public Double getCommisionAmount() {
		return commisionAmount;
	}

	public void setCommisionAmount(Double commisionAmount) {
		this.commisionAmount = commisionAmount;
	}

	public LocalDateTime getTransCommisionDate() {
		return transCommisionDate;
	}

	public void setTransCommisionDate(LocalDateTime localDateTime) {
		this.transCommisionDate = localDateTime;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public OperatingCountries getSenderCountry() {
		return senderCountry;
	}

	public void setSenderCountry(OperatingCountries senderCountry) {
		this.senderCountry = senderCountry;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
