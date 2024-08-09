/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   9 Aug 2024
 */
package org.bcms.eCSRMSRPBridge.repositories;

import java.util.List;
import java.util.UUID;

import org.bcms.eCSRMSRPBridge.entities.ContractProduct;
import org.bcms.eCSRMSRPBridge.interfaces.ContractProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 
 */
public interface ContractProductRepository extends JpaRepository<ContractProduct, UUID> {
	
	@Query(nativeQuery = true,  value = "SELECT c.id,c.contract_id,c.product_id,c.quantity,c.total_product_cost,"
			+ " c.grand_total,c.quantity_supplied,c.remaining_quantity,c.unit,c.loc_product_id,p.code,p.description,"
			+ " p.batch_no,p.category ,p.sku_code,p.sku_description,p.sku_type"
			+ "	FROM public.ecsrm_cont_product c "
			+ "	INNER JOIN public.ecsrm_prod_register p ON p.id=c.product_id "
			+ " WHERE c.contract_id=:id "
			+ " ORDER BY p.code ASC LIMIT 100")
	List<ContractProductDetails> findContractProductsByContractId(@Param("id") UUID id);

}
