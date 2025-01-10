package com.test.admin.board;

import lombok.Getter;

@Getter
public abstract class BoardDTO<T extends Board> {

	private Long seq;

    public abstract T toEntity();
}
