import React from "react";
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import { useNavigate } from "react-router-dom";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import AnchorIcon from '@mui/icons-material/Anchor';

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

    const toGoBooking = () => {
        navigate("/checkout")
    }

    const toGoHome = () => {
        navigate("/home")
    };

    const toGoPlace = () => {
        navigate("/place")
    }
    const toGoContact = () => {
        navigate("/contact")
    };

    const toGoSignup = () => {
        navigate("/signup")
    };
    const [anchorElNav, setAnchorElNav] = React.useState(null);

    const [anchorElUser, setAnchorElUser] = React.useState(null);

    const handleOpenNavMenu = (event) => {
        setAnchorElNav(event.currentTarget);
    };

    const handleOpenUserMenu = (event) => {
        setAnchorElUser(event.currentTarget);
    };
    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };
    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };
    return(
        <ThemeProvider theme={darkTheme}>
            <AppBar position="static">
                <Container maxWidth="xl">
                    <Toolbar disableGutters>
                        <AnchorIcon/>
                        <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
                            <IconButton
                                size="large"
                                aria-label="account of current user"
                                aria-controls="menu-appbar"
                                aria-haspopup="true"
                                onClick={handleOpenNavMenu}
                                color="inherit"
                            >
                                <MenuIcon />
                            </IconButton>
                            <Menu
                                id="menu-appbar"
                                anchorEl={anchorElNav}
                                anchorOrigin={{
                                    vertical: 'bottom',
                                    horizontal: 'left',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'left',
                                }}
                                open={Boolean(anchorElNav)}
                                onClose={handleCloseNavMenu}
                                sx={{
                                    display: { xs: 'block', md: 'none' },
                                }}
                            >
                                <Button
                                    onClick={toGoHome}>
                                    home
                                </Button>
                                <Button
                                    onClick={toGoBooking}>
                                    booking
                                </Button>
                                <Button
                                    onClick={toGoPlace}>
                                    Place
                                </Button>
                                <Button
                                    onClick={toGoContact}>
                                    Contact
                                </Button>
                            </Menu>
                        </Box>
                        <Typography
                            variant="h5"
                            noWrap
                            component="a"
                            href="#app-bar-with-responsive-menu"
                            sx={{
                                mr: 2,
                                display: { xs: 'flex', md: 'none' },
                                flexGrow: 1,
                                fontFamily: 'monospace',
                                fontWeight: 700,
                                letterSpacing: '.3rem',
                                color: 'inherit',
                                textDecoration: 'none',
                            }}
                        >
                        </Typography>
                        <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                            <Button
                                onClick={toGoHome}
                                sx={{ my: 2, color: 'white', display: 'block' }}
                            >
                                home
                            </Button>
                            <Button
                                onClick={toGoBooking}
                                sx={{ my: 2, color: 'white', display: 'block' }}
                            >
                                booking
                            </Button>
                            <Button
                                onClick={toGoPlace}
                                sx={{ my: 2, color: 'white', display: 'block' }}
                            >
                                Place
                            </Button>
                            <Button
                                onClick={toGoContact}
                                sx={{ my: 2, color: 'white', display: 'block' }}
                            >
                                Contact
                            </Button>
                        </Box>

                        <Box sx={{ flexGrow: 0,color:'white'}}>
                            <Button onClick={toGoSignIn}
                                    size="memium"
                                    sx={{color: 'white'}}>

                                Login
                            </Button>
                            <Menu
                                sx={{ mt: '45px' }}
                                id="menu-appbar"
                                anchorEl={anchorElUser}
                                anchorOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                open={Boolean(anchorElUser)}
                                onClose={handleCloseUserMenu}
                            >
                                {settings.map((setting) => (
                                    <MenuItem key={setting} onClick={handleCloseUserMenu}>
                                        <Typography textAlign="center">{setting}</Typography>
                                    </MenuItem>
                                ))}
                            </Menu>
                        </Box>

                        <Box sx={{ flexGrow: 0 }}>
                            <Button onClick={toGoSignup} variant="outlined" sx={{color: 'white'}}>
                                Signup
                            </Button>
                        </Box>
                    </Toolbar>
                </Container>
            </AppBar>
        </ThemeProvider>
    )
}

export default Nav;