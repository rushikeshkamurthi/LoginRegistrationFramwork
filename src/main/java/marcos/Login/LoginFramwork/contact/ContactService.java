package marcos.Login.LoginFramwork.contact;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marcos.Login.LoginFramwork.appuser.AppUser;
import marcos.Login.LoginFramwork.appuser.AppUserService;

@Service
public class ContactService {
	
	@Autowired
	public ContactRepository contactRepository;
	public AppUserService appUserService;
	
    @Autowired
    public ContactService(ContactRepository contactRepository,AppUserService appUserService) {
        this.contactRepository = contactRepository;
        this.appUserService = appUserService;
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    public List<Contact> getAllContactsForCurrentUser() {
        AppUser currentUser = appUserService.getCurrentUser(); // Get the currently authenticated user
        return contactRepository.findByUser(currentUser);
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }

    // Implement other CRUD operations as needed

	

}
