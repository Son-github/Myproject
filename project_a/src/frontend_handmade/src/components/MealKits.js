import * as React from "react";
import {useEffect, useRef, useState} from "react";
import {Box} from "@mui/joy";
import Stack from "@mui/joy/Stack";
import Search from "./Search";
import HeaderSection from "./HeaderSection";
import Filters from "./Filters";
import RentalCard from "./RentalCard";
import Pagination from "@mui/material/Pagination";
import MealKitService from "../MealKitService";

const MealKits = (props) => {

    const [mealKitsList, setMealKitsList] = useState([]);
    const [searchMealKits, setSearchMealKits] = useState("");
    const [mealKitImage, setMealKitImage] = useState([]);
    const mealKitsRef = useRef();

    const [page, setPage] = useState(1);
    const [count, setCount] = useState(0);
    const [pageSize, setPageSize] = useState(10);

    const pageSizes = [10, 20, 30];

    mealKitsRef.current = mealKitsList;

    const onChangeSearchMealKits = (e) => {
        const searchMealKits = e.target.value;
        setSearchMealKits(searchMealKits);
    };

    const getRequestParams = (searchMealKits, page, pageSize) => {
        let params = {};

        if (searchMealKits) {
            params["m_name"] = searchMealKits;
        }

        if (page) {
            params["page"] = page - 1;
        }

        if (pageSize) {
            params["size"] = pageSize;
        }

        return params;
    };

    const retrieveMealKits = () => {
        const params = getRequestParams(searchMealKits, page, pageSize);

        MealKitService.getMealKits(params)
            .then((res) => {
                const { mealKits, totalPages } = res.data;

                setMealKitsList(mealKits);
                setCount(totalPages);

                console.log(res.data);
            })
            .catch( (e) => {
                console.log(e);
            });
    };

    useEffect(retrieveMealKits, [page, pageSize]);

    const findByMealKitsName = () => {
        setPage(1);
        retrieveMealKits();
    }

    const handlePageChange = (event, value) => {
        setPage(value);
    };

    console.log(mealKitsList.at(0));

    return (
            <Box
                component="main"
                sx={{
                    height: 'calc(100vh - 55px)', // 55px is the height of the NavBar
                    display: 'grid',
                    gridTemplateColumns: { xs: 'auto'},
                    gridTemplateRows: 'auto 1fr auto',
                }}
            >
                <Stack spacing={2} sx={{ px: { xs: 2, md: 4 }, pt: 2 }}>
                    <HeaderSection />
                    <Search
                        search_Value={searchMealKits}
                        handleChangeSearchValue={onChangeSearchMealKits}
                        findByMealKits={findByMealKitsName}
                    />
                    <Filters />
                    {mealKitsList.map(value =>
                        <Stack spacing={2} sx={{ overflow: 'auto' }} key={value.id}> {/* 만약 key 설정을 안해주면 에러가 뜬다!!*/}
                            <RentalCard
                                title={value.mname}
                                category={value.mcategory}
                                link={value.msite}
                                //rareFind
                                //image="https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=400" // TODO: image는 따로 저장해야할 듯! 새로 테이블을 만들자!
                                image={value.mealKitImages.map(image => image.imageUrl)}
                            />
                        </Stack>
                        )}
                    <Pagination
                        shape="rounded"
                        size="large"
                        count={count}
                        page={page}
                        boundaryCount={1}
                        siblingCount={1}
                        onChange={handlePageChange}
                    />
                </Stack>
            </Box>
    );
}

export default MealKits;