package org.itsci.miniproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Customer extends User{
	
	@Column(nullable = false, length = 10)
	private String customerId;

	public Customer(String userId, String firstName, String lastName, String address, String email, String mobileNo, Login login, String customerId) {
		super(userId, firstName, lastName, address, email, mobileNo, login);
		this.customerId = customerId;
	}
}
