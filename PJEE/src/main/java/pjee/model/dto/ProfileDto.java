package pjee.model.dto;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import pjee.helper.forum.PjeeForumHelper;
import pjee.model.entities.Profile;
import pjee.model.entities.Users;

public class ProfileDto {
	
	@Autowired
	private PjeeForumHelper forumHelper;

	private long		id;
	private String		firstname;
	private String		lastname;
	private String		email;
	private int			age;
	private Timestamp	lastLoginDate;
	private String		image;
	private Users		user;
	private String		imageContent;

	public ProfileDto(Profile profile) {
		super();
		forumHelper = new PjeeForumHelper();
		
		this.id = profile.getId();
		this.firstname = profile.getFirstname();
		this.lastname = profile.getLastname();
		this.email = profile.getEmail();
		this.age = profile.getAge();
		this.lastLoginDate = profile.getLastLoginDate();
		this.image = profile.getImage();
		this.user = profile.getUser();
		if (this.image != null) {
			this.imageContent = forumHelper.getImageFromProfile(profile);
		}
	}

	// --------------------------------------------------------------------------
	// GENERATED
	// --------------------------------------------------------------------------

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Timestamp getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getImageContent() {
		return this.imageContent;
	}

	public void setImageContent(String imageContent) {
		this.imageContent = imageContent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.age;
		result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result + ((this.firstname == null) ? 0 : this.firstname.hashCode());
		result = prime * result + (int) (this.id ^ (this.id >>> 32));
		result = prime * result + ((this.image == null) ? 0 : this.image.hashCode());
		result = prime * result + ((this.lastLoginDate == null) ? 0 : this.lastLoginDate.hashCode());
		result = prime * result + ((this.lastname == null) ? 0 : this.lastname.hashCode());
		result = prime * result + ((this.user == null) ? 0 : this.user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProfileDto other = (ProfileDto) obj;
		if (this.age != other.age) {
			return false;
		}
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.firstname == null) {
			if (other.firstname != null) {
				return false;
			}
		} else if (!this.firstname.equals(other.firstname)) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		if (this.image == null) {
			if (other.image != null) {
				return false;
			}
		} else if (!this.image.equals(other.image)) {
			return false;
		}
		if (this.lastLoginDate == null) {
			if (other.lastLoginDate != null) {
				return false;
			}
		} else if (!this.lastLoginDate.equals(other.lastLoginDate)) {
			return false;
		}
		if (this.lastname == null) {
			if (other.lastname != null) {
				return false;
			}
		} else if (!this.lastname.equals(other.lastname)) {
			return false;
		}
		if (this.user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!this.user.equals(other.user)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProfileDto [id=");
		builder.append(this.id);
		builder.append(", firstname=");
		builder.append(this.firstname);
		builder.append(", lastname=");
		builder.append(this.lastname);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", age=");
		builder.append(this.age);
		builder.append(", lastLoginDate=");
		builder.append(this.lastLoginDate);
		builder.append(", image=");
		builder.append(this.image);
		builder.append(", user=");
		builder.append(this.user);
		builder.append("]");
		return builder.toString();
	}

}
