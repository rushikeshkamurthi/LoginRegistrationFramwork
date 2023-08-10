package marcos.Login.LoginFramwork.contact;

public class ContactRequest {
	
	private String name;
	private String Address;
	private Boolean isFav = false;
	private String image;
	private String number;

	public ContactRequest(String name, String address, Boolean isFav, String image, String number) {
		super();
		this.name = name;
		Address = address;
		this.isFav = isFav;
		this.image = image;
		this.number = number;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
