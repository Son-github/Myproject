import * as React from "react";
import {useEffect, useState} from "react";
import Box from '@mui/material/Box';
import Stack from "@mui/joy/Stack";
import {Tab} from "@mui/material";
import HeaderSection from "./HeaderSection";
import Typography from "@mui/joy/Typography";
import {ListItemButton, Table} from "@mui/joy";
import ListItemText from "@mui/material/ListItemText";
import ListItem from "@mui/material/ListItem";
import {FixedSizeList} from 'react-window';
import AliceCarousel from "react-alice-carousel";
import 'react-alice-carousel/lib/alice-carousel.css';
import TabContext from "@mui/lab/TabContext";
import {TabList, TabPanel} from "@mui/lab";
import MealKitService from "../MealKitService";
import {useParams} from "react-router-dom";

function renderRow(props) {
    const { index, style } = props;

    return (
        <ListItem style={style} key={index} component="div" disablePadding>
            <ListItemButton>
                <ListItemText primary={`Item ${index + 1}`} />
            </ListItemButton>
        </ListItem>
    );
}

export default function Details(props) {

    const MealKitState = {
        createdAt: "",
        modifiedAt: "",
        id: null,
        mcategory: "",
        mcontent: "",
        mealKitComments: [],
        mealKitImages: [],
        mname: "",
        mprice: "",
        msite: "",
        mstock: null
    };

    const [mealKit, setMealKit] = useState(MealKitState);

    const getMealKit = id => {
        MealKitService.getMealKitDetail(id)
            .then((res) => {
                setMealKit(res.data);
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

    const [value, setValue] = React.useState('1');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const items = [
        <div style={{height: 450}}><img alt="" src={mealKit.mealKitImages.at(0)} style={{width:550, height:450}}/></div>,
        <div style={{height: 450}}><img alt="" src={mealKit.mealKitImages.at(1)} style={{width:550, height:450}}/></div>,
        <div style={{height: 450}}><img alt="" src="https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=400" style={{width:550, height:450}}/></div>
    ]; // todo: mealKit.mealKitImages로 바꿀것.

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
                            {mealKit.mcategory}
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
                                    <td>159</td>

                                </tr>
                                <tr>
                                    <td>판매자</td>
                                    <td>237</td>

                                </tr>
                                <tr>
                                    <td>중량/용량</td>
                                    <td>262</td>

                                </tr>
                                <tr>
                                    <td>알레르기 정보</td>
                                    <td>305</td>

                                </tr>
                            </tbody>
                        </Table>
                        <Box
                            sx={{ width: '100%', height: 400, maxWidth: 360, pt: 5}}
                        >
                            <FixedSizeList
                                height={250}
                                width={550}
                                itemSize={46}
                                itemCount={10}
                                overscanCount={5}
                            >
                                {renderRow}
                            </FixedSizeList>
                        </Box>
                    </Box>
                </Stack>
                <TabContext value={value}>
                    <Box sx={{ px: { xs: 2, md: 20 }, pt: 3, justifyContent: 'center' }}>
                            <Box sx={{ borderBottom: 1, borderColor: 'divider'}}>
                                <TabList
                                    onChange={handleChange}
                                    centered={true}
                                    variant="fullWidth"
                                >
                                    <Tab label="상품설명" value="1" />
                                    <Tab label="상세정보" value="2" />
                                    <Tab label="후기" value="3" />
                                </TabList>
                            </Box>
                            <TabPanel value="1">Item One</TabPanel>
                            <TabPanel value="2">Item Two</TabPanel>
                            <TabPanel value="3">Item Three</TabPanel>
                    </Box>
                </TabContext>
            </Box>
        </Box>
    );
}