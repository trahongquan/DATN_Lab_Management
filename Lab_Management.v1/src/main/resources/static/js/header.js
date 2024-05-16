/************************************ **********/

/**************** Dark & Light *************** */

var toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
var currentTheme = localStorage.getItem('theme');

if (currentTheme) {
    document.documentElement.setAttribute('data-theme', currentTheme);

    if (currentTheme === 'dark') {
        toggleSwitch.checked = true;
    }
}

function switchTheme(e) {
    if (e.target.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
        localStorage.setItem('theme', 'dark');
    } else {
        document.documentElement.setAttribute('data-theme', 'light');
        localStorage.setItem('theme', 'light');
    }
}

toggleSwitch.addEventListener('change', switchTheme, false);

/********************************************* */

/** xóa app nếu người dùng cố tình sửa username*/


function checkuser(usernameHeader) {
    var username = getParameterValue('username',window.location.href)
    if(username != null && username !== usernameHeader){
        $('body').remove();
    }
}
function getParameterValue(parameterName, url) {
    var params = new URLSearchParams(new URL(url).search);
    return params.get(parameterName);
}
/********************************************* */
