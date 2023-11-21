import React, {useEffect, useState} from 'react';
import Button from '@mui/joy/Button';
import FormControl from '@mui/joy/FormControl';
import Input from '@mui/joy/Input';
import Stack from '@mui/joy/Stack';
import SearchRoundedIcon from '@mui/icons-material/SearchRounded';
import Typography from '@mui/joy/Typography';
import axios from "axios";


export default function Search(){

    const [search_Value, setSearchValue] = useState();

    useEffect(() => {
        getSearchValue();
    })

    async function getSearchValue() {
        await axios
            .get('/home')
            .then((res) => {
                console.log(res.data);
            })
            .catch((error) => {
                console.log(error);
            })
    }

    const handleChangeSearchValue = (e) => {
        e.preventDefault();
        setSearchValue(e.target.value);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        await axios
            .post('/home', {
                searchValue: search_Value
            })
            .then((res) => {
                console.log(res.data)
            })
            .catch((error) => {
                console.log(error);
            })
    }


    return (
        <div>
            <Stack spacing={1} direction="row" sx={{ mb: 2 }} onSubmit={handleSubmit}>
                <FormControl sx={{ flex: 1 }}>
                    <Input
                        placeholder="Search"
                        value={search_Value}
                        startDecorator={<SearchRoundedIcon />}
                        onChange={handleChangeSearchValue}
                        aria-label="Search"
                    />
                </FormControl>
                <Button type="submit" variant="solid" color="primary">
                    Search
                </Button>
            </Stack>
        </div>
    );
}