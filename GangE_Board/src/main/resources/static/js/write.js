$(function () {
    $("#submitBtn").click(function () {
        if ($("#title").val() == '') {
            $("#title").addClass("is-invalid");
        }
        if ($("#contents").val() == '') {
            $("#contents").addClass("is-invalid");
        }

        if ($("#title").val() != '' && $("#contents").val() != '') {
            $("#writeForm").submit();
        }
    });
});