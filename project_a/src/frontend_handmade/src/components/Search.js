import React, {useEffect, useRef, useState} from 'react';
import Button from '@mui/joy/Button';
import FormControl from '@mui/joy/FormControl';
import Input from '@mui/joy/Input';
import Stack from '@mui/joy/Stack';
import SearchRoundedIcon from '@mui/icons-material/SearchRounded';
import Typography from '@mui/joy/Typography';
import axios from "axios";
import MealKitService from "../MealKitService";

type SearchProps = {
    search_Value: string;
    handleChangeSearchValue: React.EventHandler;
    findByMealKits: React.EventHandler;
}

export default function Search({

    search_Value,
    handleChangeSearchValue,
    findByMealKits

                               }: SearchProps) {

    return (
        <div>
            <Stack spacing={1} direction="row" sx={{ mb: 2 }}>
                <FormControl sx={{ flex: 1 }}>
                    <Input
                        placeholder="Search"
                        startDecorator={<SearchRoundedIcon />}
                        value={search_Value}
                        onChange={handleChangeSearchValue}
                        aria-label="Search"
                    />
                </FormControl>
                <Button type="button" variant="solid" color="primary" onClick={findByMealKits}>
                    Search
                </Button>
            </Stack>
        </div>
    );
}