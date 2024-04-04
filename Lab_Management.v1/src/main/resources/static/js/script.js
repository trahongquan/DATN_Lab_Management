/**
 * *******************************************
 * Truyền id sang model xóa:
 * *******************************************
 */
/*{
$(document).ready(function () {
    $('.delete-button').on('click', function () {
        var employeeId = $(this).data('employee-id');
        $('#deleteEmployeeButton').attr('href', '/employees/delete/' + employeeId);
        $('#deleteConfirmationModal').modal('show');
    });
});
}*/
/**
 * *******************************************
 * Thêm sp vào giỏ hàng khi nhập vào icon giỏ hàng:
 * *******************************************
 */
$('.iconcart').on('click', function () {
    var id = $(this).closest('div').attr('data-phoneid');
    console.log(typeof id);
    console.log(id);
    console.log('Đã click ở đây, nhưng chưa xử lý hàm')
});
/**
 * *******************************************
 * ghi thêm bản ghi phone vào cookie và chyển tới view Cart
 * *******************************************
 */

