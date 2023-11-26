import http from "../http-common";

const getAll = (params) => {
    return http.get("/mealKits", { params});
}