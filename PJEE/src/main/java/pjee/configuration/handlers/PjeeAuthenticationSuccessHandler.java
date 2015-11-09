package pjee.configuration.handlers;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import pjee.helper.PjeeConstants;
import pjee.helper.forum.PjeeForumHelper;

@Component
public class PjeeAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements PjeeConstants {

	@Autowired
	private PjeeForumHelper forumHelper;

	protected Logger logger;

	public PjeeAuthenticationSuccessHandler() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		forumHelper.saveAllUnreadPosts();

		String targetUrl = ROOT_PAGE;
		String referer = request.getHeader("referer");
		if (referer.contains("connection")) {
			if (referer.contains("?ref=")) {
				String[] refs = referer.split("\\?");
				if (refs.length == 2) {
					targetUrl = URLDecoder.decode(refs[1].substring(4), "UTF-8");
				}
			}
		} else {
			targetUrl = referer;
		}
		logger.info("Returning to " + targetUrl);
		response.sendRedirect(targetUrl);
	}

}
