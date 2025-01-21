package com.test.admin.dao;

import static com.test.admin.entity.QNotice.notice;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.admin.entity.Notice;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductInfoQueryRepository {
	
	private final JPAQueryFactory jpaQueryFactory;

}
