package com.son.project_a.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMealKitComment is a Querydsl query type for MealKitComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMealKitComment extends EntityPathBase<MealKitComment> {

    private static final long serialVersionUID = -319420902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMealKitComment mealKitComment = new QMealKitComment("mealKitComment");

    public final QAuditingFields _super = new QAuditingFields(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMealKit mealKit;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath title = createString("title");

    public final QUserAccount userAccount;

    public QMealKitComment(String variable) {
        this(MealKitComment.class, forVariable(variable), INITS);
    }

    public QMealKitComment(Path<? extends MealKitComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMealKitComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMealKitComment(PathMetadata metadata, PathInits inits) {
        this(MealKitComment.class, metadata, inits);
    }

    public QMealKitComment(Class<? extends MealKitComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mealKit = inits.isInitialized("mealKit") ? new QMealKit(forProperty("mealKit")) : null;
        this.userAccount = inits.isInitialized("userAccount") ? new QUserAccount(forProperty("userAccount")) : null;
    }

}

