package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Bank;
import models.OperatingCountries;
@Repository
public interface BankRepository extends Base<Bank, Integer> {

	@Query("SELECT b FROM Bank b where b.bankName =:name")
	public Bank findBankByName(@Param("name")String name);
	@Query("SELECT b FROM Bank b where b.bankCountry =:country")
	public List<Bank> findBankByCountry(@Param("country")OperatingCountries country);
	@Query("SELECT b FROM Bank b where b.bankBalance =:balance AND b.bankCountry=:country")
	public List<Bank> findBankByBalanceAndCountry(@Param("balance")Double balance, @Param("country")OperatingCountries country);
}