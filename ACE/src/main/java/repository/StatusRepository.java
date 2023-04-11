package repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Status;
@Repository
public interface StatusRepository extends Base<Status, Integer> {

	@Query("SELECT s FROM Status s where s.status =:status")
	public Status findStatusByName(@Param("status")String status);
}