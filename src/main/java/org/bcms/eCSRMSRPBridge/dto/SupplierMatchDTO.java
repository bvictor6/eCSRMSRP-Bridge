/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   3 Aug 2024
 */
package org.bcms.eCSRMSRPBridge.dto;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
public class SupplierMatchDTO {
	private UUID ecsrmId;
	private Double jwSimilarity;

}
