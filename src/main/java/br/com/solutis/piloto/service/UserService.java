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
import java.util.Optional;

/**
 * @author ProfitCode IT Solutions
 * 
 * Service interface to User.
 *
 */
public interface UserService {

	User create (User user) throws Exception;

	Optional<User> read (Long id) throws Exception;

	User update (Long id, User detail) throws Exception;

	boolean delete (Long id) throws Exception;
}

