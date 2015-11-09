function showNotifs() {
	var notifications = $(".notification");
	if (notifications.length) {
		var len = notifications.length;
		for (var i = 0; i < len; i++) {
			var jNotif = $(notifications[i]);
			var classNotif = notifications[i].className.match(/notification-([^\s]+)/);
			$.notify(jNotif.text(), {
				className : classNotif[1],
				style : "bootstrap",
				position : "top right"
			});
		}
	}
}

function remindFromUrl() {
	var uri = window.location.pathname.substr(1);
	var query = window.location.search.substring(1);
	var ref = uri;

	if (query) {
		ref += "?" + query;
	}
	if (ref != "") {
		$(".connectionLink").attr("href", "/connection?ref=" + encodeURIComponent(ref));
		$("#formConnection").attr("action", "/connection?ref=" + encodeURIComponent(ref));
	}
	// console.log("|"+uri+"|", "|"+query+"|", "|"+ref+"|");
}

function timeoutRedirect() {
	var timeout = $("#timeoutRedirect");
	if (timeout.length) {
		var time = timeout.attr("data-timeout");
		setInterval(function() {
			time -= 1000;
			if (time > 0) {
				$("span#timer").text(time / 1000);
			} else {
				window.document.location = timeout.attr("href");
			}
		}, 1000);
	}
}

$(document).ready(function() {
	// Show notifications if needed
	showNotifs();
	// Redirect to previous page of connection page
	remindFromUrl();
	// Redirect if timeout is required
	timeoutRedirect();

	// Initialize tooltips
	$('[data-toggle="tooltip"]').tooltip();

	// Set the most recent post active on index carousel
	$("#carousel-posts").find("div.carousel-inner").find("div.item").first().addClass("active");

	// Set the confirmationDeletePostModal validation url for the good post
	$(".delete-post").click(function(event) {
		var id = $(this).data("id");
		var url = $("#post-" + id).find("span.delete-post-url").text();
		$("#deletePostOk").attr("href", url)
	})

	// Set clickable row
	$(".clickable").click(function() {
		window.document.location = $(this).data("href");
	});

	// Login modal focus
	$('#confirmationLoginModal').on('shown.bs.modal', function() {
		$('#usernameInput').focus();
	})
});
