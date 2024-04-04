/**
 * *******************************************
 * Số lượng hàng hiện có trong giỏi hàng
 * *******************************************
 */
$(document).ready( function () {
    var cookies = document.cookie;

    var cartCookie = getCookie("cartCookie");
    // Chuyển cartCookie thành Object updatedCart (nếu có)
    var updatedCart = cartCookie ? JSON.parse(atob(cartCookie)) : [];
    var cartqty = parseInt(updatedCart.productsID.length)
    $('.cart-qty').text('(' + cartqty + ')');
    console.log(cartqty);
// Hàm lấy giá trị cookie bằng tên
    function getCookie(name) {
        var cookieValue = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
        return cookieValue ? cookieValue.pop() : null;
    }
});

/**
 * *******************************************
 * Chuyển về dạng slice cho màn hình bé
 * *******************************************
 */
$(document).ready(function() {
    $('#toggle').click(function() {
        $('nav').slideToggle();
    });
})

/**
 * *******************************************
 * Slide banner
 * *******************************************
 */
// <!-- Initialize Swiper -->
if(document.querySelector('banner')){
    var swiper = new Swiper(".mySwiper", {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
            rotate: 0,
            stretch: 0,
            depth: 300,
            modifier: 1,
            slideShadows: false,
        },
        pagination: {
            el: ".swiper-pagination",
        },
        loop: true, // Chạy vòng vô tận
        slidesPerView: 3, // Hiển thị 1 slide tại một thời điểm
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        on: {
            transitionEnd: function () {
                $('.mySwiper').css({
                    transform: 'translate3d(0px, 0px, 0px)',
                })
                $('.swiper-wrapper').css({
                    transform: 'translate3d(0px, 0px, 0px)',
                })
            }
        }

    });
}
// $(document).ready(function () {
//     var swiperWrapper = document.getElementById("x100");
//     swiperWrapper.style.transform = "translate3d(0px, 0px, 0px)";
// });
