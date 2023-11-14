import * as React from "react";
import {Box, CssVarsProvider} from "@mui/joy";
import CssBaseline from "@mui/material/CssBaseline";
import Stack from "@mui/joy/Stack";
import Search from "./Search";
import HeaderSection from "./HeaderSection";
import Filters from "./Filters";
import RentalCard from "./RentalCard";
import Pagination from "./Pagination";

export default function Home() {
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
                    <Search
                        value={"Meal Kit"}
                    />
                    <Filters />
                    <Stack spacing={2} sx={{ overflow: 'auto' }}>
                        <RentalCard
                            title="A Stylish Apt, 5 min walk to Queen Victoria Market"
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
                        />
                    </Stack>
                    <Pagination />
                </Stack>
            </Box>
        </CssVarsProvider>
    );
}