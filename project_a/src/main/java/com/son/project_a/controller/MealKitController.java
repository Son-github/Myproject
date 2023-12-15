package com.son.project_a.controller;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitImage;
import com.son.project_a.domain.constant.SearchType;
import com.son.project_a.dto.MealKitDto;
import com.son.project_a.exception.ResourceNotFoundException;
import com.son.project_a.exception.ServerError;
import com.son.project_a.repository.MealKitRepository;
import com.son.project_a.response.MealKitResponse;
import com.son.project_a.service.MealKitService;
import com.son.project_a.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/mealKits")
@ResponseBody
@RestController
public class MealKitController {

    private final MealKitService mealKitService;
    private final PaginationService paginationService;

    @Autowired
    private MealKitRepository mealKitRepository;

    private Logger log = LoggerFactory.getLogger(MealKitController.class);


    @GetMapping
    public Map<String, Object> mealKits(
            @RequestParam(value = "m_name",required = false) String searchValue,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        try {
            List<MealKit> mealKits = new ArrayList<MealKit>();
            List<MealKitImage> mealKitImages = new ArrayList<MealKitImage>();
            Pageable pagination = PageRequest.of(page, size);
            Page<MealKit> mealKitPage;

            if (searchValue == null) {
                mealKitPage = mealKitRepository.findAll(pagination);
            } else {
                mealKitPage = mealKitRepository.findMealKitBymNameContaining(searchValue, pagination);
            }

            log.info("Page<MealKitDto> searchMealKits: {}", mealKitService.searchMealKits(searchValue, pagination));

            mealKits = mealKitPage.getContent();
            //List<MealKit> mealKitStream = mealKits.stream().filter(mealKit -> mealKit.getMName().contains("e")).collect(Collectors.toList());
            //List<String> mealKitImageStream = mealKitStream.stream().filter(mealKit -> mealKit.getMealKitComments());
            //log.info("mealKitStream: {}", mealKitStream);
            ///log.info("mealKits: {}", mealKits.get(0).getMealKitImages());
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("mealKits", mealKits);
            response.put("totalPages", mealKitPage.getTotalPages());

            return response;
        } catch (Exception e) {
            throw new ServerError(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public MealKit findById(@PathVariable("id") Long id) {

        MealKit mealKit = mealKitRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(
                        "MealKit Not Found"
                ));

        return mealKit;
    }


}
