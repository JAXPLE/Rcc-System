//오더 타입 받아와서 저장하는 리스트
const orderTypeList = [];
//최대 숫자로 사용할 변수(=1회 최대 주문가능 수)
let maxCount = 10;
//버튼-입력값을 연결하기 위한 인덱스 변수
let index = 0;
let maxIndex = 0;

//js 로직 순서:(변경시 아래 텍스트도 수정할 것
//항목 생성-클릭해서 팝업-팝업 페이지인지 검증-완료시 리스트 출력-클릭하면 값 전달하고 종료-전달 메소드-받아서 실행


// id="typeName' + tmpIndex 는 코드로 이름 받아올 때만 사용
//   class="typeButton' + tmpIndex + '" 는 버튼 작동이랑 만들때 밸류값 조정
//  id="typeCount' + tmpIndex + '" 는 중앙 숫자값인데 버튼 작동할때 사용


// name = "popupButton": 변경버튼
// name = "typeName": 이름
// name = "countMinusButton": 감소버튼
// name="counts[]":출력값
// name = "countPlusButton" :증가버튼


document.addEventListener("DOMContentLoaded", function () {

    //팝업 페이지인지 확인하는 용도의 변수
    let source = callParentGetData();
    if (source != 'parent') {

        $.ajax({
            url: "/orderTypeList",
            "method": "GET",
            "timeout": 0,
            //위의 리스트가 존재하면 그걸 받아와서 자바스크립트 내 배열에 대입하기
            success: function (data) {

                console.log(data);

                for (let i = 0; i < data.length; i++) {
                    //숫자 감지용 가운터 속성 추가
                    data[i].used = false;
                    const orderType = data[i];
                    orderTypeList.push(orderType);

                }
                maxIndex = data.length;
                console.log(`배열 길이: ${maxIndex}`)

                console.log("js내부 변수에 타입 리스트 할당됨.")
                console.log(orderTypeList);

            }
        });
    } else {

        console.log('자식 페이지 출력으로 인해 리스트 호출 무시됨')
        //넘겨온 리스트 삽입
        const newArray = window.opener.sendList;

        console.log('부모페이지 발 전달 배열: ' + newArray); //
        printOrderTypeList(newArray);
    }


});


function addOrder() {


    if (index <= maxIndex) {
        //배열의 길이
        let tmpIndex = index;

        openPopup('add');


        //인덱스랑 코드 넣어주는 함수


    }

}

function addHtml(code) {

    let html = '';
    html += '<div id="divCode[]"><tr> ';

    html += ' <td> <input type="text" name = "typeName[]"  data-code="' + code + '"></td>';
    html += ' <td xmlns="http://www.w3.org/1999/html"> <input type="button" name = "countMinusButton[]" class="minusButton"  data-code="' + code + '" value="-" ></td>';
    html += ' <td> <input type="text" name="counts[]"  data-code="' + code + '" ></td>';
    html += ' <td> <input type="button" name = "countPlusButton[]" class="plusButton"  data-code="' + code + '"value="+"  ></td>';
    html += ' <td> <input type="button" name = "deleteButton[]" class="deleteButton"  data-code="' + code + '" value="삭제" ></td>';

    html += '</tr></div>';


    console.log('추가되는 html: ' + html);


    $('#add').append(html);

    let name = findTypeNameByTypeCode(code);
    console.log('인덱스:' + index);
    $("input[name='typeName[]']").eq(index).val(name);

    let tmpName = $("input[name='typeName[]']").eq(index).val();
    console.log(tmpName);

    $("input[name='counts[]']").eq(index).val(1);

    index++;
    maxCount--;


    const type = findTypeTypeCode(code);
    type.used = true;


}

//보내기 버튼
$(document).on('click', '#submitButton', function () {
    const types = $("input[name='counts[]']");
    const orderTypeData = [];


    types.each(function () {
        const type = $(this);
        const count = parseInt(type.val());
        if (!isNaN(count) && count > 0) {

            let tmpArr = [];
            const code = type.data('code');

            tmpArr.push(code, count);
            orderTypeData.push(tmpArr);
            console.log(tmpArr);
        }
    });
    if (orderTypeData.length > 0) {
        alert('다음 페이지로 이동합니다.');
        orderTypeData.sort();
        const form = $('#orderForm');
        const orderTypeDataJSON = JSON.stringify(orderTypeData);
        localStorage.setItem('orderTypeData', orderTypeDataJSON);
        form.submit();

    } else {

        alert('하나 이상은 선택하셔야 합니다!');
    }

});


$(document).on('click', '.updateButton', function () {


    console.log('업데이트 버튼  1 작동');
    const code = $(this).data('code'); // 클릭된 버튼의 data-key 값
    console.log('현 버튼의 코드:' + code);
    const index = findIndexByCode(code); // key에 해당하는 배열의 인덱스
    console.log('인덱스:' + index);
    openPopup('update', index)


});
$(document).on('click', '.deleteButton', function () {


    console.log('삭제 버튼 작동');
    const code = $(this).data('code'); // 클릭된 버튼의 data-key 값
    console.log('현 버튼의 코드:' + code);
    const tmpIndex = findIndexByCode(code); // key에 해당하는 배열의 인덱스
    console.log('인덱스:' + tmpIndex);

    let count = $("input[name='counts[]']").eq(tmpIndex).val();
    console.log('삭제할 대상의 봉투 개수:' + count);
    maxCount += count;
    const divArray = $("#add").find("[id^='div']");

    const type = findTypeTypeCode(code);
    type.used = false;
    divArray.eq(tmpIndex).remove();
    index--;
});

function setDataCodeByIndex(code, index) {
    console.log('업데이트 설정 구문 진입');
    console.log('바꿀 인덱스 지점' + index);
    console.log('업데이트할 코드' + code);

    // 함수 실행

    let popupButton = $('input[name="popupButton[]"]:eq(' + index + ')');
    let typeName = $('input[name="typeName[]"]:eq(' + index + ')');
    let countMinusButton = $('input[name="countMinusButton[]"]:eq(' + index + ')');
    let counts = $('input[name="counts[]"]:eq(' + index + ')');
    let countPlusButton = $('input[name="countPlusButton[]"]:eq(' + index + ')');


    popupButton.dataset.code = code;
    typeName.dataset.code = code;
    countMinusButton.dataset.code = code;
    counts.dataset.code = code;
    countPlusButton.dataset.code = code;


    let name = findTypeNameByTypeCode(code);
    $("input[name='typeName[]']").eq(index).val(name);
    $("input[name='counts[]']").eq(index).val(0);


}


$(document).on('click', '.plusButton', function () {


    let code = $(this).data('code'); // 클릭된 버튼의 data-key 값
    console.log('클릭한 버튼의 데이터 코드:' + code);


    let tmlIndex = findIndexByCode(code);
    let count = $("input[name='counts[]']").eq(tmlIndex).val();

    console.log('코드로 찾은 타입의 현 주문 숫자: ' + count);
    if (count < 10 && maxCount > 0) {//증가 버튼

        count++;
        $("input[name='counts[]']").eq(tmlIndex).val(count);

        console.log('배열의 카운트값' + count);
        maxCount--;
        console.log('증가 후 맥스카운트:' + maxCount);

    }


});

$(document).on('click', '.minusButton', function () {


    let code = $(this).data('code'); // 클릭된 버튼의 data-key 값
    console.log('클릭한 버튼의 데이터 코드:' + code);


    let tmlIndex = findIndexByCode(code);
    let count = $("input[name='counts[]']").eq(tmlIndex).val();

    console.log('코드로 찾은 타입의 현 주문 숫자: ' + count);
    if (count > 0 && maxCount < 10) {//증가 버튼

        count--;
        $("input[name='counts[]']").eq(tmlIndex).val(count);

        console.log('배열의 카운트값' + count);
        maxCount++;
        console.log('증가 후 맥스카운트:' + maxCount);

    }


});


//타입코드 받아서 이름 반환
function findTypeNameByTypeCode(code) {
    for (let i = 0; i < orderTypeList.length; i++) {

        const orderType = orderTypeList[i];
        if (orderType.orderTypeCode == code) {

            console.log('찾은 이름:' + orderType.orderTypeName);
            return orderType.orderTypeName;


        }

    }


}

//타입코드 받아서 객체 반환
function findTypeTypeCode(code) {
    for (let i = 0; i < orderTypeList.length; i++) {

        const orderType = orderTypeList[i];
        if (orderType.orderTypeCode == code) {
            return orderType;


        }

    }


}

function findIndexByCode(code) {

    console.log('넘겨받은 코드:' + code);

    const inputs = $('input[name="counts[]"]');
    for (let i = 0; i < inputs.length; i++) {
        console.log(inputs[i]); // 각 요소에 대한 정보 출력
    }
    console.log('찾은 배열의 길이:' + inputs.length);


    for (let i = 0; i < inputs.length; i++) {
        let tmp = inputs[i];
        let tmpCode = parseInt(tmp.dataset.code);
        console.log('tmpcode:' + tmpCode);
        if (tmpCode === code) {
            console.log('찾은 인덱스:' + i);
            return i;
        }
    }
}


function openPopup(type, orderIndex) {
    console.log('팝업창에서 전달받은 인덱스:' + orderIndex);

    let url = '';

    if (typeof orderIndex !== 'undefined') {//두번째 인자가 있으면
        console.log('팝업 페이지에서 업데이트 구문으로 인식');

        url += "orderFormPopup?source=parent&type=" + type;
        url += "&orderIndex=" + orderIndex;
    } else {//인자값이 하나면

        console.log('팝업 페이지에서 생성 구문으로 인식');
        url += "orderFormPopup?source=parent&type=" + type;
    }

    console.log('url: ' + url);
    let windowName = "popup";
    let windowFeatures = "width=600,height=400,resizable=yes";
    window.sendList = orderTypeList;
    let popupWindow = window.open(url, windowName, windowFeatures);

}

// 부모 페이지의 getData() 함수를 호출하는 함수. 해당 함수를 활용해서 팝업 페이지에서 리스트 로드를 막는다
function callParentGetData() {
    const urlParams = new URLSearchParams(window.location.search);
    const source = urlParams.get('source');
    return source;
}

function callParentGetType() {
    const urlParams = new URLSearchParams(window.location.search);
    const typeMassage = urlParams.get('type');
    return typeMassage;
}

function callParentGetIndex() {
    const urlParams = new URLSearchParams(window.location.search);
    const orderIndex = urlParams.get('orderIndex');
    return orderIndex;
}


//팝업페이지에서 배열 출력하는 메소드
function printOrderTypeList(list) {
    console.log(`팝업페이지 내 출력 메소드 진입`);
    let html = '';

    for (let i = 0; i < list.length; i++) {


        const orderType = list[i];
        const code = orderType.orderTypeCode;//코드
        const name = orderType.orderTypeName;//이름
        const detail = orderType.orderTypeDetail;//상세설명


        html += ' <tr><td>' + code + '</td>';
        html += '<td><img src="img/' + code + '.png"></td>';
        html += `<td>[${name}]</td>`;
        html += '<td>' + detail + '</td>';

        if (orderType.used) {
            console.log('사용중')

        } else {
            html += '<td><input id="sendButton" type="button" onclick="sendValueToParent(' + code + ')" value="선택하기"></td>';

        }


        html += '</tr>';
    }
    $('#typeList').append(html);
}


//클릭하면 코드를 전달하고 팝업창을 끄는 윈도우
function sendValueToParent(code) {


    console.log('팝업 내 값 부모 전달 메서드 진입');
    // 부모 페이지 참조
    const parentWindow = window.opener;

    let typeMessage = callParentGetType();
    console.log('부모 페이지에서 받은 구문:' + typeMessage);


    if (typeMessage === 'add') {
        //새 메뉴 만들기
        parentWindow.addHtml(code);

    } else if (typeMessage === 'update') {
        //기존 메뉴 변경
        //url에서 인덱스 받아오기
        let orderIndex = callParentGetIndex();
        console.log('추적한 인덱스:' + orderIndex);
        debugger;
        parentWindow.setDataCodeByIndex(code, orderIndex);


    }


    window.close();
}