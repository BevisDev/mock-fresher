var indexEntry = entryList.length - 1;
var indexInter = interList.length - 1;

$("#btn-add-entry").click(function () {
	indexEntry += 1;
	$("#entry-table tr:last").after('<tr><td class="text-center"><i class="fa fa-trash font-weight-bold btn-remove" onclick="removeRowTable(this)"></i></td><td class="final-cell"></td><td><input type="date" class="ip-custom" name="entryTests[' + indexEntry + '].date" /></td><td><input type="text" class="ip-custom" name="entryTests[' + indexEntry + '].languageValuator" /></td><td><input type="text" class="ip-custom" name="entryTests[' + indexEntry + '].languageResult"/></td ><td><input type="text" class="ip-custom" name="entryTests[' + indexEntry + '].technicalValuator"/></td><td><input type="text" class="ip-custom" name="entryTests[' + indexEntry + '].technicalResult"/></td><td><select name="entryTests[' + indexEntry + '].resultEntry" class="sl-custom">' + getResultList() + '</select></td></tr >');;
});

$("#btn-add-inter").click(function () {
	indexInter += 1;
	$("#inter-table tr:last").after('<tr ><td class="text-center"><i class="fa fa-trash font-weight-bold btn-remove" onclick="removeRowTable(this)"></i></td><td class="d-none"></td><td class="final-cell"></td><td><input type="date" class="ip-custom" name="interviews['+indexInter+'].date"/></td><td><input type="text" class="ip-custom" name="interviews['+indexInter+'].interviewer" /></td><td><input type="text" class="ip-custom" name="interviews['+indexInter+'].comment"/></td><td><select class="sl-custom" name="interviews['+indexInter+'].result">'+ getResultList() + '</select></td></tr>');
});

function removeRowTable(btn) {
	$(btn).parent().parent().remove();
}

function getResultList() {
	let options = "";
	resultList.forEach(result => {
		options += '<option  value="' + result + '">' + result + '</option>';
	});
	return options;
}