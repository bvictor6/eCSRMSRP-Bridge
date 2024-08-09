/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   9 Aug 2024
 */
package org.bcms.eCSRMSRPBridge.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
@Table(name = "ecsrm_cont_product")
public class ContractProduct {
	@Id
	private UUID id;
	private UUID contractId;
	private UUID productId;
	private int quantity;
	private Double totalProductCost;
	private Double grandTotal;
	private int quantitySupplied;
	private int remainingQuantity;
	private UUID locProductId;
		
}
