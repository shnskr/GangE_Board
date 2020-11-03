var idFlag = false;
var pwFlag = false;
var pwChkFlag = false;

var idRe = /^\w{5,20}$/;
var pwRe = /^[\w!@#$%^&*()]{8,20}$/;

$(function () {

    // 아이디 유효성 체크
    $("#id").keyup(function () {
        if ($("#id").val() == '') {
            $("#idNotice").text("아이디를 입력해 주세요");
            $("#idNotice").css("color", "red");
            idFlag = false;
            flagChk();
        } else if (idRe.test($("#id").val())) {
        	
        	var userId = $("#id").val();
        	
            $.ajax({
                url: "/account/idcheck?id=" + userId,
                type: "GET",
                success: function (data) {
                    if (data) {
                    	$("#idNotice").text("중복된 아이디 입니다");
                        $("#idNotice").css("color", "red");
                        idFlag = false;
                        flagChk();
                    } else {
                        $("#idNotice").text("사용 가능한 아이디 입니다");
                        $("#idNotice").css("color", "blue");
                        idFlag = true;
                        flagChk();
                    }
                }
            });
        } else {
            $("#idNotice").text("5~20자 영어, 숫자, 특수문자 _ 사용 가능");
            $("#idNotice").css("color", "red");
            idFlag = false;
            flagChk();
        }
    });

    // 비밀번호 유효성 체크
    $("#pw").keyup(function () {
        pwEqual();
        if ($("#pw").val() == '') {
            $("#pwNotice").text("비밀번호를 입력해 주세요");
            $("#pwNotice").css("color", "red");
            pwFlag = false;
            flagChk();
        } else if (pwRe.test($("#pw").val())) {
            $("#pwNotice").text("사용 가능한 비밀번호 입니다");
            $("#pwNotice").css("color", "blue");
            pwFlag = true;
            flagChk();
        } else {
            $("#pwNotice").text("8~20자 영어, 숫자, 특수문자 !@#$%^&*()_만 사용 가능");
            $("#pwNotice").css("color", "red");
            pwFlag = false;
            flagChk();
        }
    });

    // 비밀번호 확인 유효성 체크
    $("#pwChk").keyup(function () {
    	pwEqual();
    });
});

// 회원가입 버튼 활성화
function flagChk() {
    if (idFlag && pwFlag && pwChkFlag) {
        $("#notice").css("background-color", "lightblue");
        $("#submitButton").attr("disabled", false);
    } else {
        $("#notice").css("background-color", "pink");
        $("#submitButton").attr("disabled", true);
    }
}

function pwEqual() {
	if ($("#pwChk").val() == '') {
		$("#pwChkNotice").text("비밀번호 확인을 입력해 주세요");
        $("#pwChkNotice").css("color", "red");
        pwChkFlag = false;
        flagChk();
	} else if ($("#pw").val() == $("#pwChk").val()) {
        $("#pwChkNotice").text("두 개의 비밀번호가 일치합니다");
        $("#pwChkNotice").css("color", "blue");
        pwChkFlag = true;
        flagChk();
    } else {
        $("#pwChkNotice").text("두 개의 비밀번호가 틀립니다");
        $("#pwChkNotice").css("color", "red");
        pwChkFlag = false;
        flagChk();
    }
}