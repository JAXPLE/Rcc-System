$(document).ready(function () {
    getAllBoard(0)
    pageList();
});

function getAllBoard(page) {
    $.ajax({
        url: `/getAllOrder?page=${page}`,
        method: "GET",
        timeout: 0
    }).done(response => {
        $('#orderList').empty();
        response.forEach(order => {

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
            <td><input type = "button" onclick="checkState(${order.orderCode})"  value="수거처리" class="but"> </td></tr>`;


            $('#orderList').append(html);
        });
    });
}

function pageList() {
    $.ajax({
        url: "/getTotalPageSize",
        method: "GET",
        timeout: 0
    }).done(size => {
        $('#orderPage').empty();
        for (let i = 0; i < size; i++) {
            $('#orderPage').append(`
                <span id="pageIndexBtn" onclick="getAllBoard(${i})">${i + 1}</span>
            `);
        }
    });
}

function checkState(orderCode) {

    var UP;
    UP = confirm("수거 완료 처리 시키겠습니까?");
    if (UP) {
        $.ajax({
            url: "/updateOrderState",
            method: "POST",
            timeout: 0,
            data: {
                orderCode: orderCode,
            }
        }).done(response => {
            alert("처리하였습니다");
            location.href = "adminPage"
        })
    }

}