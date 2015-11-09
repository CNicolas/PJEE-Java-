package pjee.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pjee.model.entities.Profile;
import pjee.model.repositories.ProfileRepository;

@Service
public class PjeeContext {

	@Autowired
	private ProfileRepository profileRepository;

	private Profile profile;

	/**
	 * Le nom de l'utilisateur connecté.
	 * 
	 * @return le nom
	 */
	public String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			if (isConnected()) {
				return authentication.getName();
			}
		}
		return null;
	}

	/**
	 * Utilisateur connecté ou anonyme.
	 * 
	 * @return vrai : connecté
	 */
	public boolean isConnected() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			String name = auth.getName();
			return !"anonymousUser".equals(name);
		}
		return false;
	}

	/**
	 * Returns the profile of the connected user.
	 * 
	 * @return the profile
	 */
	public Profile getProfile() {
		if (profile == null) {
			String username = getUsername();
			if (username != null) {
				profile = profileRepository.findOneByUser_username(username);
			} else {
				return null;
			}
		}
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
