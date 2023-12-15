package com.son.project_a.service;


import com.son.project_a.domain.constant.SearchType;
import com.son.project_a.dto.MealKitDto;
import com.son.project_a.dto.MealKitWithCommentsAndImagesDto;
import com.son.project_a.repository.MealKitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MealKitService {

    private final MealKitRepository mealKitRepository;


    @Transactional(readOnly = true)
    public List<MealKitDto> searchMealKits(String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return mealKitRepository.findAll(pageable).map(MealKitDto::from).toList();
        }

        return mealKitRepository.findMealKitBymNameContaining(searchKeyword, pageable).map(MealKitDto::from).stream().toList();

    }

    @Transactional(readOnly = true)
    public MealKitWithCommentsAndImagesDto getMealKit(Long mealKitId) {
        return mealKitRepository.findById(mealKitId)
                .map(MealKitWithCommentsAndImagesDto::from)
                .orElseThrow( () -> new EntityNotFoundException("MealKit가 없습니다."));
    }
}
