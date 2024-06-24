$(document).ready(function() {
    $('#submitAddEquid').click(function(event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của nút submit
        console.log('hellll');
        if (checkseri()) {
            var confirmed = confirm('Bạn có chắc chắn thêm thiết bị này?');
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

    // var input = document.createElement('input');
    // input.type = 'text';
    // input.setAttribute('class', 'form-control m-3 col-sm-6');
    // input.name = 'series';
    // input.required = true;
    // input.setAttribute('style', 'font-weight : bold');
    // input.setAttribute('placeholder', "seri mới");

    var btnremove = document.createElement('button');
    btnremove.setAttribute('type', 'button');
    btnremove.setAttribute('class', 'btnremove btn btn-primary my-3 col-sm-2');
    btnremove.addEventListener('click', function() {
        removeSeri(this);
    });
    var icon_btnremove = document.createElement('i');
    icon_btnremove.setAttribute('class', 'fa fa-times-circle');
    btnremove.appendChild(icon_btnremove);

    var div_select = document.createElement('div');
    div_select.setAttribute('class', 'd-flex flex-row flex-wrap justify-content-start col-sm-6');

    var input_date = document.createElement('input');
    input_date.type = 'date';
    input_date.setAttribute('class', 'form-control m-2 col-sm-6');
    input_date.name = 'using';
    input_date.required = true;

    var select = document.createElement('select');
    select.setAttribute('class', 'form-control m-2 col-sm-4');
    select.name = 'levels';
    select.required = true;

    var options = ['Cấp 1', 'Cấp 2', 'Cấp 3', 'Cấp 4', 'Cấp 5'];
    options.forEach(function(option) {
        var opt = document.createElement('option');
        opt.value = option;
        opt.text = option;
        select.add(opt);
    });

    div_select.appendChild(input_date);
    div_select.appendChild(select);

    div_inputseri.appendChild(input);
    div_inputseri.appendChild(btnremove);
    div_inputseri.appendChild(div_select);

    formGroup.insertBefore(div_inputseri, button);
}