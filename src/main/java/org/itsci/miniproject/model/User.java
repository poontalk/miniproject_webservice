package org.itsci.miniproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {
	
	@Id
	@Column(length = 10)
	private String userId;
	
	@Column(nullable = false , length = 45)
	private String firstName;
	
	@Column(nullable = false ,length = 45)
	private String lastName;
	
	@Column(nullable = false ,length = 255)
	private String address;
	
	@Column(nullable = false ,length = 45)
	private String email;
	
	@Column(nullable = false ,length = 10)
	private String mobileNo;
	
	@OneToOne(cascade = CascadeType.ALL,optional=false)
	@JoinColumn(name = "loginId")
	private Login login;

	public User(String userId, String firstName, String lastName, String address, String email, String mobileNo) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.mobileNo = mobileNo;
	}
	public User(String userId) {
		this.userId = userId;
	}

}
