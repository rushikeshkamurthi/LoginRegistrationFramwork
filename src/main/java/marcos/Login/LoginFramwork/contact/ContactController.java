package marcos.Login.LoginFramwork.contact;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "contact")
public class ContactController {
	
	
	@GetMapping(path = "save")
	String saveContact() {
		return "You are able to access the secured end point";
	}
	
	@GetMapping(path = "delete")
	String deleteContact() {
		return "You are able to access the secured delete end point";
	}

}
