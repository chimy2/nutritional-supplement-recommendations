package com.test.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = -356082827L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotice notice = new QNotice("notice");

    public final com.test.admin.board.QBoard _super = new com.test.admin.board.QBoard(this);

    public final QAdmin admin;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public QNotice(String variable) {
        this(Notice.class, forVariable(variable), INITS);
    }

    public QNotice(Path<? extends Notice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotice(PathMetadata metadata, PathInits inits) {
        this(Notice.class, metadata, inits);
    }

    public QNotice(Class<? extends Notice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin")) : null;
    }

}

