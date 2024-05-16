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