package com.sy.cafe.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCreatedTimeEntity is a Querydsl query type for CreatedTimeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCreatedTimeEntity extends EntityPathBase<CreatedTimeEntity> {

    private static final long serialVersionUID = -1329177660L;

    public static final QCreatedTimeEntity createdTimeEntity = new QCreatedTimeEntity("createdTimeEntity");

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public QCreatedTimeEntity(String variable) {
        super(CreatedTimeEntity.class, forVariable(variable));
    }

    public QCreatedTimeEntity(Path<? extends CreatedTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCreatedTimeEntity(PathMetadata metadata) {
        super(CreatedTimeEntity.class, metadata);
    }

}

