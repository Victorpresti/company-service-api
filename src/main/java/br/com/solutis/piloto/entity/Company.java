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
package br.com.solutis.piloto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ProfitCode IT Solutions
 *
 */
@Entity
@Table(name = "Companies", schema="wisales_service_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

	private static final long serialVersionUID = -2096127641665459704L;

	@Id
	@Column(name="cnpj", length=14, nullable=false, unique=true)
	private String cnpj;

	@Column(name="reasonSocial", length=250, nullable=false, unique=false)
	private String reasonSocial;

	@Column(name="fantasyName", length=250, nullable=false, unique=false)
	private String fantasyName;

	@Column(name="cadastralSituation", nullable=false, unique=false)
	private Integer cadastralSituation;

	@Column(name="cadastralSituationDate", nullable=false, unique=false)
	private Date cadastralSituationDate;
}