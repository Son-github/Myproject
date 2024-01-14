import http from "../src/http-common"

const getMealKits = (params) => {
    return http.get("/", { params });
}

const getMealKitDetail = (id) => {
    return http.get(`/detail/${id}`);
}

const MealKitService = {
    getMealKits,
    getMealKitDetail
};

export default MealKitService;