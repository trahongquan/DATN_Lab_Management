$(document).ready(function() {
    checkuser(username);

    $('#date').val(date);
    $('#experiment_type').prop('disabled','true');
    $('#experiment_report').prop('disabled','true');
    $('#lesson').prop('disabled','true');
    $('#lesson_div').hide();

    $('#experiment_group').change(function () {
        var groupId = $(this).val();
        SetExperiment_Type(groupId);
        tempGroupId = groupId;
        $('#experiment_report').prop('disabled','true');
        CheckLesson(ex_group, ex_tpye, ex_report);
    });
    $('#experiment_type').change(function () {
        var typeId = $(this).val();
        SetExperiment_Report(tempGroupId, typeId)
        CheckLesson(ex_group, ex_tpye, ex_report);
    })

    if(experiment_group != 0 && experiment_group != null) $('#experiment_group').val(experiment_group);
    $('#experiment_group').trigger('change');
    if(experiment_type != 0 && experiment_type != null) $('#experiment_type').val(experiment_type);
    $('#experiment_type').trigger('change');
    if(experiment_report != 0 && experiment_report != null) $('#experiment_report').val(experiment_report)
    $('#experiment_report').trigger('change');
})
function SetExperiment_Type(groupId) {
    $('#experiment_type').removeAttr('disabled');
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
    $('#experiment_report').removeAttr('disabled');
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
function SetExperiment(experiment_group, experiment_type, experiment_report){
    console.log(experiment_group + " - " + experiment_type + " - " + experiment_report);
    if(experiment_group!=0 && experiment_type !=0 && experiment_report!=0){
        setTimeout(function () {
            $('#experiment_group').val(experiment_group);
        }, 300)
        setTimeout(function () {
            $('#experiment_type').val(experiment_type);
        }, 300)
        setTimeout(function () {
            $('#experiment_report').val(experiment_report);
        }, 300)
        console.log("đã chọn")
    }
}

function StringToList(inputString) {
    var cleanString = inputString.replace(/\[|\]/g, '');
    var stringArray = cleanString.split(',');
    var trimmedArray = stringArray.map(function(item) {
        return item.trim();
    });
    return trimmedArray;
}

/*******************************/


var ex_tpye = $('#experiment_type');
var ex_group = $('#experiment_group');
var ex_report = $('#experiment_report');
var lessonElement = $('#lesson');
var lessonDiv = $('#lesson_div');

function CheckLesson(ex_group, ex_tpye, ex_report) {
    // if(ex_group.value())
    var ex_groupId = ex_group.val();
    var ex_reportId = ex_report.val();

    var result_ex_group = $.grep(experimentGroups, function(group) {
        return group.id == ex_groupId;
    });
    var result_ex_report = $.grep(experimentReports, function(report) {
        return report.id == ex_reportId;
    });

    if(result_ex_group[0].groupName==="Đào tạo" && result_ex_report[0].reportType === "Giờ khai thác") {
        console.log("Tìm thấy.");
        lessonElement.removeAttr('disabled');
        lessonDiv.show();
    } else {
        console.log("Không tìm thấy");
        lessonDiv.hide();
        lessonElement.prop('disabled', 'true');
    }
}

lessonElement.change(function () {
    SetLesson();
});

function SetLesson() {
    var lessonId = lessonElement.val();
    var result_lesson = $.grep(lessons, function(lesson) {
        return lesson.id == lessonId;
    });
    console.log(result_lesson[0])
    // $('#name').empty();
    $('#name').val(result_lesson[0].name);
    $('#work_time').val(result_lesson[0].workTime)
}


/***********************/

function addEqui() {
    var seriesElement = $('#chosseSeries');

    // Thêm thuộc tính CSS để phóng to phần tử
    seriesElement.css({
        'transform': 'scale(1.2)',
        'transition': 'transform 0.3s ease-in-out', // Thêm transition
    });

    // Xóa thuộc tính CSS sau 2 giây sử dụng setTimeout
    setTimeout(function () {
        seriesElement.css({
            'transform': 'none',
        });
    }, 1000);

    // Gọi sự kiện focus để mở danh sách các lựa chọn
    seriesElement.focus();
}
