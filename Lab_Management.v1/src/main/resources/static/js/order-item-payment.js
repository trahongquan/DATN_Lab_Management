/**
 * *******************************************
 * Ẩn hiện nhập data payment method
 * *******************************************
 */
{
    $('#paymentMethod').on('change', function () {
        if ($(this).val() === 'Cash' ||$(this).val() === 'SamsungPay' ) {
            $('#paymentinfo').css({
                display: 'none',
            });
        }

        if ($(this).val() === 'Debit') {
            $('#paymentinfo').css({
                margin: '10px',
                display: 'block',
                transition: 'transform 2s step-start',
                transform: 'translateY(0%)',
            });
            $('.numberOrAdresspayment').text('Nhập số thẻ Debit').css({
                display: 'block',
            })
            $('#numberOrAdresspayment').css({
                placeholder: "Nhập số thẻ Debit",
            });
            $('.expirationdate').text('Ngày hết hạn').css({
                display: 'block',
            })
            $('#expirationdate').css({
                display: 'block',
                placeholder: "Nhập ngày hết hạn thẻ Debit",
            })
            $('.cvv').text('Số bảo mật').css({
                display: 'block',
            })
            $('#cvv').css({
                display: 'block',
                placeholder: "Nhập số cvv bảo mật",
            })
            $('#imgQR').css({
                display: 'none',
            })
        }

        /** ////////////////////////////////////  */

        if ($(this).val() === 'Credit') {
            $('#paymentinfo').css({
                margin: '10px',
                display: 'block',
                transition: 'transform 2s step-start',
                transform: 'translateY(0%)',
            });
            $('.numberOrAdresspayment').text('Nhập số thẻ Credit').css({
                display: 'block',
            })
            $('#numberOrAdresspayment').css({
                placeholder: "Nhập số thẻ Credit",
            });
            $('.expirationdate').text('Ngày hết hạn').css({
                display: 'block',
            })
            $('#expirationdate').css({
                display: 'block',
                placeholder: "Nhập ngày hết hạn thẻ Credit",
            })
            $('.cvv').text('Số bảo mật').css({
                display: 'block',
            })
            $('#cvv').css({
                display: 'block',
                placeholder: "Nhập số cvv bảo mật",
            })
            $('#imgQR').css({
                display: 'none',
            })
        }

        /** ////////////////////////////////////  */

        if ($(this).val() === 'MoMo') {
            $('#paymentinfo').css({
                margin: '10px',
                display: 'block',
                transition: 'transform 2s step-start',
                transform: 'translateY(0%)',
            });
            $('.numberOrAdresspayment').text('Nhập số điện thoại đăng ký Momo').css({
                display: 'block',
            })
            $('#numberOrAdresspayment').css({
                placeholder: "Nhập số điện thoại Momo",
            });
            $('.expirationdate').text('Ngày hết hạn').css({
                display: 'none',
            })
            $('#expirationdate').css({
                display: 'none',
                placeholder: "Nhập ngày hết hạn thẻ Credit",
            })
            $('.cvv').text('Số bảo mật').css({
                display: 'none',
            })
            $('#cvv').css({
                display: 'none',
                placeholder: "Nhập số cvv bảo mật",
            })
            $('#textQR').text('Hoặc quét mã QRcode để thanh toán').css({display:'block',fontWeight:'bold',})
            $('#imgQR').css({
                display: 'block',
                width: '500px',
            })
        }

        /** ////////////////////////////////////  */

        if ($(this).val() === 'ZaloPay') {
            $('#paymentinfo').css({
                margin: '10px',
                display: 'block',
                transition: 'transform 2s step-start',
                transform: 'translateY(0%)',
            });
            $('.numberOrAdresspayment').text('Nhập số điện thoại đăng ký ZaloPay').css({
                display: 'block',
            })
            $('#numberOrAdresspayment').css({
                placeholder: "Nhập số điện thoại ZaloPay",
            });
            $('.expirationdate').text('Ngày hết hạn').css({
                display: 'none',
            })
            $('#expirationdate').css({
                display: 'none',
                placeholder: "Nhập ngày hết hạn thẻ Credit",
            })
            $('.cvv').text('Số bảo mật').css({
                display: 'none',
            })
            $('#cvv').css({
                display: 'none',
                placeholder: "Nhập số cvv bảo mật",
            })
            $('#textQR').text('Hoặc quét mã QRcode để thanh toán').css({display:'block',fontWeight:'bold',})
            $('#imgQR').css({
                display: 'block',
                width: '500px',
            })
        }

        /** ////////////////////////////////////  */

        if ($(this).val() === 'Shopee') {
            $('#paymentinfo').css({
                margin: '10px',
                display: 'block',
                transition: 'transform 2s step-start',
                transform: 'translateY(0%)',
            });
            $('.numberOrAdresspayment').text('Nhập số điện thoại đăng ký Shopeepay').css({
                display: 'block',
            })
            $('#numberOrAdresspayment').css({
                placeholder: "Nhập số điện thoại Shopeepay",
            });
            $('.expirationdate').text('Ngày hết hạn').css({
                display: 'none',
            })
            $('#expirationdate').css({
                display: 'none',
                placeholder: "Nhập ngày hết hạn thẻ Credit",
            })
            $('.cvv').text('Số bảo mật').css({
                display: 'none',
            })
            $('#cvv').css({
                display: 'none',
                placeholder: "Nhập số cvv bảo mật",
            })
            $('#textQR').text('Hoặc quét mã QRcode để thanh toán').css({display:'block',fontWeight:'bold',})
            $('#imgQR').css({
                display: 'block',
                width: '500px',
            })
        }

        /** ////////////////////////////////////  */

        if ($(this).val() === 'ApplePay') {
            $('#paymentinfo').css({
                margin: '10px',
                display: 'block',
                transition: 'transform 2s step-start',
                transform: 'translateY(0%)',
            });
            $('.numberOrAdresspayment').text('Nhập địa chỉ iclound thanh toán').css({
                display: 'block',
            })
            $('#numberOrAdresspayment').css({
                placeholder: "Nhập số điện thoại ZaloPay",
            });
            $('.expirationdate').text('Ngày hết hạn').css({
                display: 'none',
            })
            $('#expirationdate').css({
                display: 'none',
                placeholder: "Nhập ngày hết hạn thẻ Credit",
            })
            $('.cvv').text('Số bảo mật').css({
                display: 'none',
            })
            $('#cvv').css({
                display: 'none',
                placeholder: "Nhập số cvv bảo mật",
            })
            $('#imgQR').css({
                display: 'none',
                width: '200px',
            })
        }

        /** ////////////////////////////////////  */

        if ($(this).val() === 'PayPal') {
        $('#paymentinfo').css({
            margin: '10px',
            display: 'block',
            transition: 'transform 2s step-start',
            transform: 'translateY(0%)',
        });
        $('.numberOrAdresspayment').text('Nhập địa chỉ Paypal thanh toán').css({
            display: 'block',
        })
        $('#numberOrAdresspayment').css({
            placeholder: "Nhập số điện thoại ZaloPay",
        });
        $('.expirationdate').text('Ngày hết hạn').css({
            display: 'none',
        })
        $('#expirationdate').css({
            display: 'none',
            placeholder: "Nhập ngày hết hạn thẻ Credit",
        })
        $('.cvv').text('Số bảo mật').css({
            display: 'none',
        })
        $('#cvv').css({
            display: 'none',
            placeholder: "Nhập số cvv bảo mật",
        })
        $('#imgQR').css({
            display: 'none',
            width: '200px',
        })
    }
    });
}
/**
 * *******************************************
 * Lấy dữ liệu từ local Session hiển thị lên row
 * *******************************************
 */
/** ĐÃ ĐƯỢC CHUYỂN LÊN SCRIPT TRONG ORDER*/
/*window.onload = function () {

    // Lấy dữ liệu từ Local Storage
    var selectedProducts = localStorage.getItem('selectedProductsID');

    // Kiểm tra xem dữ liệu có tồn tại trong Local Storage không
    if (selectedProducts) {
        var parsedData = JSON.parse(selectedProducts); // Deserialize the JSON
        var tableBody = document.getElementById("productTableBody");
        if(tableBody){
            tableBody.style.backgroundColor = "white";
            var total = 0;
            // Lặp qua dữ liệu và cập nhật nội dung HTML
            parsedData.forEach(function(product, index) {
                var newRow = tableBody.insertRow();
                var cellIndex = newRow.insertCell(0);
                var cellImage = newRow.insertCell(1);
                var cellName = newRow.insertCell(2);
                var cellPrice = newRow.insertCell(3);
                var cellQuantity = newRow.insertCell(4);
                var cellTotal = newRow.insertCell(5);

                newRow.setAttribute('data-model', product.model);

                //tạo một thẻ input hiden lưu model
                var inputmodel = document.createElement('input');
                inputmodel.type = "hidden"; // Loại input là "hidden"
                inputmodel.value = product.model;
                inputmodel.name = "inputmodel";
                newRow.append(inputmodel)
                //tạo một thẻ input hiden lưu quantity
                var inputquantity = document.createElement('input');
                inputquantity.type = "hidden"; // Loại input là "hidden"
                inputquantity.value = product.quantity;
                inputquantity.name = "inputquantity";
                newRow.append(inputquantity);

                // Tạo một thẻ img
                var img = document.createElement('img');
                img.src = '/Images/' + product.imageName;
                img.alt = 'Product Image';
                img.style.width = '100px';

                cellIndex.textContent = index + 1;
                cellImage.appendChild(img);
                cellName.textContent = product.phoneName;
                cellPrice.textContent = '$'+product.price;
                cellQuantity.textContent = product.quantityorder;
                cellTotal.textContent = '$'+ (parseFloat(product.price)*product.quantityorder).toFixed(2);
                total += parseFloat(product.price)*product.quantityorder;
            });
            $('#totalAmount').text('$'+total.toFixed(2));
            // var x = total.toFixed(2)+'';
            $('.totalAmount').attr('value',total.toFixed(2));
        } else {
            var alertDiv = document.getElementById('nocitceAdmin');
            alertDiv.innerHTML = '<br><br><br><br><br><strong>Thông báo:</strong> Bạn cần đăng nhập bằng tài khoản Khách hàng';
            alertDiv.style.display = 'block';
            alertDiv.style.color = 'red';
            alertDiv.style.fontSize = '3em';
        }

    }
};*/
/**
 * *******************************************
 * gửi dữ liệu mua hàng cho trang chờ xử lý
 * *******************************************
 */

/*
$('#btnSendRequestBuy').click(function () {

});*/

/**
 * *******************************************
 * gửi dữ liệu mua hàng cho trang chờ xử lý
 * (kkông sử dụng - không hoạt động)
 * *******************************************
 */
// // $('#btnSendRequestBuy').click(function () {
//     $(document).ready(function () {
//
// // Đầu tiên, tạo một mảng chứa thông tin sản phẩm từ các thẻ input ẩn
//     var products = [];
//     var rows = document.querySelectorAll('tr[data-model]');
//     rows.forEach(function (row) {
//         var model = row.getAttribute('data-model');
//         var quantity = row.querySelector('input[name="inputquantity"]').value;
//         products.push({model: model, quantity: quantity});
//     });
//
// // Chuyển mảng sản phẩm thành JSON
//     var jsonData = JSON.stringify(products);
//
// // Gửi dữ liệu JSON đến máy chủ
//     fetch('/RequestOrderAPI', {
//         method: 'POST',
//         body: jsonData,
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     })
//     .then(response => response.json())
//     .then(data => {
//         console.log(' Đã gửi được dữ liệu về server');
//     })
//     .catch(error => {
//         console.error('Lỗi: ', error);
//     });
// });

