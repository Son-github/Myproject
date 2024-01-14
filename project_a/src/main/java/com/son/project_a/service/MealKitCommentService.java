package com.son.project_a.service;

import com.son.project_a.domain.MealKit;
import com.son.project_a.domain.MealKitComment;
import com.son.project_a.domain.UserAccount;
import com.son.project_a.dto.MealKitCommentDto;
import com.son.project_a.repository.MealKitCommentRepository;
import com.son.project_a.repository.MealKitRepository;
import com.son.project_a.repository.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MealKitCommentService {

    Logger log = LoggerFactory.getLogger(MealKitCommentService.class);

    private final UserAccountRepository userAccountRepository;
    private final MealKitCommentRepository mealKitCommentRepository;
    private final MealKitRepository mealKitRepository;

    @Transactional(readOnly = true)
    public List<MealKitCommentDto> searchMealKitComment(long mealKitId) {
        return mealKitCommentRepository.findByMealKitId(mealKitId)
                .stream()
                .map(MealKitCommentDto::from)
                .toList();
    }

    public void saveMealKitComment(MealKitCommentDto dto) {
        try{
            MealKit mealKit = mealKitRepository.getReferenceById(dto.mealKitId());
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userEmail());
            MealKitComment mealKitComment = dto.toEntity(mealKit, userAccount);

            mealKitCommentRepository.save(mealKitComment);

        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패. 댓글의 게시글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    public void updateMealKitComment(MealKitCommentDto dto) {
        try{
            MealKitComment mealKitComment = mealKitCommentRepository.getReferenceById(dto.id());
            if (dto.content() != null ) { mealKitComment.setContent(dto.content()); }
            if (dto.title() != null) { mealKitComment.setTitle(dto.title()); }
        } catch (EntityNotFoundException e) {
            log.warn("댓글 업데이트 실패. 댓글을 찾을 수 없습니다. - dto: {}", dto);
        }
    }

    public void deleteMealKitComment(Long mealKitCommentId, String userEmail) {
        mealKitCommentRepository.deleteByIdAndUserAccount_UserEmail(mealKitCommentId, userEmail);
    }
}
