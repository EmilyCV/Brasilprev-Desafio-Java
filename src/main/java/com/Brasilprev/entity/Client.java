package com.Brasilprev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;

@Entity(name = "T_CLIENT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client implements UserDetails {

//	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "client_cpf", unique = true, length = 11)
    @CPF
    private String cpf;

    @Column(name = "client_name", nullable = false, length = 60)
    private String name;

    @Column(name = "client_email", nullable = false, length = 60)
    @Email
    private String email;

    @Column(name = "client_password", nullable = false, length = 20)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//	@Override
//	public String toString() {
//		return "Client [cpf=" + cpf + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
//				+ address + ", roles=" + roles + "]";
//	}


}
