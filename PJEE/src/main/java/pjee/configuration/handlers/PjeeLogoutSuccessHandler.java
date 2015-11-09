package pjee.configuration.handlers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import pjee.helper.PjeeConstants;
import pjee.helper.PjeeContext;
import pjee.model.entities.Profile;
import pjee.model.repositories.ProfileRepository;

@Component
public class PjeeLogoutSuccessHandler implements LogoutSuccessHandler, PjeeConstants {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	protected PjeeContext context;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		updateLastLogin();

		String refererUrl = request.getHeader("Referer");

		if (requiresAuthentication(refererUrl, authentication)) {
			response.sendRedirect(ROOT_PAGE);
		} else {
			response.sendRedirect(refererUrl);
		}
	}

	private boolean requiresAuthentication(String refererUrl, Authentication authentication) {
		String[] params = refererUrl.split("/");
		String returnTo = params[params.length - 1];
		return Arrays.asList(AUTHENTICATED_PAGES).contains(returnTo);
	}

	/**
	 * Set new last login date.
	 */
	private void updateLastLogin() {
		Profile profile = context.getProfile();

		Timestamp actual = new Timestamp(new Date().getTime());
		profile.setLastLoginDate(actual);

		profileRepository.save(profile);

		context.setProfile(null);
	}
}
