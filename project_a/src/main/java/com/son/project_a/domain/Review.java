package com.son.project_a.domain;

import io.micrometer.core.instrument.distribution.StepBucketHistogram;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @JoinColumn(name = "mealKitId")
    @ManyToOne(optional = false)
    private MealKit mealKit;

    @JoinColumn(name = "userAccountId")
    @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Column private String RWriter;
    @Column private String RContents;
    @Column private String RFile;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    protected Review() {}

    public Review(MealKit mealKit, UserAccount userAccount, String RWriter, String RContents, String RFile){
        this.mealKit = mealKit;
        this.userAccount = userAccount;
        this.RWriter = RWriter;
        this.RContents = RContents;
        this.RFile = RFile;
        this.createdAt = LocalDateTime.now();
    }

    public static Review of(MealKit mealKit, UserAccount userAccount, String RWriter, String RContents, String RFile) {
        return new Review(mealKit, userAccount, RWriter, RContents, RFile);
    }


}
