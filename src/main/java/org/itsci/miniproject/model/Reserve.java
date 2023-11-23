package org.itsci.miniproject.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reserve")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Reserve {
	
	@Id
	@Column(length = 10)
	private String reserveId;
	
	@Column(nullable = false, length = 6)
	private LocalDateTime reserveDate;
	
	@Column(nullable = false, length = 45)
	private String status;
	
	@Column(nullable = false)
	private double totalPrice;
	
	@Column(nullable = false, length = 6)
	private LocalDateTime payDate;
	
	@Column(nullable = false, length = 10)
	private String receiptId;

	@Column(nullable = false, length =45)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime ScheduleDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barberId")
    private Barber barber;
	
	@ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "customerId")
    private Customer customer;
}
