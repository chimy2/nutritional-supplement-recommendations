package com.test.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminAuth is a Querydsl query type for AdminAuth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminAuth extends EntityPathBase<AdminAuth> {

    private static final long serialVersionUID = -1371607078L;

    public static final QAdminAuth adminAuth = new QAdminAuth("adminAuth");

    public final EnumPath<com.test.admin.auth.AdminRole> role = createEnum("role", com.test.admin.auth.AdminRole.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QAdminAuth(String variable) {
        super(AdminAuth.class, forVariable(variable));
    }

    public QAdminAuth(Path<? extends AdminAuth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminAuth(PathMetadata metadata) {
        super(AdminAuth.class, metadata);
    }

}

