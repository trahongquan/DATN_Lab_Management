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
