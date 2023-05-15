$(document).ready(function () {
    myOrderList(0);
    myBoardList(0);
});

function myOrderList(page) {
    const clientCode = document.getElementById("orderListClientCode").value;

    data = {
        clientCode: clientCode,
        page: page
    }

    // console.log(data);

    $.ajax({
        url: `/getClientOrder`,
        method: "POST",
        timeout: 0,
        data: JSON.stringify(data),
        contentType: "application/json"
    }).done(response => {
        $('#orderList').empty();
        $('#orderListPage').empty();

        // console.log(response.list);
        // console.log('totalPage : ' + response.totalPage);

        response.list.forEach(order => {
            let html = `<tr>
                    <td> <input type="button" id="orderCode" value=${order.orderCode}> </td>
                    <td><p>${order.buyerCode}</p></td> `;
            if(`${order.orderState}`==='수거완료'){

                html += ` <td><p>${order.sellerCode}</p></td>`;
            }else{
                html += ` <td><p>담당 직원을 배정중입니다</p></td>`;

            }

            html+=`
                    <td> <p>${order.orderAddress}</p></td>
                    <td> <p>${order.orderTotalPrice}원</p> </td>
                    <td><p>${order.orderRequest}</p></td><td>  <p>${order.orderStartDate}</p> </td>`;
            if (`${order.orderEndDate}` === null || `${order.orderEndDate}`==='null') {
                html += `  <td> <p>-</p> </td>  `;

            } else {
                html += `  <td> <p>${order.orderEndDate}</p></td> `;

            }
            html += `<td>  <p>${order.orderState}</p>  </td>
           </tr>`;


            $('#orderList').append(html);
        });
        const totalPage = response.totalPage;
        // btn
        for (let i = 0; i<totalPage; i++) {
            $('#orderListPage').append(`
                <span id="pageIndexBtn" onclick="myOrderList(${i})">${i+1}</span>
            `)
        }
    });
}

function myBoardList(page) {
    const loginName = document.getElementById("loginName").value;

    $.ajax({
        url: "/searchGetBoard",
        method: "POST",
        timeout: 0,
        data: JSON.stringify({
            "searchType": "WRITER",
            "searchText": loginName,
            "page": page
        }),
        contentType: "application/json; charset=utf-8"
    }).done(response => {
        $('#boardList').empty();
        $('#boardListPage').empty();
         response.list.forEach(board => {
             const dateOptions = {
                 month: '2-digit',
                 day: '2-digit',
                 timeZone: 'Asia/Seoul',
             };
             const formattedDate = new Intl.DateTimeFormat('ko-KR', dateOptions).format(new Date(board.boardGenerationDate));
             $('#boardList').append(`
                <tr>
                    <td>
                        <a value=${board.boardCode}>${board.boardCode}</a>
                    </td>
                    <td>
                        <a value=${board.boardWriter} onclick="location.href='/boardDetail?boardCode=${board.boardCode}'">${board.boardWriter}</a>
                    </td>
                    <td>
                        <a value=${board.boardTitle} onclick="location.href='/boardDetail?boardCode=${board.boardCode}'">${board.boardTitle}</a>
                    </td>                  
                    <td>${formattedDate.replace('.', '/').replace('.', '').replace(' ', '')}</td>
                </tr>
            `);
         });
        for (let i = 0; i < response.size; i++) {
            $('#boardListPage').append(`
                <span id="pageIndexBtn" onclick="myBoardList(${i})">${i+1}</span>
            `);
        }
    });
}


