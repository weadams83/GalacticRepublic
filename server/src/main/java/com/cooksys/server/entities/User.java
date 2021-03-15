package com.cooksys.server.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long groupId;
	
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	@CreationTimestamp
	private Timestamp created;
	
	@UpdateTimestamp
	private Timestamp updated;
	
	@Column(nullable = false)
	private Boolean isDeleted = false;
	
	private Long updatedBy;
	
	private Long rollId;

}
