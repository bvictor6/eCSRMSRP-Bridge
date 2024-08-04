/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.classes.Constants;
import org.bcms.eCSRMSRPBridge.components.JaroSimilarity;
import org.bcms.eCSRMSRPBridge.components.JaroWinklerSimilarity;
import org.bcms.eCSRMSRPBridge.dto.SupplierDTO;
import org.bcms.eCSRMSRPBridge.dto.SupplierMatchDTO;
import org.bcms.eCSRMSRPBridge.entities.Supplier;
import org.bcms.eCSRMSRPBridge.services.SupplierService;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 */
@RestController
@RequestMapping(path = Constants.API_PATH_V1 + "/suppliers")
public class SupplierController {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired SupplierService supplierService;
	
	@Autowired JaroWinklerSimilarity jaroWinklerSimilarity;
	@Autowired JaroSimilarity jaroSimilarity;
	
	@GetMapping("/suppliers/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") UUID id){
		Optional<Supplier> supplier = supplierService.getSupplierById(id);
		
		if(supplier.isPresent()) {
			return new  ResponseEntity<>(supplier.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Supplier not found with id " + id, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/exact")
	public ResponseEntity<?> findByName(@RequestBody Supplier supplier){
		logger.info("find supplier exact match by name " + supplier.getName());
		return new ResponseEntity<>(supplierService.getsupplierByName(supplier.getName()), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:8089")
	@PostMapping(path = "/partial")
	public ResponseEntity<String> findByContainsName(@RequestBody Supplier supplier){
		logger.info("find supplier partial match by name " + supplier.getName());
		//String s1 = "Botswana Baylor Children's Clinical Centre of Excellence", s2 = "Botswana Baylor Childrens Centre"; 
		double jwSimilarity = 0.00;
		double jroSimilarity = 0.00;
		UUID ecsrmId =  null;
		//List<Supplier> suppliers = supplierService.getSupplierByNameContaining(supplier.getName());
		List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
		for(SupplierDTO s: suppliers) {
			//
			double jws = jaroWinklerSimilarity.jaro_Winkler(s.getName().toUpperCase(), supplier.getName().toUpperCase()) * 100; //multiply by 100 to make it a percentage
			logger.info(s.getName() + " matches " +supplier.getName() + " by " + Math.floor(jws * 100)/100 + " using jaroWinklerSimilarity.");
			double jrs = jaroSimilarity.jaro_distance(s.getName().toUpperCase(), supplier.getName().toUpperCase()) * 100; //multiply by 100 to make it a percentage
			logger.info(s.getName() + " matches " +supplier.getName() + " by " + Math.floor(jrs * 100)/100 + " using jaroSimilarity.");
			//check if the match is higher than what we already got and assign to it
			if((Math.floor(jws * 100)/100) > (Math.floor(jwSimilarity * 100)/100)) {
				jwSimilarity = jws;
				ecsrmId = s.getId();
			}
			if((Math.floor(jrs * 100)/100) > (Math.floor(jroSimilarity * 100)/100))
				jroSimilarity = jrs;
			logger.info("--------------------------------------------------------");
		}		
		
		System.out.println("Jaro-Winkler Similarity =" + jwSimilarity ); //truncate to 2 decimal places
		System.out.print("Jaro Similarity =" + jroSimilarity +"\n");
		//
		SupplierMatchDTO supplierMatchDTO = new SupplierMatchDTO();
		supplierMatchDTO.setEcsrmId(ecsrmId);
		supplierMatchDTO.setJwSimilarity(jwSimilarity);
		//
		ObjectMapper obj = new ObjectMapper();
		String jsonObject = null;
		//convert to json
		try {
			jsonObject = obj.writeValueAsString(supplierMatchDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return new ResponseEntity<String>(jsonObject,HttpStatus.OK);
	}

}
