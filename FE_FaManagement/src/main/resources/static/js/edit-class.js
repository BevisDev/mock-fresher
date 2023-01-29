var showModal = false;

$("#btn-ok").click(function () {
	showModal = true;
});
$("#btn-cancel").click(function () {
	showModal = false;
});
$(document).ready(function () {
	$('[data-toggle="toggle"]').change(function () {
		$(this).parents().next('.hide').toggle();
	});

	$.validator.setDefaults({
		submitHandler: function (form) {
			if(validateData() === true)
			{
				if (showModal === false) {
					$("#confirmModal").modal();
				} else {
					form.submit();
					showModal = false;
				}
			}
		}
	});
	
	$('.select-multiple').select2();

	if ($("#msg").val() != "") {
		alert($("#msg").val());
	}
	
	jQuery.validator
		.addMethod(
			"dateValidate",
			function (value, element) {
				return /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/i
					.test(value);
			});

	jQuery.validator.addMethod("startDateValidate", function (
		value, element) {
		let startDate = new Date(value);
		if ($("#expectedEndDate").val() == "")
			return true;
		let endDate = new Date($("#expectedEndDate").val());
		return endDate >= startDate;
	});

	jQuery.validator.addMethod("passDateValidate", function (
		value, element) {
		let today = new Date();
		today.setHours(0, 0, 0, 0);
		return new Date(value) > today;
	});

	jQuery.validator.addMethod("weightedNumValidate", function (
		value, element) {
		if (value == "")
			return true;
		return /^\d{2}-\d{2}-\d{2}-\d{2}$/i.test(value);
	});

	$("#editClass").validate({
		errorClass: "invalid-feedback",
		successClass: "valid-feedback",
		rules: {
			"expectedStartDate": {
				required: true,
				dateValidate: true,
				startDateValidate: true,
				passDateValidate: true
			},
			"expectedEndDate": {
				required: true,
				dateValidate: true,
				passDateValidate: true
			},
			"location": {
				required: true
			},
			"budgetCode": {
				required: true
			},
			"estimatedBudget": {
				required: true
			},
			"classAdmin": {
				required: true
			},
			"skill": {
				required: true
			},
			"classTypeId": {
				required: true
			},
			"positionId": {
				required: true
			},
			"weightedNumber": {
				weightedNumValidate: true
			}
		},
		messages: {
			"expectedStartDate": {
				required: "You must input all required fields!",
				dateValidate: "Wrong format.",
				startDateValidate: '"Expected Start Date” must not be later than “Expected End Date"',
				passDateValidate: "Cannot input past date"

			},
			"expectedEndDate": {
				required: "You must input all required fields!",
				dateValidate: "Wrong format.",
				passDateValidate: "Cannot input past date"
			},
			"location": {
				required: "You must input all required fields!"
			},
			"budgetCode": {
				required: "You must input all required fields!"
			},
			"estimatedBudget": {
				required: "You must input all required fields!"
			},
			"classAdmin": {
				required: "You must input all required fields!"
			},
			"skill": {
				required: "You must input all required fields!"
			},
			"classTypeId": {
				required: "You must input all required fields!"
			},
			"positionId": {
				required: "You must input all required fields!"
			},
			"weightedNumber": {
				weightedNumValidate: "Wrong format."
			}

		},
		highlight: function (element,
			errorClass, validClass) {
			$(element).addClass("is-invalid")
				.remove("is-valid");

		},
		unhighlight: function (element,
			errorClass, validClass) {

			$(element).addClass("is-valid")
				.removeClass("is-invalid");

		},
		errorElement: "em",
		errorPlacement: function (error,
			element) {
			error.addClass("invalid-feedback");
			if (element.prop("type") === "radio") {
				error.insertAfter(element
					.parent().parent());
			} else {
				error.insertAfter(element);
			}
		},
	});
})

function deleteRow(btn) {
	if ($(btn).parent().parent().parent().find(".trData").length > 1)
		$(btn).closest("tr").remove();
	else {
		$(btn).closest("tr").find(":input").val("");
		$(btn).closest("tr").find("select");
		$(btn).closest("tr").find(":input[name=row_index]").val("");
		$(btn).closest("tr").find(".error").text("");
	}
}

function addNewBudget(btn) {
	let last_row = $(btn).parent().parent().parent().find("tr").last();
	let row_index = parseInt(last_row.find("input[name=row_index]").val()) + 1;
	if(isNaN(row_index))
		row_index=0;
	let tbody = $(last_row).parent();
	let trHtml = '<tr class="budget-data trData">'
		+ '<td hidden="true"><input name="row_index" value="'
		+ row_index
		+ '" type="text"></td>'
		+ '<td><button class="btn deleteBtn" type="button"'
		+ 'onclick="deleteRow(this)">'
		+ '<i class="bi bi-trash3-fill" style="color: red;"></i>'
		+ '</button>'
		+ '</td>'
		+ '<td><input name="budgetDetailDtos['
		+ row_index
		+ '].item" class="requiredData"> <p class="error"></p></td>'
		+ '<td><input name="budgetDetailDtos['
		+ row_index
		+ '].unit" class="requiredData"> <p class="error"></p></td>'
		+ '<td><input class="unitExpense requiredData" type="number" onchange="caculateAmountBudget(this)" name="budgetDetailDtos['
		+ row_index
		+ '].unitExpense"> <p class="error"></p></td>'
		+ '<td><input class="quantity requiredData" type="number" onchange="caculateAmountBudget(this)" name="budgetDetailDtos['
		+ row_index
		+ '].quantity"> <p class="error"></p></td>'
		+ '<td><input class="amount requiredData" onchange="caculateSumBudget(this)" name="budgetDetailDtos['
		+ row_index
		+ '].amount" readonly></td>'
		+ '<td><input class="tax requiredData" type="number" onchange="caculateSumBudget(this)" name="budgetDetailDtos['
		+ row_index
		+ '].tax" > <p class="error"></p></td>'
		+ '<td><input class="sum requiredData" onchange="caculateTotal()" name="budgetDetailDtos['
		+ row_index
		+ '].sum"'
		+ 'readonly></td>'
		+ '<td><input  name="budgetDetailDtos['
		+ row_index
		+ '].note"></td>' + '</tr>';
	tbody.append(trHtml);
}

function addNewAudit(btn) {
	let last_row = $(btn).parent().parent().parent().find("tr").last();
	let row_index = parseInt(last_row.find("input[name=row_index]").val()) + 1;
	if(isNaN(row_index))
		row_index=0;
	let select_options = $(last_row).find(".select-eventCategory").html();
	let tbody = $(last_row).parent();
	let trHtml = '<tr class="audit-data trData">'
		+ '<td hidden><input name="row_index" value="'
		+ row_index
		+ '" type="text"></td>'
		+ '<td>'
		+ '<button class="btn deleteBtn" type="button" onclick="deleteRow(this)"'
		+ 'id="deleteBtn">'
		+ '<i class="bi bi-trash3-fill" style="color: red;"></i>'
		+ '</button>'
		+ '</td>'
		+ '<td><input id="date" class="form-control requiredData" name="auditDtos['
		+ row_index
		+ '].date" type="date" '
		+ 'placeholder=" / / " /> <p class="error"></td>'
		+ '<td><select class=" col-12 requiredData"'
		+ 'name="auditDtos['
		+ row_index
		+ '].eventCategoryId">'
		+ select_options
		+ 'th:text="${eventCategory.name}"'
		+ 'th:val="${eventCategory.id}"></option>'
		+ '</select></td>'
		+ '<td><input name="auditDtos['
		+ row_index
		+ '].relatedPartyOrPeople"></td>'
		+ '<td><input name="auditDtos['
		+ row_index
		+ '].action" class="requiredData"> <p class="error"></p></td>'
		+ '<td><input name="auditDtos['
		+ row_index
		+ '].pic" class="requiredData"> <p class="error"></p></td>'
		+ '<td><input id="date" class="form-control requiredData" type="date"'
		+ 'name="auditDtos['
		+ row_index
		+ '].deadline" placeholder=" / / " /> <p class="error"></p></td>'
		+ '<td><input name="auditDtos['
		+ row_index
		+ '].note"></td>'
		+ '</tr>';
	tbody.append(trHtml);

}

function caculateAmountBudget(input) {
	let row = $(input).parent().parent();
	let unitExpense = row.find(".unitExpense").val();
	let quanitty = row.find(".quantity").val();
	if (unitExpense != "" && quanitty != "") {
		row.find(".amount").val(unitExpense * quanitty);
		caculateSumBudget(row.find(".amount"))
	}
}

function caculateSumBudget(input) {
	let row = $(input).parent().parent();
	let amount = row.find(".amount").val();
	let tax = row.find(".tax").val();
	if (amount != "" && tax != "'") {
		row.find(".sum").val(amount + amount * tax / 100);
		caculateTotal();
	}
}

function caculateTotal() {
	let total = 0;
	$('.sum').each(function () {
		total += parseFloat($(this).val());
	})
	$('.total').val(total);
	if ($('.total').val() == "NaN")
		$('.total').val(0);
	let estimateBudget = $("#estimateBudget").val();
	if (total > estimateBudget)
		$("input[name=overBudget]").val("YES");
	else
		$("input[name=overBudget]").val("NO");
}

function validateData() {
	let rs = true;
	rs = validateBudget(rs);
	rs = validateAudit(rs)
	let total = parseFloat($("#total").val());
	let estimated = parseFloat($("#estimateBudget").val());
	if (total > estimated) {
		$("#errorOverBudget").text("Over Budget !!!");
		rs = false;
	} else {
		$("#errorOverBudget").text("");
	}
	return rs;
}

function validateBudget(preRs) {
	let rs = true;
	let budgetDatas = $.find(".budget-data");
	$(budgetDatas).find(".error").text("");
	let inputDefaults = $(budgetDatas[0]).find(".requiredData");

	let defaultValue = true;
	for (let i = 0; i < inputDefaults.length; i++) {
		if ($(inputDefaults[i]).val() == "") {
			$(inputDefaults[i]).parent().find(".error").text(
				"You must input all required fields!");
			rs = false;
		} else {
			defaultValue = false;
		}
	}

	if (defaultValue == true) {
		rs = true;
		$(budgetDatas[0]).find(".error").text("");
	}

	for (let i = 1; i < budgetDatas.length; i++) {
		let inputs = $(budgetDatas[i]).find(".requiredData");
		for (j = 1; j < inputs.length; j++) {
			if ($(inputs[j]).val() == "") {
				$(inputs[j]).parent().find(".error").text(
					"You must input all required fields!");
				rs = false;
			}
		}
	}
	if (preRs == true)
		return rs;
	return preRs;
}

function validateAudit(preRs) {
	let rs = true;
	let auditDatas = $.find(".audit-data");

	let inputDefaults = $(auditDatas[0]).find(".requiredData");

	let defaultValue = true;
	for (let i = 0; i < inputDefaults.length; i++) {
		if ($(inputDefaults[i]).val() == "") {
			$(inputDefaults[i]).parent().find(".error").text(
				"You must input all required fields!");
			rs = false;
		} else {
			defaultValue = false;
		}
	}

	if (defaultValue == true) {
		rs = true;
		$(auditDatas[0]).find(".error").text("");
	}
	
	for (let i = 1; i < auditDatas.length; i++) {
		let inputs = $(auditDatas[i]).find(".requiredData");
		for (j = 0; j < inputs.length; j++) {
			if ($(inputs[j]).val() == "") {
				$(inputs[j]).parent().find(".error").text(
					"You must input all required fields!");
				rs = false;
			}
		}
	}
	if (preRs == true)
		return rs;
	return preRs;
}