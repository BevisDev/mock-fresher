//Table expland click event 
$(document).ready(function () {
    $('[data-toggle="toggle"]').change(function () {
        $(this).parent().insertAfter('.tr')
        $(this).parents().next('.hide').toggle();
    });

    //Table add row when click button 
    $('.addBtn').click(function () {
    	addRow(this);	
    })

   $('.deleteBtn').click(function(){
        $(this).closest('tr').remove();
    })
});

function addRow(btn)
{
	var row_index = $(btn).parent().parent().parent().index();
	console.log(row_index);
	let row = $(btn).parent().parent().parent().parent().find("tbody").find("tr")[row_index+1];
	let tbody= $(row).parent();
	let rowClone = $(row).clone();
	$(rowClone).find(":input").val("");
	$(tbody).append(rowClone);
}