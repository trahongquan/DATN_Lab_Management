function enableImportButton() {
    var fileInput = document.getElementById('fileInput');
    var importButton = document.getElementById('importButton');

    // Kiểm tra xem có file được chọn hay không
    if (fileInput.files.length > 0) {
        importButton.disabled = false; // Kích hoạt nút import
    } else {
        importButton.disabled = true; // Vô hiệu hóa nút import
    }
}