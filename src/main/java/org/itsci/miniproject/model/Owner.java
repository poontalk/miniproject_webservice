package org.itsci.miniproject.model;

import java.time.LocalDateTime;
import java.util.List;

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
public class Owner {
	@Id
	@Column(nullable = false, length = 10)
	private String ownerId;
	
	@Column(nullable = false, length = 100)
	private String shopName;
	
	@Column(nullable = false, length = 6)
	private LocalDateTime openTime;
	
	@Column(nullable = false, length = 6)
	private LocalDateTime closeTime;
	
	@Column(length = 6)
	private LocalDateTime dayOff;
	
	@Column(length = 10)
	private String weekend;

	@OneToOne(cascade = CascadeType.ALL,optional = false)
	@JoinColumn(name = "userId")
	private User user;

}
