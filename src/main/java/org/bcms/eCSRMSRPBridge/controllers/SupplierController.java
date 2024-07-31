/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.classes.NameParam;
import org.bcms.eCSRMSRPBridge.entities.Contract;
import org.bcms.eCSRMSRPBridge.entities.Supplier;
import org.bcms.eCSRMSRPBridge.services.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * 
 */
@Controller
@RequestMapping(path = "/api/suppliers")
public class SupplierController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired SupplierService supplierService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") UUID id){
		Optional<Supplier> supplier = supplierService.getSupplierById(id);
		
		if(supplier.isPresent()) {
			return new  ResponseEntity<>(supplier.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Supplier not found with id " + id, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/exact")
	public ResponseEntity<?> findByName(@RequestBody NameParam supplier){
		logger.info("find supplier name " + supplier.getName());
		return new ResponseEntity<>(supplierService.getsupplierByName(supplier.getName()), HttpStatus.OK);
	}
	
	@GetMapping(path = "/partial")
	public ResponseEntity<?> findByContainsName(@RequestBody Supplier supplier){
		return new ResponseEntity<>(supplierService.getSupplierByNameContaining(supplier.getName()),HttpStatus.OK);
	}

}
