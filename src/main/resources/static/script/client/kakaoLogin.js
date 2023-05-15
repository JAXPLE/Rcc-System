


Kakao.init('d7c7e8b3793ffdb799d469a5cb545f59');
console.log(Kakao.isInitialized());


function loginWithKakao() {
    Kakao.Auth.login({
        success: function (response) {
            location.href=
                "https://kauth.kakao.com/oauth/authorize?" +
                "client_id=e8a5603f3be2edee2433cc080f00306c&redirect_uri=" +
                "http://localhost:8080/oauth/kakao&response_type=code";
            },
        fail: function (error) {
            console.log(error)
        },
    })
}
