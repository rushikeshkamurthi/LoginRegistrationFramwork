package marcos.Login.LoginFramwork.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true) 
public interface ContactRepository extends JpaRepository<Contact, Long>{
	

}
