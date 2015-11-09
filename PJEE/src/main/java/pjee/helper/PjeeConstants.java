package pjee.helper;

/**
 * Routes.
 */
public interface PjeeConstants {
	// --------------------------------------------------------------------------
	// OTHER
	// --------------------------------------------------------------------------
	public final String	CSS_VENDOR_RESOURCES	= "/css/vendor/*";
	public final String	FONTS_RESOURCES			= "/css/fonts/*";
	public final String	CSS_RESOURCES			= "/css/*";
	public final String	JS_VENDOR_RESOURCES		= "/js/vendor/*";
	public final String	JS_RESOURCES			= "/js/*";
	public final String	IMG_RESOURCES			= "/img/*";

	// --------------------------------------------------------------------------
	// PAGES NAME
	// --------------------------------------------------------------------------
	public final String	VOID_PAGE				= "";
	public final String	ROOT_PAGE				= "/";
	public final String	INDEX_PAGE				= "index";
	public final String	ROOT_INDEX_PAGE			= "/index";
	public final String	PERSONNES_PAGE			= "personnes";
	public final String	ROOT_PERSONNES_PAGE		= "/personnes";
	public final String	CONNECTION_PAGE			= "connection";
	public final String	ROOT_CONNECTION_PAGE	= "/connection";
	public final String	ACCOUNT_PAGE			= "account";
	public final String	ROOT_ACCOUNT_PAGE		= "/account";
	public final String	PROFILE_PAGE			= "profile";
	public final String	ROOT_PROFILE_PAGE		= "/profile";
	public final String	LOGOUT_PAGE				= "logout";
	public final String	ROOT_LOGOUT_PAGE		= "/logout";
	public final String	ERROR_PAGE				= "error";
	public final String	ROOT_ERROR_PAGE			= "/error";
	public final String	FORUM_PAGE				= "forum";
	public final String	ROOT_FORUM_PAGE			= "/forum";
	public final String	NEWPOST_PAGE			= "newpost";
	public final String	ROOT_NEWPOST_PAGE		= "/newpost";
	public final String	EDITPOST_PAGE			= "editpost";
	public final String	ROOT_EDITPOST_PAGE		= "/editpost";
	public final String	DELETEPOST_PAGE			= "deletepost";
	public final String	ROOT_DELETEPOST_PAGE	= "/deletepost";
	public final String	NEWCATEGORY_PAGE		= "newcategory";
	public final String	ROOT_NEWCATEGORY_PAGE	= "/newcategory";
	public final String	SEARCH_PAGE				= "results";
	public final String	ROOT_SEARCH_PAGE		= "/results";
	public final String	CREATEACCOUNT_PAGE		= "createaccount";
	public final String	ROOT_CREATEACCOUNT_PAGE	= "/createaccount";

	// --------------------------------------------------------------------------
	// PAGES GROUP
	// --------------------------------------------------------------------------
	public final String[]	ANONYMOUS_ROOT_PAGES		= { ROOT_PAGE, ROOT_INDEX_PAGE, ROOT_CONNECTION_PAGE,
			ROOT_PERSONNES_PAGE, ROOT_ERROR_PAGE, ROOT_FORUM_PAGE, ROOT_SEARCH_PAGE, CSS_VENDOR_RESOURCES,
			CSS_RESOURCES, JS_VENDOR_RESOURCES, JS_RESOURCES, IMG_RESOURCES, FONTS_RESOURCES, ROOT_PROFILE_PAGE,
			ROOT_CREATEACCOUNT_PAGE };
	public final String[]	ANONYMOUS_PAGES				= { VOID_PAGE, INDEX_PAGE, CONNECTION_PAGE, PERSONNES_PAGE,
			ERROR_PAGE, FORUM_PAGE, SEARCH_PAGE, PROFILE_PAGE, CREATEACCOUNT_PAGE };
	public final String[]	AUTHENTICATED_ROOT_PAGES	= { ROOT_ACCOUNT_PAGE, ROOT_PROFILE_PAGE, ROOT_LOGOUT_PAGE,
			ROOT_NEWPOST_PAGE, ROOT_EDITPOST_PAGE, ROOT_DELETEPOST_PAGE, ROOT_NEWCATEGORY_PAGE };
	public final String[]	AUTHENTICATED_PAGES			= { ACCOUNT_PAGE, PROFILE_PAGE, LOGOUT_PAGE, NEWPOST_PAGE,
			EDITPOST_PAGE, DELETEPOST_PAGE, NEWCATEGORY_PAGE };

	// --------------------------------------------------------------------------
	// TITLES
	// --------------------------------------------------------------------------
	public final String	DEFAULT_TITLE		= "Projet PJEE";
	public final String	ERROR_TITLE			= "Erreur PJEE";
	public final String	INDEX_TITLE			= "Accueil PJEE";
	public final String	CONNECTION_TITLE	= "Connexion PJEE";
	public final String	ACCOUNT_TITLE		= "Mon compte";
	public final String	FORUM_TITLE			= "Forum PJEE";
	public final String	NEWPOST_TITLE		= "Nouveau post PJEE";
	public final String	EDITPOST_TITLE		= "Modification d'un post PJEE";
	public final String	NEWCATEGORY_TITLE	= "Nouveau sujet PJEE";
	public final String	SEARCH_RESULT_TITLE	= "Résultats de la recherche PJEE";

}
