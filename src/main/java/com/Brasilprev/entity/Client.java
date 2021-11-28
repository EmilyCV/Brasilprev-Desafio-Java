package com.Brasilprev.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="T_CLIENT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client implements UserDetails {
	
//	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="client_cpf", unique = true, length = 11)
	private String cpf;
	
	@Column(name="client_name", nullable = false, length = 60)
	private String name;
	
	@Column(name = "client_email")
    private String email;
	
	@Column(name = "client_password")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
	private Address address;
	
//	@Transient
//	private String token;
	
	@ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

//	@Override
//	public String toString() {
//		return "Client [cpf=" + cpf + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
//				+ address + ", roles=" + roles + "]";
//	}

	

}
