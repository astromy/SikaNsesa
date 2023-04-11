package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import models.CommissionRates;

@Repository
public interface CommisionRateRepository extends Base<CommissionRates, Long>{
	
	@Query("SELECT cr FROM CommissionRates cr")
	public List<CommissionRates> findCurrentRate();
	
	//public AdminUser findUserByUsername(String adminUserName);
	
	/*@Query("SELECT d FROM Dispatch d where d.iddispatch =:dispatchId")
	public Transaction findDispatchById(@Param("dispatchId")Integer dispatchId);
	
	@Query("SELECT d FROM Dispatch d where d.riderUserName =:user")
	public Transaction findDispatchByUsername(@Param("user")String user);
	
	@Query("SELECT riderUserPass FROM Dispatch d where d.riderUserName =:pass")
	public String findDispatchPass(@Param ("pass")String pass);
	
	@Query("SELECT d FROM Dispatch d where d.shop =:shop")
	public List<Transaction> findDispatchByShop(@Param ("shop")Shop  shop);
	
	@Query("Select d From Dispatch d where d.riderUserName=:userName and d.riderUserPass=:password")
	public Transaction findByUsernamePassword (@Param("userName")String userName, @Param("password")String password);
	*/
}