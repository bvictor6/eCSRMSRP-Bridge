/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   9 Aug 2024
 */
package org.bcms.eCSRMSRPBridge.interfaces;

import java.util.UUID;

/**
 * Used to do a native SQL query for contract products :: findContractProductsByContractId(@Param("id") UUID id);
 * the fields must match the database table fields in the query!
 */
public interface ContractProductDetails {
	UUID getId();
	UUID getContractId();
	UUID getProductId();
	int getQuantity();
	Double getTotalProductCost();
	Double getGrandTotal();
	int getQuantitySupplied();
	int getRemainingQuantity();
	String getUnit();
	UUID getLocProductId();
	String getCode();
	String getDescription();
	String getBatchNo();
	String getCategory();
	String getSkuCode();
	String getSkuDescription();
	String getSkuType();
	

}
