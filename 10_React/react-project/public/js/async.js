
const serverData = [{
    id: 1,
    name: "jiwon",
},
{
    id: 2,
    name: "jiwin",
},
{
    id: 3,
    name: "kiwon",
}]


//기존콜백방식
// function getUser(data, successCallback, errorCallback) {
//     //일반함수내에서 비동기 작업 실행
//     setTimeout(() => {
//         //완료 후 callback을 통한 데이터 전달
//         const user = serverData.filter(u => u.id === data.id);

//         if (user.length > 0) successCallback(user);
//         else errorCallback("user를 찾을 수 없습니다.")
//     }, 3000);
// }

// getUser({ id: 1 }, (user) => {
//     console.log(user);
// }, (errorMsg) => {
//     console.log(errorMsg);
// })

function getUser(data) {
    //Promise -> 비동기작업을 실행해줄 객체
    return new Promise((resolve, reject) => {
        //resolve: 성공 실행해줄 함수
        //reject: 실패시 실행해줄 함수

        //Promise내에서 비동기 함수 실행
        setTimeout(() => {
            //완료 후 정해진 함수를 통한 데이터 전달
            const user = serverData.filter(u => u.id === data.id);

            if (user.length > 0) resolve(user);
            else reject("user를 찾을 수 없습니다.")
        }, 3000);
    })
}

// getUser({id: 5})
//     .then(result => {
//         console.log("then 결과 : ", result);
//     })
//     .catch(error => {
//         console.log("error 결과 : ", error);
//     })
//     .finally(() => {
//         console.log("finally 실행 ");
//     });




async function testAsync() {
    try {
        const result = await getUser({ id: 3 });
        console.log("await의 결과 : ", result);
    } catch (error) {
        console.log("catch 결과 : ", error);
    } finally {
        console.log("async/await 실행완료");
    }
}

testAsync();

