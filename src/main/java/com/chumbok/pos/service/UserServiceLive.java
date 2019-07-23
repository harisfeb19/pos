package com.chumbok.pos.service;

import com.chumbok.pos.entity.Role;
import com.chumbok.pos.entity.User;
import com.chumbok.pos.repository.RoleRepository;
import com.chumbok.pos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Transactional
@Service("userService")
public class UserServiceLive implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("");  
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);	
    }

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> list = userRepository.findAll();
		return list;
	}
}