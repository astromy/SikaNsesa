package repository;

import org.springframework.stereotype.Repository;

import models.ClientSubscription;

@Repository
public interface ClientSubscriptionRepository extends Base<ClientSubscription, Long>{
	//public AdminUser findUserByUsername(String adminUserName);
	
	/*@Query("SELECT cs FROM ClientSubscription cs")
	public List<ClientSubscription> findAllClientSubscription();
	
	@Query("SELECT cs FROM ClientSubscription cs where cs.client =:client")
	public List<ClientSubscription> findClientSubscriptionByClient(@Param("client")OperatingCountries client);
	
	@Query("SELECT cs FROM ClientSubscription cs where cs.subscriptionAmount =:amount")
	public List<ClientSubscription> findClientSubscriptionByAmount(@Param("amount")Double amount);
	
	@Query("SELECT cs FROM ClientSubscription cs where cs.client =:client and Year(cs.subscriptionDate)=Year(Now())")
	public ClientSubscription findClientSubscriptionByClientAndDate(@Param("client")OperatingCountries client);
	//(TIMESTAMPDIFF(DAY,cs.subscriptionDate,CURDATE()))<=365 ")
	
	@Query("SELECT cs FROM ClientSubscription cs where cs.client =:client and Year(cs.subscriptionDate)=Year(Now())")
	public List<ClientSubscription> findClientSubscriptionsByClientAndDate(@Param("client")OperatingCountries client);
	//(TIMESTAMPDIFF(DAY,cs.subscriptionDate,CURDATE()))<=365 ")
	
	@Query("SELECT cs FROM ClientSubscription cs where YEAR(cs.subscriptionDate) = YEAR(Now())")
	public List<ClientSubscription> findAllClientSubscriptionInCurrentYear(@Param ("token")String token);
*/
/*	@Query("SELECT c.clientToken FROM Clients c where c.clientName LIKE '%null%' OR c.clientName LIKE '%Anonymous%' OR c.clientContact  LIKE  '%null%' OR CHAR_LENGTH(c.clientContact)>10")
	public ArrayList<String> findAllUncompletedAccounts();
	
	@Query("SELECT c FROM Clients c where c.shop =:shop")
	public List<Clients> findDispatchByShop(@Param ("shop")Shop  shop);
	
	@Query("Select c From Clients c where c.riderUserName=:userName and c.riderUserPass=:password")
	public Clients findByUsernamePassword (@Param("userName")String userName, @Param("password")String password);
	*/
}