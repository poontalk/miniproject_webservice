package org.itsci.miniproject.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "owner")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Owner extends User{
	
	@Column(nullable = false, length = 10)
	private String ownerId;
	
	@Column(nullable = false, length = 100)
	private String shopName;
	
	@Column(nullable = false, length = 6)
	private LocalDateTime openTime;
	
	@Column(nullable = false, length = 6)
	private LocalDateTime closeTime;
	
	@Column(nullable = false, length = 6)
	private LocalDateTime dayOff;
	
	@Column(nullable = false, length = 6)
	private LocalDateTime weekend;
	
}
