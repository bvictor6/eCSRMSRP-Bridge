/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.dto.SupplierDTO;
import org.bcms.eCSRMSRPBridge.entities.Supplier;
import org.bcms.eCSRMSRPBridge.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class SupplierService {
	@Autowired SupplierRepository supplierRepository;
	
	public Optional<Supplier> getSupplierById(UUID id){
		return supplierRepository.findById(id);
	}
	
	public List<Supplier> getsupplierByName(String name){
		return supplierRepository.findByNameIgnoreCase(name);
	}
	
	public List<Supplier> getSupplierByNameContaining(String name){
		return supplierRepository.findByNameContainingIgnoreCase(name);
	}
	
	public List<SupplierDTO> getAllSuppliers(){
		return supplierRepository.findAllSuppliers();
	}

}
