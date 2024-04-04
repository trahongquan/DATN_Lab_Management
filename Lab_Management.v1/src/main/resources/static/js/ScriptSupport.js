/**
 * *******************************************
 * Đánh giá sản phẩm:
 * *******************************************
 */
// Script JavaScript cho trang HTML
document.addEventListener("DOMContentLoaded", function() {
    var stars = document.querySelectorAll(".star");

    stars.forEach(function(star, index) {
        star.addEventListener("mouseover", function() {
            highlightStars(index);
        });

        star.addEventListener("mouseout", function() {
            resetStars();
        });

        star.addEventListener("click", function() {
            // Xử lý khi người dùng chọn số sao
            alert("Bạn đã đánh giá " + (index + 1) + " sao.");
        });
    });

    function highlightStars(index) {
        for (var i = 0; i <= index; i++) {
            stars[i].style.color = "#FFD700";
        }
    }

    function resetStars() {
        stars.forEach(function(star) {
            star.style.color = "";
        });
    }
});
