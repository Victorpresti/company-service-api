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

/**
 * @author ProfitCode IT Solutions
 *
 */
@Entity
@Table(name = "Users", schema="account_service_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = -2096127641665459704L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUser", nullable=false, unique=true, insertable=true, updatable=true )
	private Long id;
	
	@Column(name="name",  length=250, nullable=false, unique=false)
	private String name;
	
	@Column(name="email", length=250, nullable=false, unique=false)
	private String email;

}