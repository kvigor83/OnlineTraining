function CorrectLogin(item) {
    var re = /^[A-Za-z][A-Za-z0-9_-]+$/;
    var item_view = 'login_view';
    var item_correct = 'login_correct';
    if (document.getElementById(item).value.length >= 5 && document.getElementById(item).value.search(re) > -1) {
        document.getElementById(item_correct).innerHTML = '';
        document.getElementById(item_correct).className = 'correct';
        document.getElementById('check_login').value = 1;
    } else {
        document.getElementById(item_correct).innerHTML = 'не допустимый логин';
        document.getElementById(item_correct).className = 'acorrect';
        document.getElementById('check_login').value = 0;
    }
    checkAll();
}


function CorrectPass(item) {
    var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}/;
    var item_view = 'pass_view';
    var item_correct = 'pass_correct';
    var item_login_value = document.getElementById('login_id').value;
    var item_login_length = document.getElementById('login_id').value.length;
    CorrectRepass('repass_id');
    if (document.getElementById(item).value == item_login_value && item_login_length >= 5) {
        document.getElementById(item_correct).innerHTML = 'пароль совпадает с логином';
        document.getElementById(item_correct).className = 'acorrect';
        document.getElementById('check_pass').value = 0;
    } else {
        if (/*document.getElementById(item).value.length >= 6 && */document.getElementById(item).value.search(re) > -1) {
            document.getElementById(item_correct).innerHTML = '';
            document.getElementById(item_correct).className = 'correct';
            document.getElementById('check_pass').value = 1;
            document.getElementById(item_view).innerHTML = '';

        } else  {
            document.getElementById(item_correct).innerHTML = 'не допустимый пароль';
            document.getElementById(item_correct).className = 'acorrect';
            document.getElementById('check_pass').value = 0;

        }
    }
    checkAll();
}

function CorrectRepass(item) {
    var item_pass_value = document.getElementById('pass_id').value;
    var item_pass_length = document.getElementById('pass_id').value.length
    var item_correct = 'repass_correct';
    if (document.getElementById(item).value == item_pass_value) {
        document.getElementById(item_correct).innerHTML = '';
        document.getElementById(item_correct).className = 'correct';
        document.getElementById('check_repass').value = 1;
    } else /*if (document.getElementById(item).value.length >= 6)*/ {
        document.getElementById(item_correct).innerHTML = 'пароли не совпадают';
        document.getElementById(item_correct).className = 'acorrect';
        document.getElementById('check_repass').value = 0;
    }
    checkAll();
}


function CorrectEmail(item) {
    var rem = /\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,8}/;
    if (document.getElementById(item).value.search(rem) > -1) {
        document.getElementById('email_correct').innerHTML = '';
        document.getElementById('email_correct').className = 'correct';
        document.getElementById('check_email').value = 1;
    } else {
        document.getElementById('email_correct').innerHTML = 'не верный email';
        document.getElementById('email_correct').className = 'acorrect';
        document.getElementById('check_email').value = 0;
    }
    checkAll();
}


function checkAll() {
    var x;
    var check_login = document.getElementById('check_login').value;
    var check_pass = document.getElementById('check_pass').value;
    var check_repass = document.getElementById('check_repass').value;
    var check_email = document.getElementById('check_email').value;
    x = check_login + check_pass + check_repass + check_email;
    document.getElementById('check_all').value = x;
    if (document.getElementById('check_all').value == 1111) {
        document.getElementById('submit_id').hidden = false;
    } else {
        document.getElementById('submit_id').hidden = true;
    }
}