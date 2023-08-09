package marcos.Login.LoginFramwork.contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import marcos.Login.LoginFramwork.appuser.AppUser;

@Entity
public class Contact {

	@SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence", // Make sure this matches the name of your sequence in the database
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence" // Make sure this matches the name of your generator
    )
	private Long id;
	private String name;
	private String Address;
	private Boolean isFav = false;
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private AppUser user;
	
	
	public Contact() {
		// Default class
	}
	
	public Contact(String name, String address, Boolean isFav, String image) {
		super();
		this.name = name;
		Address = address;
		this.isFav = isFav;
		this.image = image;
	}

	public Long getId() {
		return id;
	}
	public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Boolean getIsFav() {
		return isFav;
	}

	public void setIsFav(Boolean isFav) {
		this.isFav = isFav;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", Address=" + Address + ", isFav=" + isFav + ", image=" + image
				+ "]";
	}
	
	
	
}
