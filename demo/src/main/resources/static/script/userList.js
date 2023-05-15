//
// $(document).ready(function() {
//     $.ajax({
//         url: "/v1/serch/member",
//         "method": "GET",
//         "timeout": 0,
//         success: function(data) {
//             for(var i = 0; i < data.length; i++) {
//                 var user = data[i];
//                 $('#userList').append('<tr>' + '<td>' + user.id+ '</td>');
//                 $('#userList').append('<td>' + user.pass+ '</td>');
//                 $('#userList').append('<td>' + user.name+ '</td>'+'</tr>');
//
//             }
//         }
//     });
// });
$(document).ready(function() {
    $.ajax({
        url: "/v1/serch/member",
        "method": "GET",
        "timeout": 0,
        success: function(data) {
            alert("작동 완료")
            const html = '';
            for (const i = 0; i < data.length; i++) {

                const user = data[i];
                html += '<tr><td>' + user.id + '</td><td>' + user.pass + '</td><td>' + user.name + '</td></tr>';

            }
            $('#userList').append(html);
        }
    });
});
