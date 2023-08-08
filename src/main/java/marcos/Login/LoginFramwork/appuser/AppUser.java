package marcos.Login.LoginFramwork.appuser;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails{
	
	
	@SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence", // Make sure this matches the name of your sequence in the database
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence" // Make sure this matches the name of your generator
    )
    private Long id; // Corrected the field name to lowercase "id"
	private String lastname;
	private String firstname;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING) 
	private AppUserRole appUserRole; 
	private boolean enabled = false;
	private boolean locked = false;
	
	public AppUser() {
        // Default constructor
    }

	public boolean isLocked() {
		return locked;
	}


	public void setLocked(boolean isLocked) {
		this.locked = isLocked;
	}


	public void setEnabled(boolean isEnabled) {
		this.enabled = isEnabled;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public AppUser(String username, String name, String email, String password, AppUserRole appUserRole) {
		super();
		this.lastname = username;
		this.firstname = name;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name() );
		return Collections.singletonList(authority); 
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public AppUserRole getAppUserRole() {
		return appUserRole;
	}


	public void setAppUserRole(AppUserRole appUserRole) {
		this.appUserRole = appUserRole;
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
		return enabled;
	}


}
