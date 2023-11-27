import * as React from "react";
import {useEffect, useMemo, useRef, useState} from "react";
import {Box, CssVarsProvider} from "@mui/joy";
import CssBaseline from "@mui/material/CssBaseline";
import Stack from "@mui/joy/Stack";
import Search from "./Search";
import HeaderSection from "./HeaderSection";
import Filters from "./Filters";
import RentalCard from "./RentalCard";
import Pagination from "./Pagination";
import axios from "axios";
import MealKitService from "../MealKitService";
import { useTable } from "react-table";

const MealKits = (props) => {

    const [mealKitsList, setMealKitsList] = useState([]);
    const [searchMealKits, setSearchMealKits] = useState("");
    const mealKitsRef = useRef();

    const [page, setPage] = useState(1);
    const [count, setCount] = useState(0);
    const [pageSize, setPageSize] = useState(10);

    const pageSizes = [5, 10, 15];

    mealKitsRef.current = mealKitsList;

    const onChangeSearchMealKits = (e) => {
        const searchMealKits = e.target.value;
        setSearchMealKits(searchMealKits);
    };

    const getRequestParams = (searchMealKits, page, pageSize) => {
        let params = {};

        if (searchMealKits) {
            params["mName"] = searchMealKits;
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
                const { mealKits, totalPages} = res.data;

                setMealKitsList(mealKits);
                setCount(totalPages);

                console.log("성공");
                console.log(res.data);
            })
            .catch( (e) => {
                console.log(e);
            });
    };

    useEffect( retrieveMealKits, [page, pageSize]);

    console.log("Count: " + count);

    const findByMealKitsName = () => {
        setPage(1);
        retrieveMealKits();
    }

    const handlePageChange = (event, value) => {
        setPage(value);
    };

    const handlePageSizeChange = (event) => {
        setPageSize(event.target.value);
        setPage(1);
    };

    return (
        <CssVarsProvider disableTransitionOnChange>
            <CssBaseline />
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
                    <Search />
                    <Filters />
                    {mealKitsList.map(value =>
                        <Stack spacing={2} sx={{ overflow: 'auto' }} key={value.id}>
                            <RentalCard
                                title={value.mname}
                                category={value.mcategory}
                                rareFind
                                image="https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=400" // TODO: image는 따로 저장해야할 듯! 새로 테이블을 만들자!
                            />
                            {/*<RentalCard
                                title="ttt"
                                category="Entire apartment rental in Collingwood"
                                rareFind
                                image="https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=400"
                            />
                            <RentalCard
                                title="Designer NY style loft"
                                category="Entire loft in central business district"
                                liked
                                image="https://images.unsplash.com/photo-1600596542815-ffad4c1539a9?auto=format&fit=crop&w=400"
                            />
                            <RentalCard
                                title="5 minute walk from University of Melbourne"
                                category="Entire rental unit in Carlton"
                                image="https://images.unsplash.com/photo-1537726235470-8504e3beef77?auto=format&fit=crop&w=400"
                            />
                            <RentalCard
                                title="Magnificent apartment next to public transport"
                                category="Entire apartment rental in Collingwood"
                                image="https://images.unsplash.com/photo-1618221195710-dd6b41faaea6?auto=format&fit=crop&w=400"
                            />
                            <RentalCard
                                title="Next to shoppng mall and public transport"
                                category="Entire apartment rental in Collingwood"
                                image="https://images.unsplash.com/photo-1582268611958-ebfd161ef9cf?auto=format&fit=crop&w=400"
                            />
                            <RentalCard
                                title="Endless ocean view"
                                category="A private room in a shared apartment in Docklands"
                                image="https://images.unsplash.com/photo-1564013799919-ab600027ffc6?auto=format&fit=crop&w=400"
                            />
                            <RentalCard
                                title="A Stylish Apt, 5 min walk to Queen Victoria Market"
                                category="one bedroom apartment in Collingwood"
                                image="https://images.unsplash.com/photo-1481437156560-3205f6a55735?auto=format&fit=crop&w=400"
                            />*/}
                        </Stack>
                        )}
                    <Pagination
                        count={count}
                        page={page}
                        onChange={handlePageChange}
                    />
                </Stack>
            </Box>
        </CssVarsProvider>
    );
}

export default MealKits;