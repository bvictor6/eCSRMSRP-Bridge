/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.dto.SupplierDTO;
import org.bcms.eCSRMSRPBridge.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 */
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
	
	List<Supplier> findByNameIgnoreCase(String name);
	
	List<Supplier> findByNameContainingIgnoreCase(String name);
	
	@Query("SELECT new org.bcms.eCSRMSRPBridge.dto.SupplierDTO(id, name) "
			+ "FROM Supplier")
	List<SupplierDTO> findAllSuppliers();

}
