
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

});