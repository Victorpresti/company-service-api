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

import br.com.solutis.piloto.entity.Company;
import br.com.solutis.piloto.repository.ICompanyRepository;
import lombok.extern.log4j.Log4j2;
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
public class CompanyServiceImpl implements CompanyService {

	/**
	 * Set data repositories.
	 */
	@Autowired
	private ICompanyRepository iCompanyRepository;

	@Override
	public Company create (Company company) throws Exception {
		
		log.info("Creating a new Company.");
		
		try {
			return iCompanyRepository.save(company);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Company> read (String cnpj) throws Exception {
		
		log.info("Reading company by identification.");
		
		try {
			return iCompanyRepository.findById(cnpj);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Company update (String cnpj, Company detail) throws Exception {
		
		log.info("Updating company by identification and user details.");
		
		try {
			Optional<Company> company = iCompanyRepository.findById(cnpj);
			if (company.isPresent()) {
				return iCompanyRepository.save(new Company(cnpj, detail.getReasonSocial(), detail.getFantasyName(), detail.getCadastralSituation(), detail.getCadastralSituationDate()));
			}
			return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean delete (String cnpj) throws Exception {
		
		log.info("Deleting company by identification.");
		
		try {
			Optional<Company> user = iCompanyRepository.findById(cnpj);
			
			if (user.isPresent()) {				
				iCompanyRepository.deleteById(cnpj);
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
