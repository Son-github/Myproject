package com.son.project_a.controller;


import com.son.project_a.dto.request.MealKitCommentRequest;
import com.son.project_a.service.MealKitCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/detail")
@Controller
public class MealKitCommentController {

    private final MealKitCommentService mealKitCommentService;

}
