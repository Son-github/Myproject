package com.son.project_a.controller;

import com.son.project_a.domain.MealKit;
import com.son.project_a.exception.ResourceNotFoundException;
import com.son.project_a.exception.ServerError;
import com.son.project_a.repository.MealKitRepository;
import com.son.project_a.service.MealKitService;
import com.son.project_a.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Pageable pagination = PageRequest.of(page, size);
            Page<MealKit> mealKitPage;

            if (searchValue == null) {
                mealKitPage = mealKitRepository.findAll(pagination);
            } else {
                mealKitPage = mealKitRepository.findBymName(searchValue, pagination);
            }

            mealKits = mealKitPage.getContent();
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
