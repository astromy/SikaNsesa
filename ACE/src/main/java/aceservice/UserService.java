package aceservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.Users;
import repository.UserRepository;


@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository UserRepository;
	
	public List<Users> getAllUsers() {
		return (List<Users>) UserRepository.findAll();
	}
	public Users getUserById(int Userid){
		return UserRepository.findOne(Userid);
	}
	public Users save(Users User){
		return UserRepository.save(User);
	}
	public Users getUserByName(String Username){
		Users User = UserRepository.findUserByUsername(Username);
		if(User == null){
			return null;
		}
		return User;
	}
	
	public String getPassword(String Username){
		return UserRepository.findUserPassword(Username);
	}

	
	/*public List<Users> findAllEnabled(){
		return UserRepository.findAllEnabled();
	}*/
	
	public boolean isExist(Integer id, String name){
		List<Users> UserUnique = UserRepository.findUserNamesUnique(id);
		List<String> names = new LinkedList<String>();

		for (Users u : (UserUnique)) {
			names.add(u.getUsername().toUpperCase());
		}
		return names.contains(name.toUpperCase());
	}


}
