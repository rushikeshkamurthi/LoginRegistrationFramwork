package marcos.Login.LoginFramwork.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
	
	@Autowired
	public ContactRepository contactRepository;
	
	
	public Contact saveContact(Contact contact) {
		contactRepository.save(contact);
		
		return contact ;
	}
	

}
