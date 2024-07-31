/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.repositories;

import java.util.Optional;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 */
public interface ContractRepository extends JpaRepository<Contract, UUID> {
	Optional<Contract> findByContractNoIgnoreCase(String contract);
	
	Optional<Contract> findByTenderNoIgnoreCase(String tender);
}
