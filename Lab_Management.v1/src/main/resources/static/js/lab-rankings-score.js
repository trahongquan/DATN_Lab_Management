function clickDetail(labsOnLineAndScores, id) {
    // console.log(labsOnLineAndScores)
    var tbody = $('#tbody');
    tbody.html('');
    var index = 0;
    for (var i = 0; i < labsOnLineAndScores.length; i++) {
        var score = labsOnLineAndScores[i];
        if(score.lab.id === id){
        console.log(labsOnLineAndScores[i])
            for (var j = 0; j < score.typeName.length; j++) {
                var row = $('<tr></tr>');
                // STT
                var sttCell = $('<td></td>').text(j + 1);
                row.append(sttCell);

                // Phân loại
                var typenameCell = $('<td></td>');
                    var name = score.typeName[j];
                    var nameParts = name.split("-");
                    var partA = nameParts[0];
                    var partB = nameParts[1];

                    var typenameItem = $('<p></p>');

                    var partASpan = $('<span></span>').text(partA);
                    var partBSpan = $('<span></span>').text(partB);

                    var separator = $('<span></span>').text(' - ');

                    var partAStrong = $('<strong></strong>').append(partASpan);
                    partAStrong.addClass('text-primary');
                    partBSpan.addClass('text-info');

                    typenameItem.append(partAStrong);
                    typenameItem.append(separator);
                    typenameItem.append(partBSpan);

                    typenameCell.append(typenameItem);
                row.append(typenameCell);

                // Số lượng
                var quantityCell = $('<td></td>');
                    var qty = score.quantity[j];
                    var quantityItem
                    if(partA.trim() === 'Giờ khai thác'){
                        var hour = score.hour;
                        var str = qty + ' (Tổng: ' + hour+ ' giờ)';
                        quantityItem = $('<p></p>').text(str);
                    } else {quantityItem = $('<p></p>').text(qty)}
                    quantityCell.append(quantityItem);
                row.append(quantityCell);

                    // Điểm số
                var scoresCell = $('<td></td>');
                    var sco
                    if(partA.trim() === 'Giờ khai thác'){
                        sco = (score.scores[j]*hour).toFixed(1);
                    } else {sco = (score.scores[j]*qty).toFixed(1);}
                    var scoreItem = $('<p></p>').html('<strong class="text-danger">' + sco + '</strong>');
                    scoresCell.append(scoreItem);
                row.append(scoresCell);

                /** append vào body table*/
                tbody.append(row);
            }
        }
    }
}

$(".close-btn").click(function() {
    $(".popup").slideUp(300);
});

// Optional: Close the popup when clicking outside the content
$(window).click(function(event) {
    if ($(event.target).hasClass("popup")) {
        $(".popup").slideUp(300);
    }
});


/** quick choose time */
{
    $(document).ready(function () {
        /** Cài đặt trạng thái các select khi tải đến trang mà vẫn giữ theo trạng thái của experimentYesNo*/
        var experimentYesNo = $('#experiment_YesNo');
        var selectInputEx = $('#experiment_group, #experiment_type, #experiment_report');
        setTimeout(function () {
            SetUpStatusExperimentSelect(selectInputEx);
        }, 300);

        experimentYesNo.change(function () {
            SetUpStatusExperimentSelect(selectInputEx);
        });
        function SetUpStatusExperimentSelect(selectInputEx){
            if (experimentYesNo.prop('checked')) {
                selectInputEx.each(function () {
                    $(this).removeAttr('disabled');
                    $(this).addClass('font-weight-bold')
                })
            } else {
                selectInputEx.each(function () {
                    $(this).attr('disabled', 'true');
                    $(this).removeClass('font-weight-bold')
                })

            }
        }

/*****************************************************************************************/

        $('#btn-yesterday').click(function () {
            var endDate = new Date();
            var startDate = new Date();
            startDate.setDate(startDate.getDate() - 2);
            endDate.setDate(startDate.getDate() + 1);
            redirectToProfitReport(0, startDate, endDate, experiment_YesNo);
            $('#start_date').val(formatDateTime(startDate));
            $('#end_date').val(formatDateTime(endDate));
        });

        $('#btn-1-day').click(function () {
            var endDate = new Date();
            var startDate = new Date();
            startDate.setDate(startDate.getDate() - 1);
            redirectToProfitReport(0, startDate, endDate, experiment_YesNo);
            $('#start_date').val(formatDateTime(startDate));
            $('#end_date').val(formatDateTime(endDate));
        });

        $('#btn-1-week').click(function () {
            var endDate = new Date();
            var startDate = new Date();
            startDate.setDate(startDate.getDate() - 7);
            redirectToProfitReport(0, startDate, endDate, experiment_YesNo);
            $('#start_date').val(formatDateTime(startDate));
            $('#end_date').val(formatDateTime(endDate));
            console.log(startDate)
            console.log(endDate)
        });

        $('#btn-1-month').click(function () {
            var endDate = new Date();
            var startDate = new Date();
            startDate.setMonth(startDate.getMonth() - 1);
            $('#start_date').val(formatDateTime(startDate));
            $('#end_date').val(formatDateTime(endDate));
            redirectToProfitReport(0, startDate, endDate, experiment_YesNo);
        });

        $('#btn-3-months').click(function () {
            var endDate = new Date();
            var startDate = new Date();
            startDate.setMonth(startDate.getMonth() - 3);
            $('#start_date').val(formatDateTime(startDate));
            $('#end_date').val(formatDateTime(endDate));
            redirectToProfitReport(0, startDate, endDate, experiment_YesNo);
        });

        $('#btn-6-months').click(function () {
            var endDate = new Date();
            var startDate = new Date();
            startDate.setMonth(startDate.getMonth() - 6);
            $('#start_date').val(formatDateTime(startDate));
            $('#end_date').val(formatDateTime(endDate));
            redirectToProfitReport(0, startDate, endDate, experiment_YesNo);
        });

        $('#btn-1-year').click(function () {
            var endDate = new Date();
            var startDate = new Date();
            startDate.setFullYear(startDate.getFullYear() - 1);
            $('#start_date').val(formatDateTime(startDate));
            $('#end_date').val(formatDateTime(endDate));
            redirectToProfitReport(0, startDate, endDate, experiment_YesNo);
        });

        $('#btn-1-year-2').click(function () {
            redirectToProfitReport(0, "","", experiment_YesNo);
        });

        $('#btn-kk').click(function () {
            var endDate = new Date();
            var startDate = new Date();
            startDate.setMonth(0);
            startDate.setDate(1);
            $('#start_date').val(formatDateTime(startDate));
            $('#end_date').val(formatDateTime(endDate));
            redirectToProfitReport(0, startDate, endDate, experiment_YesNo);
        });

        function redirectToProfitReport(labId, startDate, endDate, experiment_YesNo) {
            console.log(experiment_YesNo);
            if(labId==0) {
                var url = '/Lab/admin/LabRankings';
            } else {
                var url = '/Lab/admin/room/showFormForUpdate/'+labId;
            }
            var formattedStartDate = formatDateTimeX(startDate);
            var formattedEndDate = formatDateTimeX(endDate);
            url += '?startDate=' + formattedStartDate + '&endDate=' + formattedEndDate + '&experiment_YesNo=' + experiment_YesNo;
            window.location.href = url;
        }

        function formatDateTimeX(date) {
            var year = date.getFullYear();
            var month = ('0' + (date.getMonth() + 1)).slice(-2);
            var day = ('0' + date.getDate()).slice(-2);
            var hours = ('0' + date.getHours()).slice(-2);
            var minutes = ('0' + date.getMinutes()).slice(-2);
            return year + '-' + month + '-' + day;
            // return year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
        }
        function formatDateTime(date) {
            var year = date.getFullYear();
            var month = ('0' + (date.getMonth() + 1)).slice(-2);
            var day = ('0' + date.getDate()).slice(-2);
            var hours = ('0' + date.getHours()).slice(-2);
            var minutes = ('0' + date.getMinutes()).slice(-2);
            // return year + '-' + month + '-' + day;
            return year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
        }
    });
}