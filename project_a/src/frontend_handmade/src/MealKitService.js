import http from "../src/http-common"

const getMealKits = (params) => {
    return http.get("/mealKits", {params});
}

const MealKitService = {
    getMealKits
};

export default MealKitService;