import http from "@/util/http-common"

async function getQuestion(no, success, fail) {
    return await http.get(`/qna/question/${no}`).then(success).catch(fail);
}

export { getQuestion };