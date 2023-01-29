function deleteTraineeById() {
    let traineeIds = []
    let traineeId = $('#traineeId').val();
    traineeIds.push(traineeId);
    console.log(traineeIds)
    if ($('#trainee-type').text() == 'Trainee') {
        dialog('Are you sure to delete', function () {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(traineeIds),
                url: "/trainee/delete-trainee-infor",
                success: function (message) {
                    alert(message);
                    window.location = "http://localhost:8080/trainee";
                }
            })
        })
    } else {
        alert("Trainee profile type must be 'Trainee' ");
    }
}

function deleteTrainees() {
    let traineeIds = [];
    $('input[name="trainee"]:checked').each(function () {
        traineeIds.push(this.value)
    })
    if (traineeIds.length == 0)
        alert("Please choose trainee to remove");
    else {
        if ($('#trainee-type').val() == 'Trainee') {
            dialog('Are you sure to delete', function () {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/trainee/delete-trainee-infor",
                    data: JSON.stringify(traineeIds),
                    success: function (message) {
                        window.location = "http://localhost:8080/trainee";
                    },
                });
            })
        } else {
            alert("Trainee profile type must be 'Trainee' ");
        }
    }
}

function dialog(message, yesCallback, noCallback) {
    let icon = '<i class="bi bi-question-circle-fill" style="color: green;"></i>';
    $('#modal-title').html(icon + message);
    var dialog = $('#confirmModal').modal("show");

    $('#btn-ok').click(function () {
        dialog.modal();
        yesCallback();
    });
    $('#btn-cancel').click(function () {
        dialog.modal();
        noCallback();
    });
}

// function page(ele, page) {
//     let pagesize = $("#pageSize").val();
//     $.ajax({
//         type: "GET",
//         url: '/trainee',
//         data: {
//             offSet: page,
//             pageSize: pagesize
//         },
//         success: function (data) {
//             $('#traineeTbl').html(data);
//         }
//     });
// }