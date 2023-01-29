$(document).ready(function() {
	$('#btn-update').removeAttr("href");
	$('#btn-update').prop('disabled', true);
	if ($('#locate-inp').val() == '') {
		$('#locate-inp').val($('#locate-selects option:selected').text());
	}

	if ($('#name-inp').val() == '') {
		$('#name-inp').val($('#name-selects option:selected').text());
	}

	if ($('#status-inp').val() == '') {
		$('#status-inp').val($('#status-selects option:selected').text());
	}
	// Handle Location
	$('#locate-btn').click(function() {
		if ($('#locate-lst').is(":hidden")) {
			$('#locate-lst').show();
			$('#locate-btn').html("<i class='bi bi-caret-up-fill'></i>");
			//			let val = $('#locate-inp').val();
			//			for (let i = 0; i < $('#locate-selects > option').length; i++) {
			//				if ($('#locate-selects select').val() === val) {
			//					$('#locate-selects').prop('selectedIndex', i);
			//					break;
			//				}
			//			}
		}
		else { HideSelectBox("locate-lst", "locate-btn"); }
	});


	$('#locate-selects').change(function() {
		$("#locate-inp").val($('#locate-selects option:selected').text());
		HideSelectBox("locate-lst", "locate-btn");
	});


	$('#locate-inp').focus(function() {
		HideSelectBox("locate-lst", "locate-btn");
	});

	function HideSelectBox(listId, btnId) {
		$('#' + listId).hide();
		$('#' + btnId).html("<i class='bi bi-caret-down-fill'></i>");
	}

	// Handle Name
	$('#name-btn').click(function() {
		if ($('#name-lst').is(":hidden")) {
			$('#name-lst').show();
			$('#name-btn').html("<i class='bi bi-caret-up-fill'></i>");
			//			let val = $('#name-inp').val();
			//			for (let i = 0; i < $('#name-selects > option').length; i++) {
			//				if ($('#name-selects select').val() === val) {
			//					$('#name-selects').prop('selectedIndex', i);
			//					break;
			//				}
			//			}
		}
		else { HideSelectBox("name-lst", "name-btn"); }
	});


	$('#name-selects').change(function() {
		$("#name-inp").val($('#name-selects option:selected').text());
		HideSelectBox("name-lst", "name-btn");
	});


	$('#name-inp').focus(function() {
		HideSelectBox("name-lst", "name-btn");
	});

	// Handle Status
	$('#status-btn').click(function() {
		if ($('#status-lst').is(":hidden")) {
			$('#status-lst').show();
			$('#status-btn').html("<i class='bi bi-caret-up-fill'></i>");
			//			let val = $('#status-inp').val();
			//			for (let i = 0; i < $('#locate-selects > option').length; i++) {
			//				if ($('#locate-selects select').val() === val) {
			//					$('#locate-selects').prop('selectedIndex', i);
			//					break;
			//				}
			//			}
		}
		else { HideSelectBox("status-lst", "status-btn"); }
	});


	$('#status-selects').change(function() {
		$("#status-inp").val($('#status-selects option:selected').text());
		HideSelectBox("status-lst", "status-btn");
	});


	$('#status-inp').focus(function() {
		HideSelectBox("status-lst", "status-btn");
	});


	$("#search_btn").click(function() {
		//alert("Handler for .click() called.");
		let locationId = $('#locate-selects').find(":selected").val(),
			classId = $('#name-selects').find(":selected").val(),
			status = $('#status-selects').find(":selected").text(),
			fromDate = isValidDate(new Date($('#from-date').val())) == true ? new Date($('#from-date').val()) : '',
			endDate = isValidDate(new Date($('#end-date').val())) == true ? new Date($('#end-date').val()) : '';

		let classBatchDto = {
			"classId": classId,
			"locationID": locationId,
			"actualStartDate": fromDate,
			"actualEndDate": endDate,
			"status": status.trim(),
		}
		console.log(JSON.stringify(classBatchDto));

		$.ajax(
			{

				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: 'class/search',
				data: JSON.stringify(classBatchDto),
				success:
					function(data) {
						console.log(data);
						$("#class-data-area").empty();
						$("#class-data-area").append(data);
					},
				error:
					function(err) {
						alert("Error: " + err);
					}
			});

	});

	$("#checkAll").click(function() {
		$('input:checkbox').not(this).prop('checked', this.checked);
		var checkSelected = [];
		$.each($("input[name='classId']:checked"), function() {
			checkSelected.push($(this).val());
		});
		if (checkSelected.length > 1 || checked.length == 0) {
			$('#btn-update').removeAttr("href");
			$('#btn-update').prop('disabled', true);
		} else {
			$('#btn-update').attr("href", "");
			$('#btn-update').prop('disabled', false);
		}
	});

	const isValidDate = (d) => {
		return d instanceof Date && !isNaN(d);
	}

	$("#btn-cancel").click(function() {
		var classIds = [];
		$.each($("input[name='classId']:checked"), function() {
			classIds.push($(this).val());
		});

		console.log(classIds);

		$.ajax({

			type: "PUT",
			contentType: "application/json; charset=utf-8",
			url: 'class/cancel',
			data: JSON.stringify(classIds),
			success: function(message) {
				alert(message);
				console.log(message);
			},
			error: function(err) {
				alert("Error: " + err);
			}
		});

	});
})