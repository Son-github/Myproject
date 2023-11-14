import React from "react";
import Button from '@mui/material/Button';
import {useNavigate} from "react-router-dom";
import {createTheme} from "@mui/material/styles";
import {Box, IconButton} from '@mui/joy';
import Typography from '@mui/joy/Typography';
import MapsHomeWorkIcon from '@mui/icons-material/MapsHomeWork';

const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];

const darkTheme = createTheme({
    palette: {
        mode: 'dark',
        primary: {
            main: '#ffffff',
        },
    },
});
function Nav() {

    const navigate = useNavigate();

    const toGoSignIn = () => {
        navigate("/signin")
    }

    const toGoHome = () => {
        navigate("/home")
    };

    const toGoSignup = () => {
        navigate("/signup")
    };
    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'row',
                justifyContent: 'space-between',
                alignItems: 'center',
                width: '100%',
                top: 0,
                px: 1.5,
                py: 1,
                zIndex: 10000,
                backgroundColor: 'background.body',
                borderBottom: '1px solid',
                borderColor: 'divider',
                position: 'sticky',
            }}
        >
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'row',
                    alignItems: 'center',
                    gap: 1.5,
                }}
            >
                <IconButton onClick={toGoHome} size="sm" variant="soft" >
                    <MapsHomeWorkIcon />
                </IconButton>
                <Typography component="h1" fontWeight="xl">
                    Mealkit Compare
                </Typography>
            </Box>

            <Box sx={{ display: 'flex', flexDirection: 'row', gap: 3 }}>
                <Box>
                    <Button onClick={toGoSignIn}
                            sx={{color: 'text.primary', fontSize: 16, fontWeight: 'Bold'}}>
                        Login
                    </Button>
                </Box>

                <Box>
                    <Button onClick={toGoSignup}
                            sx={{color: 'text.primary', fontSize: 16, fontWeight: 'Bold'}}>
                        Signup
                    </Button>
                </Box>
            </Box>
        </Box>
    );
}

export default Nav;