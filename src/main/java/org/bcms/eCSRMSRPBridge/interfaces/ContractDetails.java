/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   6 Aug 2024
 */
package org.bcms.eCSRMSRPBridge.interfaces;

import java.util.UUID;

/**
 * 
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
