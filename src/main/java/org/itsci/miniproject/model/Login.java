package org.itsci.miniproject.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "login")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Login {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loginId;	

	@Column(nullable = false,length = 60)
	private String username;
	@Column(nullable = false,length = 60)
	private String password;
	
	@ManyToMany
	@JoinTable(
	  name = "authority_login", 
	  joinColumns = @JoinColumn(name = "loginId"), 
	  inverseJoinColumns = @JoinColumn(name = "authorityId"))
	private Set<Authority> authorities;

	public Login( String username, String password) {

		this.username = username;
		this.password = password;
	}
}
