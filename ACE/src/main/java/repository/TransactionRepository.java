package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Transaction;

@Repository
public interface TransactionRepository extends Base<Transaction, Long>{
	//public AdminUser findUserByUsername(String adminUserName);
	
	@Query("SELECT t FROM Transaction t")
	public List<Transaction> findAllTransaction();
	
	@Query("SELECT t FROM Transaction t where t.senderCurrency =:senderCurrency")
	public List<Transaction> findItemPricesByItem(@Param("senderCurrency")String senderCurrency);
	
	@Query("SELECT t FROM Transaction t where t.sender =:recieptNum AND t.senderCurrency=:senderCurrency AND t.senderAmount=:senderAmount")
	public Transaction findTransactionByReciept(@Param("recieptNum")String recieptNum,@Param("senderCurrency")String senderCurrency,@Param("senderAmount")Double senderAmount);
	
/*	@Query("SELECT '{', o.item.itemName,'-', o.variant,';', o.price,';',o.quantity, '}' FROM ItemPrices o where o.item =:item")
	public List<Object> findPricesByItem(@Param("item")Item item);
	
	@Query("SELECT '{', o.item.itemName,'-', o.variant,';', o.price,';',o.quantity, '}' FROM ItemPrices o where o.shop =:shop")
	public List<Object> findPricesByShop(@Param("shop")Shop shop);
	
	@Query("SELECT  ip.iditemPrices, ip.price, ip.quantity,ip.variant,ip.item.idItem,ip.shop.shopId FROM ItemPrices ip")
	public List<Object> findAllItemPrices();
	
	@Query("SELECT ip.iditemPrices, ip.price, ip.quantity,ip.variant,ip.item.idItem,ip.shop.shopId FROM ItemPrices ip where ip.iditemPrices NOT IN :ids")
	public List<Object> findAllItemPrices(@Param("ids")List<Integer> ids);*/
	
}