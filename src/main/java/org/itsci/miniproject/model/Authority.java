package org.itsci.miniproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;



@Entity
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Authority {
	@Id
	@Column(nullable = false,length = 11)
	private Integer authorityId;
	
	@Column(nullable = false,length = 45)
	private String role;

	@JsonIgnore
	@ManyToMany(mappedBy = "authorities")
	Set<Login> logins;

	public Authority(Integer authorityId, String role) {
		this.authorityId = authorityId;
		this.role = role;
	}
}
