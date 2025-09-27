package hcmute.vn.model;

public class User 
{
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String resetCode;
    private java.util.Date resetExpiry;
    private String fullname;
    private String phone;
    private String imageUrl;

    // Constructors
    public User() {}
    public User(String username, String password, String email) 
    {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(int id, String username, String password, String email, String role, String resetCode, java.util.Date resetExpiry, String fullname, String phone, String imageUrl) 
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.resetCode = resetCode;
        this.resetExpiry = resetExpiry;
        this.fullname = fullname;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public String getResetCode() {
		return resetCode;
	}
	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}
	public java.util.Date getResetExpiry() {
		return resetExpiry;
	}
	public void setResetExpiry(java.util.Date resetExpiry) {
		this.resetExpiry = resetExpiry;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}