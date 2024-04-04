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
    var cartqty = parseInt(updatedCart.listProductsIDandQuantity.length)
    $('#mini-cart-count').text(cartqty);
    /*const totalAmount = updatedCart.listProductsIDandQuantity.reduce((total, item) => {
        const itemTotal = item.quantity * item.price;
    return total + itemTotal;
    }, 0);*/
    var total = 0;
    updatedCart.listProductsIDandQuantity.forEach(item => {
        total += item.quantity * item.price;
        console.log('total: '+total);
    })
    $('#mini-total-amount').text(formatCurrencyheader(total));
    console.log(cartqty);
    console.log(total);
// Hàm lấy giá trị cookie bằng tên
    function getCookie(name) {
        var cookieValue = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
        return cookieValue ? cookieValue.pop() : null;
    }
});
/**
 * *******************************************
 * Định dạng tiền tệ việt nam
 * *******************************************
 */
function formatCurrency(number) {
    return number.toLocaleString('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
}
function formatCurrencyheader(number) {
    const trillion = 1000000000000;
    const billion = 1000000000;
    const million = 1000000;
    const thousand = 1000;

    if (number >= trillion) {
        return (number / trillion).toFixed(2) + "tr ₫";
    } else if (number >= billion) {
        return (number / billion).toFixed(2) + "ty ₫";
    } else if (number >= million) {
        return (number / million).toFixed(2) + "tr ₫";
    } else if (number >= thousand) {
        return (number / thousand).toFixed(2) + "n ₫";
    } else {
        return number.toLocaleString("vi-VN") + " ₫";
    }
}
