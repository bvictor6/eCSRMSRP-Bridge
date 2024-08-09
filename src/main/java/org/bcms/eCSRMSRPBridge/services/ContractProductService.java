/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   9 Aug 2024
 */
package org.bcms.eCSRMSRPBridge.services;

import java.util.List;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.interfaces.ContractProductDetails;
import org.bcms.eCSRMSRPBridge.repositories.ContractProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service
public class ContractProductService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ContractProductRepository contractProductRepository;
	
	/**
	 * Get contract product's for a particular contract
	 */
	public List<ContractProductDetails> findContractProductsByContractId(UUID contractID) {
		return contractProductRepository.findContractProductsByContractId(contractID);
	}

}
