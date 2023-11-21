package com.son.project_a.service;


import com.son.project_a.domain.constant.SearchType;
import com.son.project_a.dto.MealKitDto;
import com.son.project_a.dto.MealKitWithCommentsDto;
import com.son.project_a.repository.MealKitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MealKitService {

    private final MealKitRepository mealKitRepository;


    @Transactional(readOnly = true)
    public Page<MealKitDto> searchByMealKitNames(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return mealKitRepository.findAll(pageable).map(MealKitDto::from);
        }

        if (Objects.requireNonNull(searchType) == SearchType.MNAME) {
            mealKitRepository.findByMName(searchKeyword, pageable).map(MealKitDto::from);
        }

        return Page.empty();
    }

    @Transactional(readOnly = true)
    public MealKitWithCommentsDto getMealKit(Long mealKitId) {
        return mealKitRepository.findById(mealKitId)
                .map(MealKitWithCommentsDto::from)
                .orElseThrow( () -> new EntityNotFoundException("MealKit가 없습니다."));
    }
}
