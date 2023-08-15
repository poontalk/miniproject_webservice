package org.itsci.miniproject.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "barber")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Barber extends User{
	
	@Column(nullable = false, length = 10)
	private String barberId;
	
	@Column(nullable = false, length = 45)
	private String barberStatus;
	
	
}
