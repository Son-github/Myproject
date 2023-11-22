package com.son.project_a.controller;

import com.son.project_a.domain.constant.SearchType;
import com.son.project_a.response.MealKitResponse;
import com.son.project_a.response.MealKitWithCommentsResponse;
import com.son.project_a.service.MealKitService;
import com.son.project_a.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/mealKits")
@Controller
public class MealKitController {

    private final MealKitService mealKitService;
    private final PaginationService paginationService;
    private Logger log = LoggerFactory.getLogger(MealKitController.class);

    @GetMapping
    public String mealKits(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, ModelMap map) {

        Page<MealKitResponse> mealKits = mealKitService.searchByMealKitNames(searchType, searchValue, pageable).map(MealKitResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), mealKits.getTotalPages());


        map.addAttribute("mealKits", mealKits);
        map.addAttribute("paginationBarNumbers", barNumbers);

        return "mealKits/index";
    }


    @GetMapping("/{mealKitId}")
    public String mealKit(@PathVariable Long mealKitId, ModelMap map) {
        MealKitWithCommentsResponse mealKitWithCommentsResponse = MealKitWithCommentsResponse.from(mealKitService.getMealKit(mealKitId));
        map.addAttribute("mealKit", mealKitWithCommentsResponse);
        map.addAttribute("mealKitComments", mealKitWithCommentsResponse.mealKitCommentResponses());

        return "mealKits/detail";
    }
}
