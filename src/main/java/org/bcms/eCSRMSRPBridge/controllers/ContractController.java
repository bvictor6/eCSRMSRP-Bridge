/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.controllers;

import java.util.Optional;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.classes.Constants;
import org.bcms.eCSRMSRPBridge.entities.Contract;
import org.bcms.eCSRMSRPBridge.services.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping(path = Constants.API_PATH_V1 + "/contracts")
@CrossOrigin(origins = "http://127.0.0.1:8089")
public class ContractController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ContractService contractService;
	
	@PostMapping(path = "/contract")
	public ResponseEntity<?> findByContractNo(@RequestBody Contract request){
		Optional<Contract> contract = contractService.getByContractNo(request.getContractNo());
		logger.info("Search contract by " + request.getContractNo());
		if(contract.isPresent()) {
			return new ResponseEntity<>(contract.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Contract not found with contract id " + request.getContractNo(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/tender")
	public ResponseEntity<?> findByTenderNo(@RequestBody Contract request){
Optional<Contract> contract = contractService.getByTenderNo(request.getTenderNo());
		
		if(contract.isPresent()) {
			return new ResponseEntity<>(contract.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Contract not found with tender id " + request.getTenderNo(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/supplier/{id}")
	public ResponseEntity<?> findSupplierContracts(@PathVariable("id") String id){
		logger.info(String.valueOf(id));
		contractService.findSupplierContracts(UUID.fromString(id));
		return new ResponseEntity<>(contractService.findSupplierContracts(UUID.fromString(id)), HttpStatus.OK);
	}

}
