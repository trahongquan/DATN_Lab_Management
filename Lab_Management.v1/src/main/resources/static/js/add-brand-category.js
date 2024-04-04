document.addEventListener("DOMContentLoaded", function () {
    var checkboxBrand = document.getElementById('checkboxBrand');
    var checkboxCategory = document.getElementById('checkboxCategory');
    var deleteBrandButton = document.getElementById('deleteBrandButton');
    var deleteCategoryButton = document.getElementById('deleteCategoryButton');

    // Kiểm tra trạng thái ban đầu của checkbox và gán giá trị disabled cho nút delete
    setDisabledState(deleteBrandButton, checkboxBrand.checked);
    setDisabledState(deleteCategoryButton, checkboxCategory.checked);

    // Thêm sự kiện cho checkbox
    checkboxBrand.addEventListener("change", function () {
        setDisabledState(deleteBrandButton, this.checked);
    });

    checkboxCategory.addEventListener("change", function () {
        setDisabledState(deleteCategoryButton, this.checked);
    });
});

// Hàm để gán giá trị disabled cho nút delete
function setDisabledState(deleteButton, isChecked) {
    if (checkboxBrand && checkboxBrand.checked || checkboxCategory && checkboxCategory.checked) {
        deleteButton.disabled = !isChecked;
    }
}