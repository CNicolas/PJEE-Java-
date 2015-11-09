package pjee.model.dto.forum;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import pjee.model.dto.ProfileDto;
import pjee.model.entities.forum.PostForum;

public class PostForumDto {
	private long				id;
	private String				title;
	private String				content;
	private Timestamp			timestamp;
	private String				type;
	private CategoryForumDto	category;
	private ProfileDto			profile;

	public PostForumDto(PostForum post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.timestamp = post.getTimestamp();
		this.type = post.getType();
		this.category = new CategoryForumDto(post.getCategory());
		this.profile = new ProfileDto(post.getProfile());
	}

	public String getDatestring() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy à HH:mm:ss");
		return sdf.format(this.timestamp);
	}

	public String getCondensedDatestring() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
		return sdf.format(this.timestamp);
	}

	/**
	 * Return the link to the category's page.
	 * 
	 * @return the category's page
	 */
	public String getHref() {
		long th = this.category.getTheme().getId();
		long ca = this.category.getId();
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
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CategoryForumDto getCategory() {
		return this.category;
	}

	public void setCategory(CategoryForumDto category) {
		this.category = category;
	}

	public ProfileDto getProfile() {
		return this.profile;
	}

	public void setProfile(ProfileDto profile) {
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
		result = prime * result + ((this.content == null) ? 0 : this.content.hashCode());
		result = prime * result + (int) (this.id ^ (this.id >>> 32));
		result = prime * result + ((this.profile == null) ? 0 : this.profile.hashCode());
		result = prime * result + ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
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
		PostForumDto other = (PostForumDto) obj;
		if (this.category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!this.category.equals(other.category)) {
			return false;
		}
		if (this.content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!this.content.equals(other.content)) {
			return false;
		}
		if (this.id != other.id) {
			return false;
		}
		if (this.profile == null) {
			if (other.profile != null) {
				return false;
			}
		} else if (!this.profile.equals(other.profile)) {
			return false;
		}
		if (this.timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!this.timestamp.equals(other.timestamp)) {
			return false;
		}
		if (this.title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!this.title.equals(other.title)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostDto [id=");
		builder.append(this.id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", timestamp=");
		builder.append(this.timestamp);
		builder.append(", type=");
		builder.append(this.type);
		builder.append(", category=");
		builder.append(this.category);
		builder.append(", profile=");
		builder.append(this.profile);
		builder.append("]");
		return builder.toString();
	}

}
