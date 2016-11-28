
function apiCallGet(call_name, get_data, callback) {
    $.get(call_name, get_data, function (data) {
        if (callback !== null) {
            callback(data);
        }
    }, 'json');
}

function apiCallPost(call_name, post_data, callback) {
    $.post(call_name, post_data, function (data) {
        if (callback !== null) {
            callback(data);
        }
    }, 'json');
}

function routerShowModal() {
    $("#router-add-modal").modal('show');
}

function validateInput() {
    var input = new Object();
    input.text = $("#small-content").val();
    apiCallPost('./analyze', JSON.stringify(input), function (data) {
        if (data.status === "success") {
            showSuccessModal();
        } else {
            showErrorModal(data.error);
        }
    });
}

function showSuccessModal() {
    $("#success-modal").modal('show');
}

function showErrorModal(error) {
    $("#error-modal-message").html("");
    $("#error-modal-message").append(error);
    $("#error-modal").modal('show');
}

