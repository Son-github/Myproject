package com.son.project_a.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class SearchController {
    // TODO: 일단 도메인부터 만들자!!
    private Logger log = LoggerFactory.getLogger(SearchController.class);

    @ResponseBody
    @PostMapping("/home")
    public void search(@RequestBody String searchValue) {
        log.info("받아온 searchValue: {}", searchValue);
    }
}
