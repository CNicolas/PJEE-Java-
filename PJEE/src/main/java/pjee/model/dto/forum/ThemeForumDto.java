package pjee.model.dto.forum;

import pjee.model.entities.forum.ThemeForum;

public class ThemeForumDto {

	private long	id;
	private String	name;
	private int		categoriesNumber;
	private int		postsNumber;

	private int newPostsNumber;

	/**
	 * Constructor.
	 * 
	 * @param themeForum
	 *            entity
	 */
	public ThemeForumDto(ThemeForum themeForum) {
		this.id = themeForum.getId();
		this.name = themeForum.getName();
		this.categoriesNumber = themeForum.getCategoriesNumber();
		this.postsNumber = themeForum.getPostsNumber();
	}

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

	public int getCategoriesNumber() {
		return categoriesNumber;
	}

	public void setCategoriesNumber(int categoriesNumber) {
		this.categoriesNumber = categoriesNumber;
	}

	public int getPostsNumber() {
		return postsNumber;
	}

	public void setPostsNumber(int postsNumber) {
		this.postsNumber = postsNumber;
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
		result = prime * result + categoriesNumber;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + newPostsNumber;
		result = prime * result + postsNumber;
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
		ThemeForumDto other = (ThemeForumDto) obj;
		if (categoriesNumber != other.categoriesNumber) {
			return false;
		}
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
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ThemeForumDto [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", categoriesNumber=");
		builder.append(categoriesNumber);
		builder.append(", postsNumber=");
		builder.append(postsNumber);
		builder.append(", newPostsNumber=");
		builder.append(newPostsNumber);
		builder.append("]");
		return builder.toString();
	}

}
