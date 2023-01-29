

$("#btn-file").click(function () {
	$("#ip-file").click();
});

var showModal = false;

$("#btn-ok").click(function () {
	showModal = true;
});
$("#btn-cancel").click(function () {
	showModal = false;
});

$(document).ready(function () {
	$.validator.setDefaults({
		submitHandler: function (form) {
			if (showModal === false) {
				$("#confirmModal").modal();
			} else {
				form.submit();
				showModal = false;
			}
		}
	});
	$.validator.addMethod("checkallowemail", function (value) {
		return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value);
	});
	$.validator.addMethod("isPhone", function (value) {
		return /^0[0-9]{9,14}$/.test(value);
	})
	$("#create-form").validate({
		errorClass: "invalid-feedback",
		successClass: "valid-feedback",
		rules: {
			fullName: {
				required: true,
			},
			phone: {
				required: true,
				isPhone: true,
			},
			email: {
				required: true,
				checkallowemail: true,
			},
			dob: {
				required: true,
			}

		},
		messages: {
			fullName: {
				required: "Enter name please!"
			},
			phone: {
				required: "Enter phone please!",
				isPhone: "Phone is incorrect!"
			},
			email: {
				required: "Enter email please!",
				checkallowemail: "Email is incorrect!",
			},
			dob: {
				required: "Enter date of birth please!",
			}
		},
		highlight: function (element, errorClass, validClass) {
			$(element).addClass("is-invalid").remove("is-valid");
		},
		unhighlight: function (element, errorClass, validClass) {
			$(element).addClass("is-valid").removeClass("is-invalid");
		},
		errorElement: "em",
		errorPlacement: function (error, element) {
			error.addClass("invalid-feedback");
			error.insertAfter(element);
		},
	});
});

$("#ip-file").change(
	function () {
		let nameFile = this.files[0].name.substr(
			this.files[0].name.lastIndexOf(".") + 1).toLowerCase();
		if (this.files.length == 1 &&
			this.files[0].size / 1024 / 1024 < 11 && (nameFile === "pdf" || nameFile === "docx")) {
			$("#u-cv").html(this.files[0].name);
			$('#i-cv').attr('style',
				'display: block !important; font-size:25px;');
		} else {
			$("#ip-file").val("");
			$("#u-cv").html("");
			$('#i-cv').attr('style', 'display: none !important');
		}
	});

