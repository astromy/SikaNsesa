package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import models.BankTransaction;

@Repository
public interface BankTransactionRepository extends Base<BankTransaction, Long>{
	//public AdminUser findUserByUsername(String adminUserName);
	
	@Query("SELECT t FROM BankTransaction t")
	public List<BankTransaction> findAllTransaction();
	
/*	@Query("SELECT t FROM BankTransaction t where t.senderCurrency =:senderCurrency")
	public List<BankTransaction> findItemPricesByItem(@Param("senderCurrency")String senderCurrency);
	
	@Query("SELECT t FROM BankTransaction t where t.sender =:recieptNum AND t.senderCurrency:=senderCurrency AND t.senderAmount:=senderAmount")
	public Double findBankBalance(@Param("recieptNum")String recieptNum,@Param("senderCurrency")String senderCurrency,@Param("recieptNum")Double senderAmount);
	
	@Query("SELECT '{', o.item.itemName,'-', o.variant,';', o.price,';',o.quantity, '}' FROM ItemPrices o where o.item =:item")
	public List<Object> findPricesByItem(@Param("item")Item item);
	
	@Query("SELECT '{', o.item.itemName,'-', o.variant,';', o.price,';',o.quantity, '}' FROM ItemPrices o where o.shop =:shop")
	public List<Object> findPricesByShop(@Param("shop")Shop shop);
	
	@Query("SELECT  ip.iditemPrices, ip.price, ip.quantity,ip.variant,ip.item.idItem,ip.shop.shopId FROM ItemPrices ip")
	public List<Object> findAllItemPrices();
	
	@Query("SELECT ip.iditemPrices, ip.price, ip.quantity,ip.variant,ip.item.idItem,ip.shop.shopId FROM ItemPrices ip where ip.iditemPrices NOT IN :ids")
	public List<Object> findAllItemPrices(@Param("ids")List<Integer> ids);*/
	
}