function clickDetail(labsOnLineAndScores, id) {
    console.log(labsOnLineAndScores)
    var tbody = $('#tbody');
    tbody.html('');
    var index = 0;
    for (var i = 0; i < labsOnLineAndScores.length; i++) {
        var score = labsOnLineAndScores[i];
            console.log(typeof (score.lab.id))
            console.log(typeof (id))
        if(score.lab.id === id){
            var row = $('<tr></tr>');
            // STT
            var sttCell = $('<td></td>').text(index + 1);
            row.append(sttCell);

                // Số lượng
            var quantityCell = $('<td></td>');
            for (var j = 0; j < score.quantity.length; j++) {
                var qty = score.quantity[j];
                var quantityItem = $('<p></p>').text(qty);
                quantityCell.append(quantityItem);
                console.log('đã qua đây');
            }
            row.append(quantityCell);

                // Phân loại
            var typenameCell = $('<td></td>');
            for (var k = 0; k < score.typeName.length; k++) {
                var name = score.typeName[k];
                var typenameItem = $('<p></p>').text(name);
                typenameCell.append(typenameItem);
            }
            row.append(typenameCell);

                // Điểm số
            var scoresCell = $('<td></td>');
            for (var l = 0; l < score.scores.length; l++) {
                var sco = score.scores[l];
                var scoreItem = $('<p></p>').html('<strong class="text-danger">' + sco + '</strong>');
                scoresCell.append(scoreItem);
            }
            row.append(scoresCell);

            tbody.append(row);
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