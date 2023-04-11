package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "commissionrates", schema = "ace")
public class CommissionRates {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idRates;
	@Column(name = "rate")
	private Double rates;
	@Column(name = "sendingBank")
	private Double sendingBank;
	@Column(name = "recievingBank")
	private Double recievingBank;

	public CommissionRates() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommissionRates(Long idRates, Double rates, Double sendingBank, Double recievingBank) {
		super();
		this.idRates = idRates;
		this.rates = rates;
		this.sendingBank = sendingBank;
		this.recievingBank = recievingBank;
	}

	public Long getIdRates() {
		return idRates;
	}

	public void setIdRates(Long idRates) {
		this.idRates = idRates;
	}

	public Double getRates() {
		return rates;
	}

	public void setRates(Double rates) {
		this.rates = rates;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getSendingBank() {
		return sendingBank;
	}

	public void setSendingBank(Double sendingBank) {
		this.sendingBank = sendingBank;
	}

	public Double getRecievingBank() {
		return recievingBank;
	}

	public void setRecievingBank(Double recievingBank) {
		this.recievingBank = recievingBank;
	}


}
