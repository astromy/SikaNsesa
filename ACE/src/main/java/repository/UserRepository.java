package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Users;

@Repository
public interface UserRepository extends Base<Users, Integer>{
	@Query("Select u FROM Users u where u.username=:userName")
	public Users findUserByUsername(@Param("userName")String userName);

	@Query("Select u FROM Users u where u.usersStatus=1")
	public List<Users> findAllEnabled();
	
	@Query("SELECT u FROM Users u where u.userId !=:id")
	public List<Users> findUserNamesUnique(@Param("id")Integer id);
	
	@Query("Select userPassword From Users where username=:userName")
	public String findUserPassword (@Param("userName")String userName);
	
	@Query("Select userPassword From Users where username=:userName")
	public Users findByUsernamePassword (@Param("userName")String userName);
}