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
import org.bcms.eCSRMSRPBridge.services.ContractProductService;
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
	@Autowired ContractProductService contractProductService;
	
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
	
	/**
	 * Get contracts belonging to a particular supplier, given the supplier id
	 * @param id - supplier id
	 * @return
	 */
	@GetMapping(path = "/supplier/{id}")
	public ResponseEntity<?> findSupplierContracts(@PathVariable("id") String id){
		logger.info("Fetch contracts for supplier " + String.valueOf(id));
		return new ResponseEntity<>(contractService.findSupplierContracts(UUID.fromString(id)), HttpStatus.OK);
	}
	
	/**
	 * Get details for a particular contract belonging to a given supplier
	 * @param id - supplier id
	 * @param contract - contract id
	 * @return
	 */
	@GetMapping(path = "/supplier/{id}/{contract}")
	public ResponseEntity<?> findSupplierContract(@PathVariable("id") String id, @PathVariable("contract") String contract){
		logger.info("Fetch contract details request for contract - " + contract + " and supplier - " +id);
		
		return new ResponseEntity<>(contractService.findSupplierContract(UUID.fromString(contract), 
				UUID.fromString(id)), HttpStatus.OK);
	}
	
	@GetMapping(path = "/products/{id}")
	public ResponseEntity<?> findContractProducts(@PathVariable("id") String id){
		logger.info("Fetch products request for contract :: " + id);
		return new ResponseEntity<>(contractProductService.findContractProductsByContractId(UUID.fromString(id)), HttpStatus.OK);
	}

}
