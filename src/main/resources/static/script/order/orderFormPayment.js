let orderTypeCode = [];
let orderTypeCount = [];
let orderTypeName = [];
let orderTypePrice = [];
//서버에서 오더 타입 받아와서 저장하는 리스트
const orderTypeList = [];

async function getTypeList() {

    $.ajax({
        url: "/orderTypeList",
        "method": "GET",
        "timeout": 0,
        //위의 리스트가 존재하면 그걸 받아와서 자바스크립트 내 배열에 대입하기
        success: function (data) {

            console.log(data);

            for (let i = 0; i < data.length; i++) {
                const orderType = data[i];
                if (isInArray(orderType.orderTypeCode)) {

                    orderTypePrice.push(orderType.orderTypePrice);

                    orderTypeName.push(orderType.orderTypeName);

                    orderTypeList.push(orderType);


                    console.log('타입이름의 타입:' + typeof (orderType.orderTypePrice));
                    console.log('타입가격의 타입:' + typeof (orderType.orderTypeName));


                }


            }
            console.log("js내부 변수에 타입 리스트 할당됨.")
            console.log(orderTypeList);
            console.log('코드 리스트:' + orderTypeCode);
            console.log('숫자 리스트:' + orderTypeCount);
            console.log('이름 리스트:' + orderTypeName);
            console.log('가격 리스트:' + orderTypePrice);
            printOrderTypeList();
        }


    });


}

async function getClient() {

    $.ajax({

        "url": "getClient",
        "method": "GET",
        "timeout": 0,

    }).done(function (client) {
        if (client) {
            $('#clientCode').val(client.clientCode)
            $('#clientName').val(client.clientName)


            $('#address').val(client.clientAddress)

        } else {
            alert('로그인 필요 서비스');

            location.href = '/clientLogin'
        }


    })


}

async function getLocalStorageData() {


    const orderTypeDataJSON = localStorage.getItem('orderTypeData');
    const orderTypeData = JSON.parse(orderTypeDataJSON);
    console.log(orderTypeData);
    //4 /1개 식으로 오니 분할해서 저장
    for (let i = 0; i < orderTypeData.length; i++) {
        orderTypeCode.push(orderTypeData[i][0]);
        orderTypeCount.push(orderTypeData[i][1]);

    }


}


async function start() {
    await getClient();
    await getLocalStorageData();
    await getTypeList();

}


document.addEventListener("DOMContentLoaded", function () {

    start();


});

function isInArray(code) {

    for (let i = 0; i < orderTypeCode.length; i++) {
        if (code === orderTypeCode[i]) {
            return true;
        }

    }

    return false;

}

$(document).on('click', '#orderValidation', function () {


    console.log('주문전송 페이지 연결 성공');

//  $('#clientCode').val();
// const form = $('#')
    const buyerCode = $('#clientCode').val();
    const orderAddress = $('#address').val();
    const orderTotalPrice = $('#orderTotalPrice').val();
    const orderRequest = $('#orderRequest').val();


    const data = {
        buyerCode: buyerCode,
        orderAddress: orderAddress,
        orderTotalPrice: orderTotalPrice,
        orderRequest: orderRequest,
        orderState: '수거전'

    }
    const clientData = JSON.stringify(data);
    console.log('제작된 유저 데이터:' + clientData);


    $.ajax({
        "url": `addOrder`,
        "method": "POST",
        "timeout": 0,
        "data": JSON.stringify(data),
        contentType: "application/json",
    }).done(function (code, textStatus, xhr) {
        if (xhr.status == 201) { // 성공 시
            let array = [];
            let inputCode = code;

            for (let i = 0; i < orderTypeCode.length; i++) {

                let inputData = {
                    orderCode: inputCode,
                    orderTypeCode: orderTypeCode[i],
                    orderStack: orderTypeCount[i]

            };
                console.log(inputData)
                array.push(inputData);
            }
            console.log('삽입될 배열: '+array);
            $.ajax({
                "url": `addOrderTrash`,
                "method": "POST",
                "timeout": 0,
                "data": JSON.stringify(array),
                contentType: "application/json",
            }).done(function (data, textStatus, xhr) {


                alert(data);

                alert("주문이 성공적으로 등록되었습니다.");
                localStorage.removeItem('orderTypeData');
                location.href = '/';
            })






        } else { // 그 외의 상황일 때
            alert("주문 등록에 실패하였습니다. 다시 시도해주세요.");
        }
    }).fail(function () { // 서버 오류 등 실패 시
        alert("서버 오류가 발생하였습니다. 다시 시도해주세요.");
    });


});

async function printOrderTypeList() {
    $('#orderTypeList').empty();
    let orderTotalPrice = 0;
    let html = '';
    for (let i = 0; i < orderTypeCode.length; i++) {
        console.log('I: ' + i);
        console.log('타입코드:' + orderTypeCode[i]);
        console.log('타입이름:' + orderTypeName[i]);
        console.log('타입가격:' + orderTypePrice[i]);
        console.log('타입카운트:' + orderTypeCount[i]);
        console.log('타입코드의 타입:' + typeof (orderTypeCode[i]));
        console.log('타입이름의 타입:' + typeof (orderTypeName[i]));
        console.log('타입가격의 타입:' + typeof (orderTypePrice[i]));
        console.log('타입카운트의 타입:' + typeof (orderTypeCount[i]));


        html += `<tr>`;
        html += `<td><input type="text" readOnly="readonly" name="orderTypeName[]" value="${orderTypeName[i]}"></td>`;


        html += `<td><input type="text" readOnly="readonly" name="orderTypePrice[]" value="${orderTypePrice[i]}"></td>`;
        html += `<td><p>*</p></td>`;
        html += ` <td><input type="text" readOnly="readonly" name="orderTypeCount[]" value="${orderTypeCount[i]}"></td>`;
        html += `<td><p>=</p></td>`;
        let tmpPrice = orderTypePrice[i] * orderTypeCount[i];
        orderTotalPrice += tmpPrice;
        html += ` <td><input type="text" readOnly="readonly" name="typePrice" value="${tmpPrice}"></td>`;


        html += `</tr>`;


    }
    $('#orderTypeList').append(html);
    let totalPrice = '';


    totalPrice += ` <p>총 결제 금액</p>`;
    totalPrice += `<input type="text" readOnly="readonly" id="orderTotalPrice" name="orderTotalPrice" value="${orderTotalPrice}">`;

    console.log('총 가격:' + orderTotalPrice);

    $('#totalPrice').append(totalPrice);


}

