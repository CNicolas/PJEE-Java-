package pjee.model.dto.forum;

public class FormCreateAccountDto {
	private String user;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private int age;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
//	public void initialize()
//	{
//		setUser("");
//		setPassword("");
//		setEmail("");
//		setFirstname("");
//		setLastname("");
//		setAge(0);
//	}
}
