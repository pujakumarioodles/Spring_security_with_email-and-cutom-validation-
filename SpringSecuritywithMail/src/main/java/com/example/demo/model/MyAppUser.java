package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.sun.istack.NotNull;




@Entity
@Table(name = "users")
public class MyAppUser {

	@Id
	@NotNull
	private  String username;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@Email
	@NotNull
	private String email;
	@NotNull
	private String number;
	@NotNull
	private String password;
	 
	private int verified;
	private String resettoken;
	
	public int getVerified() {
		return verified;
	}





	public void setVerified(int verified) {
		this.verified = verified;
	}





	


	public String getResettoken() {
		return resettoken;
	}





	public void setResettoken(String resettoken) {
		this.resettoken = resettoken;
	}








	private boolean enabled=true;
	
	@ElementCollection

	@Column(name="AUTHORITY")
	@CollectionTable(name = "AUTHORITIES",joinColumns = {@JoinColumn(name="USERNAME")})
	private Set<String> authorities;
	
	
	
	public MyAppUser() {
		authorities= new HashSet<>();
		authorities.add("USER");
	}
	
	



	/*public MyAppUser( String username,  String firstName,  String lastName,
			  String email,  String password, boolean enabled) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.enabled=enabled;
		authorities= new HashSet<>();
		authorities.add("USER");
	}*/


	public MyAppUser(String username, String firstName, String lastName, String email, String number,
			String password, boolean enabled) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.number = number;
		this.password = password;
		this.enabled = enabled;
		this.authorities = new HashSet<>();
		authorities.add("USER");
		
	}


	public String getNumber() {
		return number;
	}








	public void setNumber(String number) {
		this.number = number;
	}








	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Set<String> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
	
	
	
	
	
}
