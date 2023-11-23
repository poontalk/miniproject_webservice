package org.itsci.miniproject.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservedetail")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ReserveDetail {
	
	@Id
	@Column(length = 10)
	private String reservedetailId;
	
	@Column(nullable = false)
	private double sumPrice;
	
	@Column(nullable = false, length = 40)
	private LocalDateTime scheduleTime;
	
	@Column(nullable = false, length = 10)
	private Integer sumTimeSpend;
	
	@ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "reserveId")
    private Reserve reserve;
	
	
	@ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "serviceId")
    private Service service;
	
}
