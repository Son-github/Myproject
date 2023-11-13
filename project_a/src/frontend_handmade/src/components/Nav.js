import React from "react";
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import { useNavigate } from "react-router-dom";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import AnchorIcon from '@mui/icons-material/Anchor';
import {Search} from "@mui/icons-material";
import { styled, alpha } from '@mui/material/styles';
import InputBase from '@mui/material/InputBase';
import Badge from '@mui/material/Badge';
import SearchIcon from '@mui/icons-material/Search';
import AccountCircle from '@mui/icons-material/AccountCircle';
import MailIcon from '@mui/icons-material/Mail';
import NotificationsIcon from '@mui/icons-material/Notifications';
import MoreIcon from '@mui/icons-material/MoreVert';
import { Box, IconButton } from '@mui/joy';
import Typography from '@mui/joy/Typography';
import Avatar from '@mui/joy/Avatar';
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