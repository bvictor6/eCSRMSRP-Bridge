/**
 * Author: B. Victor
 * E-Mail: bvictor@ymail.com
 * Date:   31 Jul 2024
 */
package org.bcms.eCSRMSRPBridge.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Entity
@Table(name = "ecsrm_cont_register")
@Getter
@Setter
@NoArgsConstructor
public class Contract {
	@Id
	private UUID id;
	private String contractNo;
	private String tenderNo;
	private UUID supplierId;

}
