
/** ************* Popup **************/
$(document).ready(function() {
    $(".btn-edit").click(function() {
        var score = $(this).attr('data-score');
        var scoreid = $(this).attr('data-id');
        console.log(score)
        console.log(scoreid)
        $("#score").val(score);
        $("#id").val(scoreid);
        $(".popup").slideDown(300);
    });

    $(".close-btn").click(function() {
        $(".popup").slideUp(300);
    });

    // Optional: Close the popup when clicking outside the content
    $(window).click(function(event) {
        if ($(event.target).hasClass("popup")) {
            $(".popup").slideUp(300);
        }
    });
});

/** ************* Tạo danh sách phụ thuộc **************/
function TaoDS_PhuThuoc(date, experimentTypes, experimentReports, scores) {
    var tempGroupId
    $('#date').val(date);
    // $('#experiment_type').prop('disabled','true');
    // $('#experiment_report').prop('disabled','true');

    $('#experiment_group').change(function () {
        var groupId = $(this).val();
        SetExperiment_Type(groupId);
        tempGroupId = groupId;
        // $('#experiment_report').prop('disabled','true');
        $('#experiment_type').trigger('change')
        var selectedOptionText = $(this).find("option:selected").text();
        triggerTable(selectedOptionText);
        checkScore(scores);
    });
    $('#experiment_type').change(function () {
        var typeId = $(this).val();
        SetExperiment_Report(tempGroupId, typeId)
        var selectedOptionText = $(this).find("option:selected").text();
        triggerTable(selectedOptionText);
        checkScore(scores);
    })
    $('#experiment_report').change(function () {
        var selectedOptionText = $(this).find("option:selected").text();
        triggerTable(selectedOptionText);
        checkScore(scores);
    })
    $('#experiment_group').trigger('change');
    $('#experiment_type').trigger('change');
    $('#experiment_report').trigger('change');
}

function SetExperiment_Type(groupId) {
    // $('#experiment_type').removeAttr('disabled');
    // Xóa tất cả các option hiện tại trong select experiment_type
    $('#experiment_type').empty();
    // Thêm các option mới từ danh sách loại hình thí nghiệm nhận được
    experimentTypes.forEach(experimentType => {
        if(experimentType.experimentGroupId == groupId){
        $('#experiment_type').append($('<option>', {
            value: experimentType.id,
            text: experimentType.typeName
        }));
    }
});
}
function SetExperiment_Report(groupId, typeId) {
    // $('#experiment_report').removeAttr('disabled');
    $('#experiment_report').empty();
    var experimentTypeIdList
    // Thêm các option mới từ danh sách loại hình thí nghiệm nhận được
    experimentReports.forEach(experimentReport => {
        experimentTypeIdList = StringToList(experimentReport.experimentTypeId);
    if(experimentReport.experimentGroupId == groupId && experimentTypeIdList.includes(typeId)){
        $('#experiment_report').append($('<option>', {
            value: experimentReport.id,
            text: experimentReport.reportType
        }));
    }
});
}
function StringToList(inputString) {
    var cleanString = inputString.replace(/\[|\]/g, '');
    var stringArray = cleanString.split(',');
    var trimmedArray = stringArray.map(function(item) {
        return item.trim();
    });
    return trimmedArray;
}


/** ************* Check Score **************/
// Lấy giá trị của experimentReportId, experimentTypeId, experimentGroupId từ view HTML
function checkScore(scores) {
    var experimentReportId = parseInt($('#experiment_report').val()); // Giá trị ví dụ
    var experimentTypeId =  parseInt($('#experiment_type').val()); // Giá trị ví dụ
    var experimentGroupId =  parseInt($('#experiment_group').val()); // Giá trị ví dụ
    // Tìm bản ghi trong danh sách scores
    // var filteredScores = [];

    var filteredScores = scores.filter(function(score) {
        return (
            score.experimentReportId === experimentReportId &&
            score.experimentTypeId === experimentTypeId &&
            score.experimentGroupId === experimentGroupId
        );
    });
    console.log(filteredScores);
    if(filteredScores.length != 0){
        $('#btnAdd').attr('disabled', true)
    } else {
        $('#btnAdd').attr('disabled', false)
    }
    filteredScores.forEach(function(filteredScore) {
        console.log(filteredScore);
    });
}
function triggerTable(selectedOptionText) {
    $('input[type="search"]').val(selectedOptionText).focus()
    $('input[type="search"]').trigger('input').trigger('change')}