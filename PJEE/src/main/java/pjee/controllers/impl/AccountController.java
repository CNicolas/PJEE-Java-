package pjee.controllers.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pjee.controllers.PjeeController;
import pjee.exception.PjeeDatabaseException;
import pjee.exception.PjeeFileException;
import pjee.model.entities.Profile;

/**
 * Controlleur de la page de succès de connexion.
 */
@Controller
public class AccountController extends PjeeController {

	private final Logger logger;

	public AccountController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Page menu compte.
	 * 
	 * @param model
	 *            le model.
	 * @return la page.
	 */
	@RequestMapping(value = { ACCOUNT_PAGE, ROOT_ACCOUNT_PAGE }, method = RequestMethod.GET)
	public String account(Model model) {
		Profile profile = context.getProfile();

		// Setting Model
		model.addAttribute("profile", profile);
		model.addAttribute("image", forumHelper.getImageFromProfile(profile));

		return ACCOUNT_PAGE;
	}

	/**
	 * Maj des infos.
	 * 
	 * @param model
	 *            le model
	 * @param newProfile
	 *            l'objet du formulaire
	 * @return la page
	 */
	@RequestMapping(value = { ACCOUNT_PAGE, ROOT_ACCOUNT_PAGE }, method = RequestMethod.POST)
	public String updateAccount(Model model, @ModelAttribute Profile newProfile,
			@RequestParam("file") MultipartFile file) {
		try {
			// Check
			checkProfileByEmail(newProfile);
			checkFile(newProfile, file);

			// DatabaseAccess
			profileRepository.save(newProfile);
			context.setProfile(newProfile);

			// Setting Model
			model.addAttribute("updated", true);

			// Logging
			logger.info("Mise à jour des informations du profil");
		} catch (PjeeDatabaseException | PjeeFileException e) {
			// Setting Model
			model.addAttribute("fail", true);
			model.addAttribute("profile", context.getProfile());
			model.addAttribute("failMessage", e.getMessage());

			// Logging
			logger.error("Echec de la mise à jour des informations", e);
		}
		return "redirect:" + ROOT_ACCOUNT_PAGE;
	}

	// --------------------------------------------------------------------------
	// PRIVATE METHODS (CHECK)
	// --------------------------------------------------------------------------

	/**
	 * Check if a profile already exists with the given email.
	 * 
	 * @param newProfile
	 *            the profile to check with
	 * @throws PjeeDatabaseException
	 *             if a matching has been found
	 */
	private void checkProfileByEmail(Profile newProfile) throws PjeeDatabaseException {
		Profile check = profileRepository.findByEmail(newProfile.getEmail());
		String currentUsername = context.getUsername();
		if (check != null && !check.getUser().getUsername().equals(currentUsername)) {
			// Logging
			logger.warn("Email déjà utilisé");
			throw new PjeeDatabaseException("L'addresse email appartient déjà à quelqu'un d'autre");
		}
	}

	/**
	 * Check if the file is not empty, set the image of the profile
	 * 
	 * @param newProfile
	 *            the profile
	 * @param file
	 *            the file
	 * @throws PjeeFileException
	 *             when the getBytes() method throws an IOException
	 */
	private void checkFile(Profile newProfile, MultipartFile file) throws PjeeFileException {
		if (!file.isEmpty()) {
			String fileName = new String(Base64.getEncoder().encode(newProfile.getEmail().getBytes()));
			newProfile.setImage(fileName);
			try {
				String currentDir = System.getProperty("user.dir");
				byte[] bytes = file.getBytes();
				File fic = new File(fileName);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fic));
				stream.write(bytes);
				stream.close();

				try {
					Path fileToMovePath = Paths.get(fic.getAbsolutePath());
					Path targetDir = Paths.get(currentDir + "/img/");
					File dir = new File(targetDir.toString());
					dir.mkdirs();
					Files.delete(targetDir.resolve(fileToMovePath.getFileName()));
					Files.move(fileToMovePath, targetDir.resolve(fileToMovePath.getFileName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException ioe) {
				throw new PjeeFileException("Something failed when getting bytes : " + ioe.getMessage());
			}
		}
	}
}
