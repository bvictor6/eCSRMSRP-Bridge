/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   1 Aug 2024
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
public class SupplierDTO {
	private UUID id;
	private String name;
	
	public SupplierDTO(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

}
