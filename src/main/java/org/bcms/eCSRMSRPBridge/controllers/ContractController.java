/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.controllers;

import java.util.Optional;

import org.bcms.eCSRMSRPBridge.entities.Contract;
import org.bcms.eCSRMSRPBridge.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 */
@Controller
@RequestMapping(path = "/api/contracts")
public class ContractController {
	@Autowired ContractService contractService;
	
	@GetMapping(path = "/{contractNo}")
	public ResponseEntity<?> findByContractNo(@PathVariable("contractNo") String contractNo){
		Optional<Contract> contract = contractService.getByContractNo(contractNo);
		
		if(contract.isPresent()) {
			return new ResponseEntity<>(contract.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Contract not found with contract id " + contractNo, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/{tenderNo}")
	public ResponseEntity<?> findByTenderNo(@PathVariable("tenderNo") String tenderNo){
Optional<Contract> contract = contractService.getByTenderNo(tenderNo);
		
		if(contract.isPresent()) {
			return new ResponseEntity<>(contract.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Contract not found with tender id " + tenderNo, HttpStatus.NOT_FOUND);
		}
	}

}
