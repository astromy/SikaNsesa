package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import models.TransactionCommissions;

@Repository
public interface TransactionCommisionRepository extends Base<TransactionCommissions, Long>{
	//public AdminUser findUserByUsername(String adminUserName);
	
	@Query("SELECT tc FROM TransactionCommissions tc")
	public List<TransactionCommissions> findAllTransactionCommissions();
/*
	@Query("SELECT i FROM Item i where replace(replace(trim(i.itemName), '\r', ''), '\n', '') =:item")
	public Item findItemByName(@Param("item")String item);
	
	@Query("SELECT i FROM Item i where i.idItem NOT IN :ids")
	public List<Item> findAllItemText(@Param("ids")List<Integer> ids);
	
	@Query("SELECT  i.idItem, i.itemName, i.itemtag FROM Item i")
	public List<Object> findAllItemText();*/
	/*
	@Query("SELECT riderUserPass FROM Item i where i.riderUserName =:pass")
	public String findDispatchPass(@Param ("pass")String pass);
	
	@Query("SELECT i FROM Item i where i.shop =:shop")
	public List<Item> findDispatchByShop(@Param ("shop")Shop  shop);
	
	@Query("Select i From Item i where i.riderUserName=:userName and i.riderUserPass=:password")
	public Item findByUsernamePassword (@Param("userName")String userName, @Param("password")String password);
	*/
}