package aceservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import models.Users;

@Component
@Service("authenticationService")
@Transactional(readOnly = true)
public class AuthenticationService implements UserDetailsService {
	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private UserService userAccountService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userAccountService.getUserByName(username);
		if (user == null) {}

		log.debug("Load UserService Executed");

		return new User(user.getUsername(), user.getUserPassword(), true, true, true, true,
				getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(Users user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		log.debug("User Roles Executed");
		return authorities;
	}

}
