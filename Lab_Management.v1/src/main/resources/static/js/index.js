
/*        localStorage.setItem('username', username);
        localStorage.setItem('dateAndStatusLabsList', JSON.stringify(dateAndStatusLabsList));
        function getStoredData() {
            username = localStorage.getItem('username');
            dateAndStatusLabsList = localStorage.getItem('dateAndStatusLabsList');
            if (dateAndStatusLabsList) {
                dateAndStatusLabsList = JSON.parse(dateAndStatusLabsList);
                console.log(dateAndStatusLabsList)
            }
        }*/
function setIndex(username, dateAndStatusLabsList, currentDate) {

    var username
    var dateAndStatusLabsList

    /**
     * Không dùng cách lấy thời gian từ js vì lỗi format và khác timezone
     *  var currentDate = new Date().toISOString().split('T')[0];
        var currentDate = new Date().toLocaleString("en-US", { timeZone: "Asia/Ho_Chi_Minh", year: "numeric", month: "2-digit", day: "2-digit" }).split(',')[0].replace(/\//g, '-');
     * */
    $('#date').val(currentDate);
    console.log(currentDate)
    $('#date').attr('min', currentDate);
    $(".btn-booking").click(function () {
        var dateValue = $("#date").val();
        var labId = $(this).attr('data');
        console.log(labId)
        window.location.href = "/Lab/LabDetail/" + labId + "?date=" + (dateValue) + "&username=" + encodeURIComponent(username);
    });
    $(".alert").click(function () {
        var dateValue = $("#date").val();
        var labId = $(this).attr('value');
        console.log(labId)
        window.location.href = "/Lab/LabDetail/" + labId + "?date=" + (dateValue) + "&username=" + encodeURIComponent(username);
    });

    /**********************************************************************************
     *
     * ********************************************************************************/

    var Status = {
        APPROVE: "Đã xác nhận",
        PENDDING: "Đang chờ duyệt",
        CANCEL: "Bị từ chối"
    };

    var btnBooking = $('.btn-booking');


    $('#date').change(function () {
        checkDateAndStatus();
    });

    function setDisplay(i, green, yellow, danger, primary) {
        var yellowAlert = $('.alert.bg-yellow');
        var greenAlert = $('.alert.bg-green');
        var dangerAlert = $('.alert.bg-danger');
        var primaryAlert = $('.alert.bg-primary');

        greenAlert.eq(i).css('display', green);
        yellowAlert.eq(i).css('display', yellow);
        dangerAlert.eq(i).css('display', danger);
        primaryAlert.eq(i).css('display', primary);
    }

    function setEnabledBtn(i) {
        btnBooking.eq(i).removeAttr('disabled')
        btnBooking.eq(i).addClass('btn-success');
        btnBooking.eq(i).removeClass('btn-danger');
    }

    function setDisabledBtn(i) {
        btnBooking.eq(i).attr('disabled', true);
        btnBooking.eq(i).addClass('btn-danger');
        btnBooking.eq(i).removeClass('btn-success');
    }

    function checkDateAndStatus() {
        var dateValue = $("#date").val();
        for (var i = 0; i < dateAndStatusLabsList.length; i++) {
            console.log("alo")
            var dateAndStatusLabs = dateAndStatusLabsList[i];
            if (dateAndStatusLabs.length === 0) {
                setDisplay(i, 'none', 'none', 'none', 'block');
                setEnabledBtn(i);
            }
            for (var j = 0; j < dateAndStatusLabs.length; j++) {
                var dateAndStatusLab = dateAndStatusLabs[j];
                if (dateValue === dateAndStatusLab.date) {
                    switch (dateAndStatusLab.status) {
                        case Status.APPROVE :
                            setDisplay(i, 'block', 'none', 'none', 'none');
                            setDisabledBtn(i);
                            break;
                        case Status.PENDDING :
                            setDisplay(i, 'none', 'block', 'none', 'none');
                            setDisabledBtn(i);
                            break;
                        case Status.CANCEL :

                            setDisplay(i, 'none', 'none', 'none', 'block');
                            setEnabledBtn(i);
                            break;
                        default:
                            break;
                    }
                    break
                } else {
                    setDisplay(i, 'none', 'none', 'none', 'block');
                    setEnabledBtn(i);
                }
            }
        }
    }
    $('#date').trigger('change');
}
