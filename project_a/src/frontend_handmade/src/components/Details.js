import * as React from "react";
import {useEffect, useState} from "react";
import Box from '@mui/material/Box';
import Stack from "@mui/joy/Stack";
import {Divider, ImageList, ImageListItem} from "@mui/material";
import HeaderSection from "./HeaderSection";
import Typography from "@mui/joy/Typography";
import {ListItemButton, Table, Textarea} from "@mui/joy";
import ListItemText from "@mui/material/ListItemText";
import ListItem from "@mui/material/ListItem";
import AliceCarousel from "react-alice-carousel";
import 'react-alice-carousel/lib/alice-carousel.css';
import MealKitService from "../MealKitService";
import {useParams} from "react-router-dom";
import List from "@mui/material/List";
import Button from '@mui/material-next/Button';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';

export default function Details(props) {

    const [mealKit, setMealKit] = useState([]);
    const [mealKitImages, setMealKitImages] = useState([]);
    const [mealKitSites, setMealKitSites] = useState([]);
    const [mealKitComments, setMealKitComments] = useState([]);

    const getMealKit = id => {
        MealKitService.getMealKitDetail(id)
            .then((res) => {
                const { mealKit, mealKitImages, mealKitSites, mealKitComments } = res.data;

                setMealKit(mealKit);
                setMealKitImages(mealKitImages);
                setMealKitSites(mealKitSites);
                setMealKitComments(mealKitComments)

                console.log(res.data)
            })
            .catch(e => {
                console.log(e);
            });
    };

    const { id } = useParams();

    useEffect(() => {
        getMealKit(id);
    }, [id]);

    const items =
        mealKitImages.map( image => (
            <div key={image.id} style={{height: 450}}><img alt="" src={image.imageUrl} style={{width: 550, height: 450}}/></div>
        ));

    const renderSlideInfo = ({ item, itemsCount }) => {
        return `${item}\\${itemsCount}`;
    };

    const renderDotsItem = ({ isActive }) => {
        return isActive ? 'x' : 'o';
    };

    const renderPrevButton = ({ isDisabled }) => {
        return <span style={{ opacity: isDisabled ? '0.5' : 1 }}>&lt;</span>;
    };

    const renderNextButton = ({ isDisabled }) => {
        return <span style={{ opacity: isDisabled ? '0.5' : 1 }}>&gt;</span>;
    };

    const Carousel = () => (
        <AliceCarousel
            items={items}
            disableSlideInfo={false}
            renderSlideInfo={renderSlideInfo}
            renderDotsItem={renderDotsItem}
            renderPrevButton={renderPrevButton}
            renderNextButton={renderNextButton}
        />
    );

    console.log("mealKitComment: " + mealKitComments);

    mealKitComments.map((comment) => (
        console.log(comment)
    ));

    return (
        <Box
            component="main"
            sx={{
                height: 'calc(100vh - 55px)', // 55px is the height of the NavBar
                display: 'grid',
                gridTemplateColumns: { xs: 'auto'},
                gridTemplateRows: 'auto 1fr auto',
                justifyContent: 'center'
            }}
        >
            <Box sx={{ px: { xs: 2, md: 20 }, pt: 7}}><HeaderSection /></Box>
            <Box sx={{ justifyContent: 'center' }}>
                <Stack direction="row" spacing={10} sx={{ px: { xs: 2, md: 20 }, pt: 3 }} justifyContent="center">
                    <Box width="550px">
                        <Carousel/>
                    </Box>
                    <Box width="550px">
                        <Typography
                            color="neutral"
                            level="h2"
                            variant="plain"
                        >
                            {mealKit.mname}
                        </Typography>
                        <Typography
                            color="neutral"
                            level="body-lg"
                            noWrap={false}
                            variant="plain"
                        >
                            {mealKit.mcontent}
                        </Typography>
                        <Typography
                            color="neutral"
                            level="h4"
                            noWrap={false}
                            variant="plain"
                            sx={{pt: 3}}
                        >
                            {mealKit.mprice}
                        </Typography>
                        <Table aria-label="basic table" sx={{pt: 3, px: -2, width: 550}}>
                            <tbody>
                                <tr>
                                    <td>판매 단위</td>
                                    <td>{mealKit.msaleUnit}</td>

                                </tr>
                                <tr>
                                    <td>판매자</td>
                                    <td>{mealKit.msaleCompany}</td>

                                </tr>
                                <tr>
                                    <td>중량/용량</td>
                                    <td>{mealKit.mweight}</td>

                                </tr>
                            </tbody>
                        </Table>
                        <Box
                            sx={{pt: 3, px: -2, width: '100%', height: 400, maxWidth: 550, bgcolor: 'background.paper' }}
                        >
                            <List
                                sx={{
                                    width: '100%',
                                    Width: 550,
                                    bgcolor: 'background.paper',
                                    position: 'relative',
                                    overflow: 'auto',
                                    maxHeight: 300,
                                    '& ul': { padding: 0 },
                                }}
                            >
                                    <li>
                                            {mealKitSites.map((item) => (
                                                <ul key={item.id}>
                                                    <ListItem >
                                                        <ListItemButton onClick={()=> window.open(item.siteUrl)}>
                                                            <ListItemText primary={item.siteName} />
                                                            <ListItemText secondary={item.sitePrice} />
                                                        </ListItemButton>
                                                    </ListItem>
                                                </ul>
                                            ))}
                                    </li>
                            </List>
                        </Box>
                    </Box>
                </Stack>
                <Box sx={{ px: { xs: 2, md: 20}, pt: 3, justifyContent: 'center' }}>
                    <Divider>후기</Divider>
                    <Box sx={{ px: { xs: 2, md: 20 }, pt: 3, justifyContent: 'center' }}>
                        <Divider/> {/*TODO comments 도메인에 nickname 추가!*/}
                        {mealKitComments.map((comments) => (
                            <Stack direction="row" spacing={5} sx={{ px: { xs: 2}, pt: 3 }} key={comments.id}>
                                <Box sx={{width: 100}}>
                                    <Typography>aa</Typography>
                                </Box>
                                <Box sx={{width: 670, height: 300}}>
                                    <Stack spacing={3} sx={{px: {xs: 2}, width: 600, height: 200, whiteSpace: 'pre-line'}}>
                                        <Typography variant="h6">MealKitName</Typography>
                                        <Typography sx={{px: {xs: 1}}}>MealKitComment</Typography>
                                    </Stack>
                                    <ImageList sx={{ px: { xs: 2 }, width: 500, height: 110 }} cols={3} rowHeight={100}>
                                        {mealKitImages.map((item) => (
                                            <ImageListItem key={item.id}>
                                                <img
                                                    srcSet={`${item.imageUrl}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
                                                    src={`${item.imageUrl}?w=164&h=164&fit=crop&auto=format`}
                                                    alt={item.imageUrl}
                                                    loading="lazy"
                                                />
                                            </ImageListItem>
                                        ))}
                                    </ImageList>
                                    <Box sx={{width: 660}}>
                                        <Button
                                            style={{
                                                float: "right"
                                            }}
                                            color="secondary"
                                            disabled={false}
                                            size="small"
                                        >
                                            <ThumbUpIcon
                                                style={{
                                                    width: 18,
                                                    height: 18
                                                }}
                                            />
                                        </Button>
                                    </Box>
                                    <Box sx={{ px: {xs: 4}, pt: 5}}>
                                        <Typography style={{ float: "right", fontSize: "small"}}>{mealKit.createdAt}</Typography>
                                    </Box>
                                </Box>
                            </Stack>
                        ))}
                    </Box>
                </Box>
            </Box>
        </Box>
    );
}