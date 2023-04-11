package repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.OperatingCountries;

@Repository
public interface OperatingCountryRepository extends Base<OperatingCountries, Long>{
	@Query("SELECT c FROM OperatingCountries c")
	public ArrayList<OperatingCountries> findAll();
	@Query("SELECT c.idCountry,c.countryName,c.countryCode,c.currencyName,c.currencySymbol FROM OperatingCountries c")
	public ArrayList<Object> findCountryList();
	@Query("SELECT distinct c.currencySymbol FROM OperatingCountries c")
	public ArrayList<String> findUniqueCountryList();
	@Query("SELECT c FROM OperatingCountries c where c.idCountry = :id")
	public OperatingCountries findById(@Param("id")Integer id);
	@Query("SELECT c FROM OperatingCountries c where c.countryName = :countryName")
	public OperatingCountries findByName(@Param("countryName")String countryName);
	/*String queryString="SELECT regionCountryIdfk FROM Region r inner join office o on r.regionId= o.office_regionIdfk inner join AdminStaff a on o.officeId=a.staff_officeIdfk inner join AdminUser u on a.staffId=u.adminuser_adminstafffk where u.adminUserId=?";
	@Query(queryString)
	public Country findCountrybyUserId(long u);*/
}