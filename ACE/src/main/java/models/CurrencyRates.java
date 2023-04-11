package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currencyrates", schema = "ace")
public class CurrencyRates implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idCurrencyRates;
	@Column(name = "fromCurrency")
	private String fromCurrency;
	@Column(name = "toCurrency")
	private String toCurrency;
	@Column(name = "rate")
	private Double rate;
	@Column(name = "ratedate")
	private Date ratedate;

	public CurrencyRates() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrencyRates(Long idCurrencyRates, String fromCurrency, String toCurrency, Double rate, Date date) {
		super();
		this.idCurrencyRates = idCurrencyRates;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.rate = rate;
		this.ratedate = date;
	}

	public Long getIdCurrencyRates() {
		return idCurrencyRates;
	}

	public void setIdCurrencyRates(Long idCurrencyRates) {
		this.idCurrencyRates = idCurrencyRates;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Date getDate() {
		return ratedate;
	}

	public void setDate(Date date) {
		this.ratedate = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
