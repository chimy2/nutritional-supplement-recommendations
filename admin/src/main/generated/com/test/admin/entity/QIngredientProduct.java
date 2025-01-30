package com.test.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIngredientProduct is a Querydsl query type for IngredientProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredientProduct extends EntityPathBase<IngredientProduct> {

    private static final long serialVersionUID = 484531489L;

    public static final QIngredientProduct ingredientProduct = new QIngredientProduct("ingredientProduct");

    public final NumberPath<Long> ingredientSeq = createNumber("ingredientSeq", Long.class);

    public final NumberPath<Long> productSeq = createNumber("productSeq", Long.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QIngredientProduct(String variable) {
        super(IngredientProduct.class, forVariable(variable));
    }

    public QIngredientProduct(Path<? extends IngredientProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIngredientProduct(PathMetadata metadata) {
        super(IngredientProduct.class, metadata);
    }

}

