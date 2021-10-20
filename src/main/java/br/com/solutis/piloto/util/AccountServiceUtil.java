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
package br.com.solutis.piloto.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

/**
 * @author ProfitCode IT Solutions
 *
 */
@Slf4j
public class AccountServiceUtil {

	public static String getDataBaseUsername () {
		
		Scanner scanner = null; 
		
		try {
			File file = new File ("/var/run/secrets/app/account/database/username/username");
			scanner = new Scanner(file);
			return scanner.nextLine();
		} catch (Exception e) {			
			log.error("Error try getting database username ".concat(e.getMessage()));
		} finally {
			scanner.close();
		}
		return null;		
	}
	
	public static String getDataBasePassword () {
		
		Scanner scanner = null; 
		
		try {
			File file = new File ("/var/run/secrets/app/account/database/password/password");
			scanner = new Scanner(file);
			return scanner.nextLine();
		} catch (Exception e) {			
			log.error("Error try getting database password ".concat(e.getMessage()));
		} finally {
			scanner.close();
		}
		return null;
	}

}
