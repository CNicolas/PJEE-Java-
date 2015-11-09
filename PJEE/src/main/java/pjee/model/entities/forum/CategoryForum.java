package pjee.model.entities.forum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pjee.model.entities.Profile;

@Entity
@Table(name = "category_forum")
public class CategoryForum {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "posts_number")
	private int postsNumber;

	@ManyToOne
	@JoinColumn(name = "theme_id")
	private ThemeForum theme;

	@OneToOne
	@JoinColumn(name = "profile")
	private Profile profile;

	// --------------------------------------------------------------------------
	// GENERATED
	// --------------------------------------------------------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPostsNumber() {
		return postsNumber;
	}

	public void setPostsNumber(int postsNumber) {
		this.postsNumber = postsNumber;
	}

	public ThemeForum getTheme() {
		return theme;
	}

	public void setTheme(ThemeForum theme) {
		this.theme = theme;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + postsNumber;
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
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
		CategoryForum other = (CategoryForum) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (postsNumber != other.postsNumber) {
			return false;
		}
		if (profile == null) {
			if (other.profile != null) {
				return false;
			}
		} else if (!profile.equals(other.profile)) {
			return false;
		}
		if (theme == null) {
			if (other.theme != null) {
				return false;
			}
		} else if (!theme.equals(other.theme)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryForum [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", postsNumber=");
		builder.append(postsNumber);
		builder.append(", theme=");
		builder.append(theme);
		builder.append(", profile=");
		builder.append(profile);
		builder.append("]");
		return builder.toString();
	}

}
