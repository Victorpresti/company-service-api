/*
--+
    | Project ACCOUNT SERVICE API - Java Class File : 1.0.0 Data: 10/06/2018
    | Copyright(c) by ProfitCode IT Solutions
    |
    | All rights reserved.
    |
    | This software is confidential and proprietary information of
    | ProfitCode IT Solutions ("Confidential Information").
    | You shall not disclose such Confidential Information and shall 
    | use it only in accordance with the terms of the license agreement
    | you entered with ProfitCode IT Solutions.
 +--
 */
package br.com.solutis.piloto.service;

import br.com.solutis.piloto.entity.User;
import br.com.solutis.piloto.repository.IUserRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ProfitCode IT Solutions
 * 
 * Implements business rules of the user.
 *
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Log4j2
public class UserServiceImpl implements UserService {

	/**
	 * Set data repositories.
	 */
	@Autowired
	private IUserRepository iUserRepository;

	@Override
	public User create (User user) throws Exception {
		
		log.info("Creating a new user.");
		
		try {
			return iUserRepository.save(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<User> read (Long id) throws Exception {
		
		log.info("Reading user by identification.");
		
		try {
			return iUserRepository.findById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public User update (Long id, User detail) throws Exception {
		
		log.info("Updating user by identification and user details.");
		
		try {
			Optional<User> user = iUserRepository.findById(id);
			if (user.isPresent()) {				
				return iUserRepository.save(new User(id, detail.getName(), detail.getEmail()));
			}
			return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean delete (Long id) throws Exception {
		
		log.info("Deleting user by identification.");
		
		try {
			Optional<User> user = iUserRepository.findById(id);
			
			if (user.isPresent()) {				
				iUserRepository.deleteById(id);
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
