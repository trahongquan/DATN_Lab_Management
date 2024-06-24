
$(document).ready(function() {
    var Status = {
        APPROVE: "Đã xác nhận",
        PENDDING: "Đang chờ duyệt",
        CANCEL: "Bị từ chối"
    };
    var dateInfo = $('.date-info');
    dateInfo.each(function() {
        var confirmStatus = $(this).attr('value');
        if (confirmStatus === Status.APPROVE) {
            $(this).css({
                'color' : 'green'
            })
        } else if (confirmStatus === Status.CANCEL) {
            $(this).css({
                'color' : 'red'
            })
        } else if (confirmStatus === Status.PENDDING) {
            $(this).css({
                'color' : 'rgb(203 154 42)'
            })
        }
    });

    $(".btn-detail-booking").click(function () {
        var dateValue = $("#date").val();
        var bookingId = $(this).attr('data');
        var username = $(this).attr('data-user');
        window.location.href = "/Lab/myBookingDetail/" + bookingId + "?username=" + username;
    });

    /** Cài đặt ẩn hiện bảng hiển thị */
    var toggleStatus = false;
    $('#switch-card-table').click(function() {
        if (toggleStatus) {
            $('#card-booking').fadeOut(300, function() {
                $('#table-booking').fadeIn(300);
            });
            $('#switch-card-table').text('Chuyển sang xem dạng thẻ')
        } else {
            $('#table-booking').fadeOut(300, function() {
                $('#card-booking').fadeIn(300);
            });
            $('#switch-card-table').text('Chuyển sang xem dạng bảng')
        }
        toggleStatus = !toggleStatus;
    });
    $('#switch-card-table').trigger('click')
});