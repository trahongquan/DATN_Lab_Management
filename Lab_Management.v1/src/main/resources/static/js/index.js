
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
        checkcurrentDate(currentDate);
        checkDateAndStatus();
    });

    function checkcurrentDate(currentDate) {
        if ($('#date').val() === currentDate) {
            $('.bg-danger-subtle').css({'display':'block'})
        } else {
            $('.bg-danger-subtle').css({'display':'none'})
        }
    }
    
    function setDisplay(i, green, yellow, danger, primary/*, dangerSubtleAlert*/) {
        var yellowAlert = $('.alert.bg-yellow');
        var greenAlert = $('.alert.bg-green');
        var dangerAlert = $('.alert.bg-danger');
        var primaryAlert = $('.alert.bg-primary');

        greenAlert.eq(i).css('display', green);
        yellowAlert.eq(i).css('display', yellow);
        dangerAlert.eq(i).css('display', danger);
        primaryAlert.eq(i).css('display', primary);
        
        if (danger === 'block' || primary === 'block'){
            greenAlert.eq(i).removeClass('alert-link');
            yellowAlert.eq(i).removeClass('alert-link');
            dangerAlert.eq(i).removeClass('alert-link');
            primaryAlert.eq(i).addClass('alert-link');
        }
        if (green === 'block' || yellow === 'block'){
            greenAlert.eq(i).removeClass('alert-link');
            yellowAlert.eq(i).removeClass('alert-link');
            dangerAlert.eq(i).removeClass('alert-link');
            primaryAlert.eq(i).removeClass('alert-link');
        }
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
        console.log('dateValue='+dateValue)
        console.log('currentDate='+currentDate)
        console.log(dateValue === currentDate)
        for (var i = 0; i < dateAndStatusLabsList.length; i++) {
            var dateAndStatusLabs = dateAndStatusLabsList[i];
            if (dateAndStatusLabs.length === 0) {
                // setDisplay(i, 'none', 'none', 'none', 'block');
                // setEnabledBtn(i);
                if (dateValue === currentDate){
                    console.log('chạy vào else')
                    setDisplay(i, 'none', 'none', 'block', 'none');
                    setDisabledBtn(i);
                } else {
                    setDisplay(i, 'none', 'none', 'none', 'block');
                    setEnabledBtn(i);
                }
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
                            if (dateValue === currentDate){
                                setDisplay(i, 'none', 'none', 'block', 'none');
                                setDisabledBtn(i);
                            } else {
                                setDisplay(i, 'none', 'none', 'none', 'block');
                                setEnabledBtn(i);
                            }
                            break;
                        default:
                            break;
                    }
                    break
                } else {
                    if (dateValue === currentDate){
                        console.log('chạy vào else')
                        setDisplay(i, 'none', 'none', 'block', 'none');
                        setDisabledBtn(i);
                    } else {
                        setDisplay(i, 'none', 'none', 'none', 'block');
                        setEnabledBtn(i);
                    }
                    // setDisplay(i, 'none', 'none', 'none', 'block');
                    // setEnabledBtn(i);
                }
            }
        }
        /*if(dateValue===currentDate){
            for (var i = 0; i < dateAndStatusLabsList.length; i++){
                setDisplay(i, 'none', 'none', 'block', 'none')
                setDisabledBtn(i)
            }
        }*/
    }
    $('#date').trigger('change');
}
function Nextday(currentDate) {
    $('#next-day').click(function() {
        var date = new Date(currentDate);
        console.log(date)
        date.setDate(date.getDate() + 1);
        console.log(date)
        var formattedDate = formatDate(date); // Hàm formatDate() chuyển đổi định dạng ngày tháng nếu cần thiết
        $('#date').val(formattedDate);
        $('#date').trigger('change');
    });
}
function formatDate(date) {
    var year = date.getFullYear();
    console.log(year)
    var month = (date.getMonth() + 1).toString().padStart(2, '0');
    console.log(month)
    var day = date.getDate().toString().padStart(2, '0');
    console.log(day)
    return year + '-' + month + '-' + day;
}