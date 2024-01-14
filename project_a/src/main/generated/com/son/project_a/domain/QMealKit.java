package com.son.project_a.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMealKit is a Querydsl query type for MealKit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMealKit extends EntityPathBase<MealKit> {

    private static final long serialVersionUID = -367341435L;

    public static final QMealKit mealKit = new QMealKit("mealKit");

    public final QAuditingFields _super = new QAuditingFields(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mCategory = createString("mCategory");

    public final StringPath mContent = createString("mContent");

    public final SetPath<MealKitComment, QMealKitComment> mealKitComments = this.<MealKitComment, QMealKitComment>createSet("mealKitComments", MealKitComment.class, QMealKitComment.class, PathInits.DIRECT2);

    public final SetPath<MealKitImage, QMealKitImage> mealKitImages = this.<MealKitImage, QMealKitImage>createSet("mealKitImages", MealKitImage.class, QMealKitImage.class, PathInits.DIRECT2);

    public final SetPath<MealKitSite, QMealKitSite> mealKitSites = this.<MealKitSite, QMealKitSite>createSet("mealKitSites", MealKitSite.class, QMealKitSite.class, PathInits.DIRECT2);

    public final StringPath mName = createString("mName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> mPrice = createNumber("mPrice", Integer.class);

    public final StringPath mSaleCompany = createString("mSaleCompany");

    public final NumberPath<Integer> mSaleUnit = createNumber("mSaleUnit", Integer.class);

    public final NumberPath<Integer> mWeight = createNumber("mWeight", Integer.class);

    public QMealKit(String variable) {
        super(MealKit.class, forVariable(variable));
    }

    public QMealKit(Path<? extends MealKit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMealKit(PathMetadata metadata) {
        super(MealKit.class, metadata);
    }

}

