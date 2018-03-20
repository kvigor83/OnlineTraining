function validateForm(data) {
    ClearFields();
    if (data.fio.value.length < 3) {
        document.getElementById('fio_correct').innerHTML = 'wrong fio';
        document.getElementById('fio_correct').className = 'acorrect';
        data.fio.focus();
        return false;
    }
    if (!(document.getElementById('login_id').value.search(/^[A-Za-z][A-Za-z0-9_-]+$/) > -1)) {
        document.getElementById('login_correct').innerHTML = 'Wrong login';
        document.getElementById('login_correct').className = 'acorrect';
        data.login.focus();
        return false;
    }
    if (!(document.getElementById('pass_id').value.search(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}/) > -1)) {
        document.getElementById('pass_correct').innerHTML = 'Wrong password';
        document.getElementById('pass_correct').className = 'acorrect';
        data.pass.focus();
        return false;
    }
    if (data.repass.value !== data.pass.value) {
        document.getElementById('repass_correct').innerHTML = 'Wrong repassword';
        document.getElementById('repass_correct').className = 'acorrect';
        data.repass.focus();
        return false;
    }
    if (!(document.getElementById('email_id').value.search(/\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,8}/) > -1)) {
        document.getElementById('email_correct').innerHTML = 'Wrong email';
        document.getElementById('email_correct').className = 'acorrect';
        data.email.focus();
        return false;
    }
    return true;
}

function ClearFields() {
    document.getElementById('fio_correct').innerHTML = '';
    document.getElementById('login_correct').innerHTML = '';
    document.getElementById('pass_correct').innerHTML = '';
    document.getElementById('repass_correct').innerHTML = '';
    document.getElementById('email_correct').innerHTML = '';
}