var countdown = 3; // Số giây còn lại
var countdownElement = document.getElementById("countdown");
// Cập nhật số giây còn lại và ẩn thông báo khi hết thời gian
var countdownInterval = setInterval(function() {
    countdown--;
    countdownElement.textContent = countdown;
    if (countdown < 0) {
        document.getElementById("successMessage").style.display = "none";
        clearInterval(countdownInterval);
    }
}, 1000);

var uncountdown = 3; // Số giây còn lại
var uncountdownElement = document.getElementById("uncountdown");
// Cập nhật số giây còn lại và ẩn thông báo khi hết thời gian
var uncountdownInterval = setInterval(function() {
    uncountdown--;
    uncountdownElement.textContent = uncountdown;
    if (uncountdown < 0) {
        document.getElementById("unsuccessMessage").style.display = "none";
        clearInterval(uncountdownInterval);
    }
}, 1000);
