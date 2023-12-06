import * as React from "react";
import Grid from "@mui/material/Grid";
import Box from '@mui/material/Box';
import Stack from "@mui/joy/Stack";
import {Paper, styled} from "@mui/material";
import HeaderSection from "./HeaderSection";
import Typography from "@mui/joy/Typography";

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));


export default function Details() {

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
            <Box sx={{ px: { xs: 2, md: 20 }, pt: 7}}><HeaderSection /></Box>
            <Stack direction="row" spacing={10} sx={{ px: { xs: 2, md: 20 }, pt: 3 }}>
                <img src={"https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=400"} width='550' height='450'/>
                <Box>
                    <Typography
                        color="neutral"
                        level="h2"
                        noWrap={false}
                        variant="plain"
                    >
                        This area exist for MealKit Name.
                    </Typography>
                    <Typography
                        color="neutral"
                        level="body-lg"
                        noWrap={false}
                        variant="plain"
                    >
                        This area exist for plus explain.
                    </Typography>
                    <Typography
                        color="neutral"
                        level="body-lg"
                        noWrap={false}
                        variant="plain"
                        sx={pt: 2}
                    >
                        This area exist for plus explain.
                    </Typography>
                </Box>
            </Stack>
        </Box>
    );
}