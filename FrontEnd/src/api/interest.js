import http from "@/util/http-common"

async function getInterest(success, fail) {
    http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    return await http.get(`/house/interest`).then(success).catch(fail);
}

async function addInterest(aptCode,success, fail) {
    http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    return await http.post(`/house/interest`,{aptCode:aptCode}).then(success).catch(fail);
}

async function deleteInterest(aptCode,success, fail) {
    http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    return await http.delete(`/house/interest/${aptCode}`).then(success).catch(fail);
}

export { getInterest, addInterest, deleteInterest }; 