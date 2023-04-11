package models;

public class Authorization {

	private static final long serialVersionUID = 1L;

	private String authorization_code;
	private String card_type;
	private String last4;
	private String exp_month;
	private String exp_year;
	private String bin;
	private String bank;
	private String channel;
	private String signature;
	private Boolean reusable;
	private String country_code;
	
	public String getAuthorization_code() {
		return authorization_code;
	}
	public void setAuthorization_code(String authorization_code) {
		this.authorization_code = authorization_code;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getLast4() {
		return last4;
	}
	public void setLast4(String last4) {
		this.last4 = last4;
	}
	public String getExp_month() {
		return exp_month;
	}
	public void setExp_month(String exp_month) {
		this.exp_month = exp_month;
	}
	public String getExp_year() {
		return exp_year;
	}
	public void setExp_year(String exp_year) {
		this.exp_year = exp_year;
	}
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Boolean getReusable() {
		return reusable;
	}
	public void setReusable(Boolean reusable) {
		this.reusable = reusable;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Authorization(String authorization_code, String card_type, String last4, String exp_month, String exp_year,
			String bin, String bank, String channel, String signature, Boolean reusable, String country_code) {
		super();
		this.authorization_code = authorization_code;
		this.card_type = card_type;
		this.last4 = last4;
		this.exp_month = exp_month;
		this.exp_year = exp_year;
		this.bin = bin;
		this.bank = bank;
		this.channel = channel;
		this.signature = signature;
		this.reusable = reusable;
		this.country_code = country_code;
	}
	public Authorization() {
		super();
		// TODO Auto-generated constructor stub
	}
}
