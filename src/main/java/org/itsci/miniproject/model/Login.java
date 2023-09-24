package org.itsci.miniproject.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "login")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Login {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loginId;	

	@Column(nullable = false,length = 60)
	private String username;
	@Column(nullable = false,length = 60)
	private String password;

	@JsonManagedReference
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	  name = "authority_login", 
	  joinColumns = @JoinColumn(name = "login_Id",referencedColumnName = "loginId"),
	  inverseJoinColumns = @JoinColumn(name = "authority_Id",referencedColumnName = "authorityId"))
	private Set<Authority> authorities = new HashSet<>();

	public Login( String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString(){
		return "CustomerDTO {" +
				"loginid=" + loginId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
