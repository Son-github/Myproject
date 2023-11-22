package com.son.project_a.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("페이지네이션")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
class PaginationServiceTest {

    private final PaginationService paginationService;

    PaginationServiceTest(@Autowired PaginationService paginationService) {
        this.paginationService = paginationService;
    }

    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면 페이징 바 리스트를 만들어준다.")
    @MethodSource
    @ParameterizedTest(name = "[{index}] 현재 페이지: {0}, 총 페이지: {1} => {2}")
    void givenCurrentPageNumberTotalPage_whenCalculating_thenReturnPaginationBarNumbers(int currentPageNumber, int totalPages, List<Integer> expected) {
        // Given

        // When
        List<Integer> actual = paginationService.getPaginationBarNumbers(currentPageNumber, totalPages);

        // Then
        assertThat(actual).isEqualTo(expected);

    }

    static Stream<Arguments> givenCurrentPageNumberTotalPage_whenCalculating_thenReturnPaginationBarNumbers() {
        return Stream.of(
                arguments(0, 10, List.of(0, 1, 2, 3, 4)),
                arguments(2, 10, List.of(0, 1, 2, 3, 4)),
                arguments(3, 10, List.of(1, 2, 3, 4, 5)),
                arguments(4, 10, List.of(2, 3, 4, 5, 6))
        );
    }

    @DisplayName("현재 설정되어 있는 페이지네이션 바의 길이를 알려준다.")
    @Test
    void givenNothing_whenCalling_thenReturnsCurrentBarLength() {
        // Given

        // When
        int barLength = paginationService.currentBarLength();

        // Then
        assertThat(barLength).isEqualTo(5);
    }
}