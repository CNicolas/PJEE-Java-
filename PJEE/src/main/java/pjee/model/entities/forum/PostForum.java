package pjee.model.entities.forum;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
@Table(name = "post_forum")
public class PostForum {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	@Column(name = "type")
	private String type;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryForum category;

	@OneToOne
	@JoinColumn(name = "profile")
	private Profile profile;

	public String getDatestring() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy à HH:mm:ss");
		return sdf.format(getTimestamp());
	}

	public String getCondensedDatestring() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
		return sdf.format(getTimestamp());
	}

	/**
	 * Return the link to the category's page.
	 * 
	 * @return the category's page
	 */
	public String getHref() {
		long th = this.getCategory().getTheme().getId();
		long ca = this.getCategory().getId();
		StringBuilder builder = new StringBuilder();
		builder.append("t=");
		builder.append(th);
		builder.append("&c=");
		builder.append(ca);
		return builder.toString();
	}

	/**
	 * Returns the 70 first chars of the content.
	 * 
	 * @return the extract
	 */
	public String getContentExtract() {
		if (content.length() > 100) {
			return content.substring(0, 100) + " <strong>[...]</strong>";
		} else {
			return content;
		}
	}

	// --------------------------------------------------------------------------
	// GENERATED
	// --------------------------------------------------------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CategoryForum getCategory() {
		return category;
	}

	public void setCategory(CategoryForum category) {
		this.category = category;
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
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		PostForum other = (PostForum) obj;
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (profile == null) {
			if (other.profile != null) {
				return false;
			}
		} else if (!profile.equals(other.profile)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostForum [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", type=");
		builder.append(type);
		builder.append(", category=");
		builder.append(category);
		builder.append(", profile=");
		builder.append(profile);
		builder.append("]");
		return builder.toString();
	}

}
