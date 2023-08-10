package marcos.Login.LoginFramwork.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marcos.Login.LoginFramwork.appuser.AppUser;
import marcos.Login.LoginFramwork.appuser.AppUserService;

@RestController
@RequestMapping(path = "contact")
public class ContactController {
	
	
	private final ContactService contactService;
	private final AppUserService appUserService;

    @Autowired
    public ContactController(ContactService contactService,AppUserService appUserService) {
        this.contactService = contactService;
        this.appUserService = appUserService;
    }

    @PostMapping(path = "save")
    String saveContact(@RequestBody ContactRequest contactRequest) {
        // Assuming you have a way to get the currently logged-in user
       AppUser currentUser = appUserService.getCurrentUser();
        
        Contact contact = new Contact(
            contactRequest.getName(),
            contactRequest.getNumber(),
            contactRequest.getAddress(),
            contactRequest.getIsFav(),
            contactRequest.getImage()
        );
        contact.setUser(currentUser); // Set the user for the contact
        contactService.saveContact(contact);
        return "Contact saved successfully";
    }

    @GetMapping(path = "get/{id}")
    Contact getContact(@PathVariable Long id) {
        AppUser currentUser = appUserService.getCurrentUser();// Get the currently authenticated user
        Contact contact = contactService.getContactById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));

        if (!contact.getUser().equals(currentUser)) {
            throw new RuntimeException("Unauthorized access to contact");
        }

        return contact;
    }

    
    @DeleteMapping(path = "delete/{id}")
    String deleteContact(@PathVariable Long id) {
        AppUser currentUser = appUserService.getCurrentUser(); // Get the currently authenticated user
        Contact contact = contactService.getContactById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));

        if (!contact.getUser().equals(currentUser)) {
            throw new RuntimeException("Unauthorized access to contact");
        }

        contactService.deleteContactById(id);
        return "Contact deleted successfully";
    }

//    @GetMapping(path = "delete/{id}")
//    String deleteContact(@PathVariable Long id) {
//        contactService.deleteContactById(id);
//        return "Contact deleted successfully";
//    }
    
    @GetMapping(path = "get/all")
    public List<Contact> getAllContacts(){
    	return  contactService.getAllContactsForCurrentUser();
    }
    
    @PutMapping(path = "update/{id}")
    String updateContact(@PathVariable Long id, @RequestBody ContactRequest contactRequest) {
        AppUser currentUser = appUserService.getCurrentUser(); // Get the currently authenticated user
        
        Contact contact = contactService.getContactById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));

        if (!contact.getUser().equals(currentUser)) {
            throw new RuntimeException("Unauthorized access to contact");
        }

        // Update the contact's fields based on the request
        contact.setName(contactRequest.getName());
        contact.setNumber(contactRequest.getNumber());
        contact.setAddress(contactRequest.getAddress());
        contact.setIsFav(contactRequest.getIsFav());
        contact.setImage(contactRequest.getImage());

        contactService.saveContact(contact); // Save the updated contact

        return "Contact updated successfully";
    }

}
