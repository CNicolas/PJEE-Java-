package pjee.helper.enums;

public enum Role {

	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

	private final String name;

	private Role(String role) {
		this.name = role;
	}

	public Role getByName(String name) {
		if (!name.isEmpty()) {
			Role[] values = values();
			int len = values.length;
			for (int i = 0; i < len; i++) {
				if (values[i].getName().equals(name)) {
					return values[i];
				}
			}
		}
		return null;
	}

	public String getName() {
		return this.name;
	}

}
