package marcos.Login.LoginFramwork.contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import marcos.Login.LoginFramwork.appuser.AppUser;


@Repository
@Transactional(readOnly = true) 
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	List<Contact> findByUser(AppUser user);
}
