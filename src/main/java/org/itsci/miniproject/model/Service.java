package org.itsci.miniproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Service {
	
	@Id
	@Column(length = 10)
	private String serviceId;
	
	@Column(nullable = false , length =45)
	private String serviceName;
	
	@Column(nullable = false, length = 20)
	private double price;
	
	@Column(nullable = false, length = 10) 
	private Integer timespend; 
}
