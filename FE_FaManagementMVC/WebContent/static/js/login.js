$(document).ready(function () {
    //check log out
    if ($("#logoutMessage").val() != "")
    alert("Logout success");

    //validate
    $("#loginForm").validate({
		errorClass: "invalid-feedback",
		successClass: "valid-feedback",
		rules: {
			"username": {
				required: true
			},
			"password": {
				required: true
			}	
		},
		messages: {
			"username": {
				required: "Username must be not empty!"
            },
			"password": {
				required: "Password must be not empty!"
			}

		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass("is-invalid").remove("is-valid");

		},
		unhighlight: function(element, errorClass, validClass) {

			$(element).addClass("is-valid").removeClass("is-invalid");

		},
		errorElement: "em",
		errorPlacement: function(error, element) {
			error.addClass("invalid-feedback");
			if (element.prop("type") === "radio") {
				error.insertAfter(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		},
	});
});