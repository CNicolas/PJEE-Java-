package pjee.model.dto.forum;

import pjee.model.dto.ProfileDto;
import pjee.model.entities.forum.CategoryForum;

public class CategoryForumDto {
	private long			id;
	private String			name;
	private int				postsNumber;
	private ThemeForumDto	theme;
	private ProfileDto		profile;
	private int				newPostsNumber;

	/**
	 * Constructor.
	 * 
	 * @param category
	 *            entity
	 */
	public CategoryForumDto(CategoryForum category) {
		super();
		this.id = category.getId();
		this.name = category.getName();
		this.postsNumber = category.getPostsNumber();
		this.theme = new ThemeForumDto(category.getTheme());
		this.profile = new ProfileDto(category.getProfile());
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

	public ThemeForumDto getTheme() {
		return theme;
	}

	public void setTheme(ThemeForumDto theme) {
		this.theme = theme;
	}

	public ProfileDto getProfile() {
		return profile;
	}

	public void setProfile(ProfileDto profile) {
		this.profile = profile;
	}

	public int getNewPostsNumber() {
		return newPostsNumber;
	}

	public void setNewPostsNumber(int newPostsNumber) {
		this.newPostsNumber = newPostsNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + newPostsNumber;
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
		CategoryForumDto other = (CategoryForumDto) obj;
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
		if (newPostsNumber != other.newPostsNumber) {
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
		builder.append("CategoryForumDto [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", postsNumber=");
		builder.append(postsNumber);
		builder.append(", theme=");
		builder.append(theme);
		builder.append(", profile=");
		builder.append(profile);
		builder.append(", newPostsNumber=");
		builder.append(newPostsNumber);
		builder.append("]");
		return builder.toString();
	}

}
