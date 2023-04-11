package models;

import java.time.LocalDate;

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
@Table(name = "client_subscription", schema = "kenten")
public class ClientSubscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idclient_subscription")
	private Long idClientSubscription;

	@Column(name = "client_subscriptionDate")
	private LocalDate subscriptionDate;
	
	@Column(name = "client_subscriptionAmount")
	private Double subscriptionAmount;
	
	@Column(name = "client_subscriptionType")
	private String subscriptionType;

	@ManyToOne
	@JoinColumn(name = "client_subscriptionClientFk")
	@JsonIgnore
	private OperatingCountries client;

	public Long getIdClientSubscription() {
		return idClientSubscription;
	}

	public void setIdClientSubscription(Long idClientSubscription) {
		this.idClientSubscription = idClientSubscription;
	}

	public LocalDate getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(LocalDate localDate) {
		this.subscriptionDate = localDate;
	}

	public Double getSubscriptionAmount() {
		return subscriptionAmount;
	}

	public void setSubscriptionAmount(Double subscriptionAmount) {
		this.subscriptionAmount = subscriptionAmount;
	}

	public OperatingCountries getClient() {
		return client;
	}

	public void setClient(OperatingCountries client) {
		this.client = client;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public ClientSubscription(Long idClientSubscription, LocalDate subscriptionDate, Double subscriptionAmount,
			String subscriptionType, OperatingCountries client) {
		super();
		this.idClientSubscription = idClientSubscription;
		this.subscriptionDate = subscriptionDate;
		this.subscriptionAmount = subscriptionAmount;
		this.subscriptionType = subscriptionType;
		this.client = client;
	}

	public ClientSubscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
