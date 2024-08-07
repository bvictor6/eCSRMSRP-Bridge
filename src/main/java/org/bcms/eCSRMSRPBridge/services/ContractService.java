/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.entities.Contract;
import org.bcms.eCSRMSRPBridge.interfaces.ContractDetails;
import org.bcms.eCSRMSRPBridge.repositories.ContractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class ContractService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ContractRepository contractRepository;
	
	public Optional<Contract> getByContractNo(String contractNo){
		return contractRepository.findByContractNoIgnoreCase(contractNo);
	}
	
	public Optional<Contract> getByTenderNo(String tenderNo){
		return contractRepository.findByTenderNoIgnoreCase(tenderNo);
	}
	
	public List<Contract> getBysupplierId(UUID id){
		return contractRepository.findBySupplierId(id);
	}
	
	/*
	 * Get all contracts with details for a particular supplier using a custom native query
	 */
	public List<ContractDetails> findSupplierContracts(UUID id) {
		List<ContractDetails> contracts = contractRepository.findContractsBySupplierId(id);
		
		return contracts;
	}
	
	/**
	 * Get contract details for a particular contract belonging to a given supplier
	 */
	public ContractDetails findSupplierContract(UUID contractId, UUID supplierId) {
		return contractRepository.findContractByIdBySupplierId(contractId, supplierId);
	}

}
