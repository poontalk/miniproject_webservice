package org.itsci.miniproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Set;



@Entity
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Authority {
	@Id
	@Column(nullable = false,length = 11)
	private Integer authorityId;
	
	@Column(nullable = false,length = 45)
	private String role;

	@JsonBackReference
	@ManyToMany(mappedBy = "authorities",fetch = FetchType.LAZY)
	Set<Login> logins;

	public Authority(Integer authorityId, String role) {
		this.authorityId = authorityId;
		this.role = role;
	}
}
