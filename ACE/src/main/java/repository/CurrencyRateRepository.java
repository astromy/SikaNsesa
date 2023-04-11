package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.CurrencyRates;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRates, Long>{
	
	@Query("SELECT cr FROM CurrencyRates cr")
	public List<CurrencyRates> findAllCurrencyRates();
	
	@Query("SELECT cr FROM CurrencyRates cr where cr.fromCurrency =:fromCurrency AND cr.toCurrency =:toCurrency AND Date(cr.ratedate)=CURDATE()")
	public CurrencyRates findConversionRate(@Param("fromCurrency")String fromCurrency,@Param("toCurrency")String toCurrency);
	
	/*@Query("SELECT c FROM Clients c where c.registrationCode =:code")
	public OperatingCountries findClientByCode(@Param("code")String code);
	
	@Query("SELECT c FROM Clients c where c.clientContact  LIKE CONCAT('%', :phone,'%')")
	public OperatingCountries findClientByPhone(@Param("phone")String phone);
	
	@Query("SELECT c FROM Clients c where c.clientToken =:token")
	public OperatingCountries findClientByToken(@Param ("token")String token);

	@Query("SELECT c.clientToken FROM Clients c where c.clientName LIKE '%null%' OR c.clientName LIKE '%Anonymous%' OR c.clientContact  LIKE  '%null%' OR CHAR_LENGTH(c.clientContact)>10")
	public ArrayList<String> findAllUncompletedAccounts();*/
	
/*	@Query("SELECT c FROM Clients c where c.shop =:shop")
	public List<Clients> findDispatchByShop(@Param ("shop")Shop  shop);
	
	@Query("Select c From Clients c where c.riderUserName=:userName and c.riderUserPass=:password")
	public Clients findByUsernamePassword (@Param("userName")String userName, @Param("password")String password);
	*/
}