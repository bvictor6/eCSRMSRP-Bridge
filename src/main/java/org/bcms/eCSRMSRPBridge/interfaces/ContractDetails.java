/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   6 Aug 2024
 */
package org.bcms.eCSRMSRPBridge.interfaces;

import java.util.UUID;

/**
 *  Used to do a native SQL query for supplier contracts :: findContractsBySupplierId(@Param("id") UUID id);
 *  the fields must match the database table fields in the query!
 */
public interface ContractDetails {
	UUID getId();
	String getTenderNo();
	String getContractNo();
	String getDescription();
	String getCategory();
	String getState();
	String getTitle();
	Double getTotalContractValue();
	Long getTerminationNoticePeriod();
	Long getContractTerm();
	Boolean getIsActive();
	Boolean  getIsApproved();
	String getContractType();

}
