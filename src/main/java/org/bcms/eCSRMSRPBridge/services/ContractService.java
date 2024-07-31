/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.services;

import java.util.Optional;

import org.bcms.eCSRMSRPBridge.entities.Contract;
import org.bcms.eCSRMSRPBridge.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class ContractService {
	@Autowired ContractRepository contractRepository;
	
	public Optional<Contract> getByContractNo(String contractNo){
		return contractRepository.findByContractNoIgnoreCase(contractNo);
	}
	
	public Optional<Contract> getByTenderNo(String tenderNo){
		return contractRepository.findByTenderNoIgnoreCase(tenderNo);
	}

}
