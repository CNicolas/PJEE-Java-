package pjee.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities {

	@Id
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "authority", nullable = false)
	private String authority;

	// ----------------------------------------------------------------
	// GENERATED
	// ----------------------------------------------------------------

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.authority == null) ? 0 : this.authority.hashCode());
		result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
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
		Authorities other = (Authorities) obj;
		if (this.authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!this.authority.equals(other.authority)) {
			return false;
		}
		if (this.username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!this.username.equals(other.username)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Authorities [username=");
		builder.append(this.username);
		builder.append(", authority=");
		builder.append(this.authority);
		builder.append("]");
		return builder.toString();
	}

}
