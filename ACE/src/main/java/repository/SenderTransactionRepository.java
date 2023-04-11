package repository;

import org.springframework.stereotype.Repository;

import models.SenderTransactions;
@Repository
public interface SenderTransactionRepository extends Base<SenderTransactions, Integer> {

	/*@Query("SELECT g FROM Gender g where g.genderType =:genderType")
	public Status findStatusByName(@Param("genderType")String genderType);*/
}