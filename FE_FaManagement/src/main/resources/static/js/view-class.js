$(document).ready(function() {
	$('[data-toggle="toggle"]').change(function() {
		$(this).parents().next('.hide').toggle();
	});

	if ($("#message").val() != "") {
		alert($("#message").val());
	}
	$("#changeStatus")
		.click(
			function(e) {
				e.preventDefault();
				$("#table-status").empty();
				let checkedInput = $('input[name="trainee"]:checked');
				for (let i = 0; i < checkedInput.length; i++) {
					let traineeId = $(checkedInput[i])
						.parent().parent()
						.find("td[name=traineeId]")
						.text();
					let traineeName = $(checkedInput[i])
						.parent().parent()
						.find("td[name=fullName]")
						.text();
					$("#table-status")
						.append(
							'<div class="row row-inf">'
							+ '<div class="col-3 final-cell"> <input name="traineeId" value="'
							+ traineeId
							+ '" disabled></div>'
							+ '<div class="col-6 final-cell">'
							+ traineeName
							+ '</div>'
							+ '<div class="col-3">'
							+ '<select name="statusInClass">'
							+ '<option value="Active">Active</option>'
							+ '<option value="Deferred">Deferred</option>'
							+ '<option value="Drop-out">Drop-out</option>'
							+ '</select></div></div>');
				}
				;
				$("#changeStatusModal").modal("show");
			});

	$('#btnImportTrainee').click(function(e) {
		$('#importTraineeModal').modal("show");
	});
});

function page(ele, page, classId) {
	let pagesize = $("#pageSize").val();
	$.ajax({
		type: "GET",
		url: '/class/view/' + classId + '/trainee',
		data: {
			offSet: page,
			pageSize: pagesize
		},
		success: function(data) {
			$('#traineeTbl').html(data);
		}
	});
}

function pageTraineeNotInClass(ele, page, classId) {
	let pagesize = $("#pageSize").val();
	$.ajax({
		type: "GET",
		url: '/class/view/' + classId + '/trainee-not-in',
		data: {
			offSet: page,
			pageSize: pagesize
		},
		success: function(data) {
			$('#traineeNotInClassTbl').html(data);
		}
	});
}

function requestMoreInfo() {
	if ($("#class-status").text() == "Pending for review") {
		dialog('Are you sure you to request?', function() {
			$("#requestModal").modal("show");
		}, function() {

		});
	} else {
		alert("Class status must be 'Pending for review' to request for more infomation");
	}
}

function dialog(message, yesCallback, noCallback) {
	let icon = '<i class="bi bi-question-circle-fill" style="color: green;"></i>';
	$('#modal-title').html(icon + message);
	var dialog = $('#confirmModal').modal("show");

	$('#btn-ok').click(function() {
		dialog.modal();
		yesCallback();
	});
	$('#btn-cancel').click(function() {
		dialog.modal();
		noCallback();
	});
}

function sendRequest() {
	let classRequestInfo = {
		"classId": $("#classId").val(),
		"remarks": $("#request-remarks").val()
	}

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: '/class/request-info',
		data: JSON.stringify(classRequestInfo),
		success: function(data) {
			alert(data);
			window.location.reload();
		}
	});
}

function removeTrainee() {
	let traineeIds = [];
	$('input[name="trainee"]:checked').each(function() {
		traineeIds.push(this.value)
	});
	if (traineeIds.length == 0)
		alert("Please choose trainee to remove");
	else {
		let classId = $("#classId").val();
		console.log(JSON.stringify({
			"classId": classId,
			"traineeIds": traineeIds
		}));

		if ($("#class-status").text() == "Draft") {
			dialog('Are you sure you to remove trainee?', function() {
				$.ajax({
					type: "POST",
					contentType: "application/json",
					url: '/class/remove-trainee',
					data: JSON.stringify({
						"classId": classId,
						"traineeIds": traineeIds
					}),
					success: function(data) {
						alert(data);
						page(null, 0, $("#classId").val());
						pageTraineeNotInClass(null, 0, $("#classId").val());
					}
				});
			}, function() {

			});
		} else {
			alert("Class status must be 'Draft' to request remove trainee");
		}
	}
}

function finishClassBatch() {
	let classIds = [];
	let classId = $("#classId").val();
	classIds.push(classId);
	if ($("#class-status").text() == "In process") {
		dialog('Are you sure to  finish?', function() {
			$.ajax({
				type: "PUT",
				contentType: "application/json",
				data: JSON.stringify(classIds),
				url: '/class/finish',
				success: function(message) {
					alert(message);
					window.location.reload();
				},
				error: function(err) {
					alert("Error: " + err);
				}
			});
		});
	} else {
		alert("Class status must be 'In process' to finish class");
	}
}

function closeClassBatch() {
	let classIds = [];
	let classId = $('#classId').val();
	classIds.push(classId);
	console.log(classIds)

	if ($('#class-status').text() == "Pending for review") {
		dialog('Are you sure to close?', function() {
			$.ajax({
				type: "PUT",
				contentType: "application/json",
				data: JSON.stringify(classIds),
				url: '/class/close',
				success: function(message) {
					alert(message)
					window.location.reload();
				},
				error: function(err) {
					alert("Error:" + err)
				}
			})
		});
	} else {
		alert("Class status must be 'Pending for review' to close class");
	}
}


function cancelClassBatch() {
	let classIds = [];
	let classId = $("#classId").val();
	classIds.push(classId)
	dialog('Are you sure to cancel?', function() {
		$.ajax({
			type: "PUT",
			contentType: "application/json",
			url: '/class/cancel',
			data: JSON.stringify(classIds),
			success: function(message) {
				alert(message);
				window.location = "/class";
				console.log(message);
			},
			error: function(err) {
				alert("Error: " + err);
			}
		});
	}, function() {

	});
}

function submitClass() {
	let classId = $("#classId").val();
	dialog('Are you sure to submit?', function() {
		$.ajax({
			type: "PUT",
			contentType: "application/json",
			url: '/class/submit?classId=' + classId,
			success: function(message) {
				alert(message);
				window.location = "/class";
				console.log(message);
			},
			error: function(err) {
				alert("Error: " + err);
			}
		});
	}, function() {

	});
}

function approveSubmittedClass() {
	let classId = $("#classId").val();
	dialog('Are you sure to approve?', function() {
		$.ajax({
			type: "PUT",
			contentType: "application/json",
			url: '/class/approve?classId=' + classId,
			success: function(message) {
				alert(message);
				window.location = "/class";
				console.log(message);
			},
			error: function(err) {
				alert("Error: " + err);
			}
		});
	}, function() {

	});
}

function rejectSubmittedClass() {
	let classIds = [];
	let classId = $("#classId").val();
	classIds.push(classId)
	dialog('Are you sure to reject?', function() {
		$("#reasonModal").modal("show");

	}, function() {

	});
}

function reasonInputHandle() {
	let remarks = $("#request-remarks").val();
	let classId = $("#classId").val();
	let classBatchDto = {
		"classId": classId,
		"remarks": remarks,
	}
	$.ajax({
		type: "PUT",
		contentType: "application/json",
		url: '/class/reject',
		data: JSON.stringify(classBatchDto),
		success: function(message) {
			alert(message);
			window.location = "/class";
			console.log(message);
		},
		error: function(err) {
			alert("Error: " + err);
		}
	});
}

function addTraineeToClass() {
	let traineeIds = [];
	$('input[name="traineeNotIn"]:checked').each(function() {
		traineeIds.push(this.value)
	})

	let classId = $("#classId").val();
	console.log(JSON.stringify({
		"classId": classId,
		"traineeIds": traineeIds
	}));

	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/class/" + classId + "/trainee-not-in/add",
		data: JSON.stringify({
			"classId": classId,
			"traineeIds": traineeIds
		}),
		success: function(data) {
			alert(data);
			page(null, 0, $("#classId").val());
			pageTraineeNotInClass(null, 0, $("#classId").val());
		},
	})
}
function updateStatus() {
	$("#changeStatusModal").modal("hide");
	dialog('Are you sure you to submit?', function() {
		let row = $("#table-status").find(".row");
		let traineeUpdateStatusDtos = [];
		for (let i = 0; i < row.length; i++) {
			let traineeId = $(row[i]).find("input[name=traineeId]").val();
			let status = $(row[i]).find("select[name=statusInClass]").val();
			let traineeUpdateStatusDto = {
				"classId": $("#classId").val(),
				"traineeProifileId": traineeId,
				"statusName": status
			}
			traineeUpdateStatusDtos.push(traineeUpdateStatusDto);
		}
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: '/trainee-in-class/update-status',
			data: JSON.stringify(traineeUpdateStatusDtos),
			success: function(data) {
				alert(data);
				page(null, 0, $("#classId").val());
			}
		});
	}, function() {
		$("#changeStatusModal").modal("show");
	});

}


function importTrainee() {
	$("#importTraineeModal").modal("hide");
	dialog('Are you sure you to import trainee?', function() {
		let path = $("#importTraineeFile").val();
		if (path == null || path == "") {
			alert("Please choose file to import");
			return;
		}
		var formData = new FormData();
		formData.append('file', $('#importTraineeFile')[0].files[0]);
		let classId = $("#classId").val();
		$.ajax({
			url: '/class/' + classId + '/import-trainee',
			type: 'POST',
			data: formData,
			processData: false, // tell jQuery not to process the data
			contentType: false, // tell jQuery not to set contentType
			success: function(data) {
				alert(data);
				page(null, 0, $("#classId").val());
				pageTraineeNotInClass(null, 0, $("#classId").val());
			}
		});
	}, function() {
		$("#importTraineeModal").modal("show");
	});

}


function clickOKAccept(id) {
	window.location.href = '/class/accept?classId=' + id;
}

function clickOKStart(id) {
	window.location.href = '/class/start?classId=' + id;
}


