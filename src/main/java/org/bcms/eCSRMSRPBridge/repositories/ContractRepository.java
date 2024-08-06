/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.entities.Contract;
import org.bcms.eCSRMSRPBridge.interfaces.ContractDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 */
public interface ContractRepository extends JpaRepository<Contract, UUID> {
	Optional<Contract> findByContractNoIgnoreCase(String contract);
	
	Optional<Contract> findByTenderNoIgnoreCase(String tender);
	
	List<Contract> findBySupplierId(UUID id);
	
	@Query(nativeQuery = true, value = "SELECT c.id,c.tender_no,c.contract_no, "
			+ "c.description,c.category,c.state,c.title,"
			+ "	c.total_contract_value,c.termination_notice_period,c.contract_term,"
			+ "	c.is_active,c.is_approved,t.name as contract_type"
			+ "	FROM public.ecsrm_cont_register c"
			+ "	inner join public.ecsrm_cont_type t on t.id=c.contract_type_id"
			+ "	where c.supplier_id=:id")
	List<ContractDetails> findContractsBySupplierId(@Param("id") UUID id);
}
