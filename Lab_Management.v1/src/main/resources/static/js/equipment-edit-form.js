$(document).ready(function() {
    $('#submitButton').click(function(event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của nút submit

        if (checkseri()) {
            var confirmed = confirm('Bạn có chắc chắn sửa?');
            if (confirmed) {
                $('form').submit();
            }
        }
    });
});
function checkseri() {
    var seriesData = [];
    var isValid = true;

    $('input[name="series"]').each(function() {
        var value = $(this).val().trim();
        if (value === "" || value === "    ") {
            alert("Vui lòng điền đầy đủ thông tin seri!");
            isValid = false;
            return false;
        }
        seriesData.push(value);
    });

    console.log(seriesData);
    return isValid;
}

function removeSeri(button) {
    var inputseriDiv = button.parentNode;
    inputseriDiv.remove();
}

function addInput(button) {
    var formGroup = button.parentNode;
    var div_inputseri = document.createElement('div');
    div_inputseri.classList.add('inputseri');
    div_inputseri.setAttribute('class', 'inputseri d-flex flex-row flex-wrap justify-content-start');

    var input = document.createElement('input');
    input.type = 'text';
    input.setAttribute('class', 'form-control m-3 col-sm-6');
    input.name = 'series';
    input.required = true;
    input.setAttribute('style', 'font-weight : bold');
    input.setAttribute('placeholder', "seri mới");

    var btnremove = document.createElement('button');
    btnremove.setAttribute('type', 'button');
    btnremove.setAttribute('class', 'btnremove btn btn-primary my-3 col-sm-2');
    btnremove.addEventListener('click', function() {
        removeSeri(this);
    });

    var btnfix = document.createElement('button');
    btnfix.setAttribute('type', 'button');
    btnfix.setAttribute('class', 'btnfix btn btn-primary my-3 col-sm-2');
    btnfix.addEventListener('click', function() {
        Seri2Fix(this);
    });

    var icon_btnremove = document.createElement('i');
    icon_btnremove.setAttribute('class', 'fa fa-times-circle');
    btnremove.appendChild(icon_btnremove);

    var icon_btnfix = document.createElement('i');
    icon_btnfix.setAttribute('class', 'fa fa-wrench');
    btnfix.appendChild(icon_btnfix);

    var select = document.createElement('select');
    select.setAttribute('class', 'form-control m-3 col-sm-6');
    select.name = 'levels';
    select.required = true;

    var options = ['Cấp 1', 'Cấp 2', 'Cấp 3', 'Cấp 4', 'Cấp 5'];
    options.forEach(function(option) {
        var opt = document.createElement('option');
        opt.value = option;
        opt.text = option;
        select.add(opt);
    });

    div_inputseri.appendChild(input);
    div_inputseri.appendChild(btnfix);
    div_inputseri.appendChild(select);
    div_inputseri.appendChild(btnremove);

    formGroup.insertBefore(div_inputseri, button);
}

function addInputseriesfixed(button) {
    var formGroup = button.parentNode;
    var div_inputseri = document.createElement('div');
    div_inputseri.classList.add('inputseri');
    div_inputseri.setAttribute('class', 'inputseri d-flex flex-row flex-wrap justify-content-start');

    var input = document.createElement('input');
    input.type = 'text';
    input.setAttribute('class', 'form-control m-3 col-sm-6');
    input.name = 'seriesfixed';
    input.required = true;
    input.setAttribute('style', 'font-weight : bold');
    input.setAttribute('placeholder', "seri mới");

    var btnremove = document.createElement('button');
    btnremove.setAttribute('type', 'button');
    btnremove.setAttribute('class', 'btnremove btn btn-primary my-3 col-sm-2');
    btnremove.addEventListener('click', function() {
        removeSeri(this);
    });

    var icon_btnremove = document.createElement('i');
    icon_btnremove.setAttribute('class', 'fa fa-times-circle');
    btnremove.appendChild(icon_btnremove);

    div_inputseri.appendChild(input);
    div_inputseri.appendChild(btnremove);

    formGroup.insertBefore(div_inputseri, button);
}
function Seri2Fix(button) {
    // Lấy giá trị seri từ input
    // var inputseri = button.nextElementSibling; //Lấy phần tử Element kế sau
    var inputseri = button.previousElementSibling; //Lấy phần tử Element kế trước
    var seri = "";
    if (inputseri.tagName === 'INPUT') {
        var seri = inputseri.defaultValue;
        console.log(seri);
    }

    // Kiểm tra seri đã tồn tại trong danh sách seri máy đã qua sửa chữa chưa
    var seriFixedList = document.querySelectorAll('input[name="seriesfixed"]');
    // console.log(seriFixedList);
    var isExist = false;
    for (var i = 0; i < seriFixedList.length; i++) {
        console.log(seriFixedList[i].defaultValue);
        if (seriFixedList[i].defaultValue === seri) {
            isExist = true;
            break;
        }
    }

    // Nếu seri chưa tồn tại trong danh sách seri máy đã qua sửa chữa
    if (!isExist) {
        // Tạo một div mới để chứa seri
        var div_inputseri_fixed = document.createElement('div');
        div_inputseri_fixed.setAttribute('class', 'inputseri d-flex flex-row flex-wrap justify-content-start');

        // Tạo input chứa seri
        var input_fixed = document.createElement('input');
        input_fixed.type = 'text';
        input_fixed.setAttribute('class', 'form-control m-3 col-sm-6');
        input_fixed.name = 'seriesfixed';
        input_fixed.required = true;
        input_fixed.setAttribute('style', 'font-weight : bold');
        input_fixed.setAttribute('value', seri);
        // input_fixed.disabled = true;

        // Tạo nút Remove
        var btnremove_fixed = document.createElement('button');
        btnremove_fixed.setAttribute('type', 'button');
        btnremove_fixed.setAttribute('class', 'btnremove btn btn-primary my-3 col-sm-2');
        btnremove_fixed.addEventListener('click', function() {
            removeSeri(this);
        });

        var icon_btnremove_fixed = document.createElement('i');
        icon_btnremove_fixed.setAttribute('class', 'fa fa-times-circle');
        btnremove_fixed.appendChild(icon_btnremove_fixed);

        // Gắn input và nút Remove vào div
        div_inputseri_fixed.appendChild(input_fixed);
        div_inputseri_fixed.appendChild(btnremove_fixed);

        // Tìm đến danh sách seri máy đã qua sửa chữa và chèn div mới vào cuối danh sách
        var seriesFixedDiv = document.querySelector('.seriesFixedDiv');
        seriesFixedDiv.appendChild(div_inputseri_fixed);
    } else {
        // Hiển thị thông báo cho người dùng biết seri đã có trong danh sách seri máy đã qua sửa chữa
        alert('Seri đã tồn tại trong danh sách seri máy đã qua sửa chữa.');
    }

    // var inputseriDiv = button.parentNode;
    // inputseriDiv.remove();
}