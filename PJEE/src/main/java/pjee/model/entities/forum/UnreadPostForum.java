package pjee.model.entities.forum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pjee.model.entities.Profile;

@Entity
@Table(name = "unread_post_forum")
public class UnreadPostForum {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	@JoinColumn(name = "profile")
	private Profile profile;

	@OneToOne
	@JoinColumn(name = "post_forum")
	private PostForum postForum;

	// --------------------------------------------------------------------------
	// GENERATED
	// --------------------------------------------------------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public PostForum getPostForum() {
		return postForum;
	}

	public void setPostForum(PostForum postForum) {
		this.postForum = postForum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((postForum == null) ? 0 : postForum.hashCode());
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
		UnreadPostForum other = (UnreadPostForum) obj;
		if (id != other.id) {
			return false;
		}
		if (postForum == null) {
			if (other.postForum != null) {
				return false;
			}
		} else if (!postForum.equals(other.postForum)) {
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
		builder.append("UnreadPostForum [id=");
		builder.append(id);
		builder.append(", profile=");
		builder.append(profile);
		builder.append(", postForum=");
		builder.append(postForum);
		builder.append("]");
		return builder.toString();
	}

}
