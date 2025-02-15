package com.test.admin.board;

import lombok.Data;

@Data
public class PageDTO {

	private long totalCount;
	private int blockSize;
	private int currentPage;
	private int lastPage;
	
	private int startBlockPage;
	private int endBlockPage;
	
	public PageDTO(int page, int size, long count, int blockSize) {
		this.totalCount = count;
		this.blockSize = blockSize;
		this.currentPage = page;
		this.lastPage = (int) Math.ceil((double)count / size);
		
		this.startBlockPage = page + 1;
		this.startBlockPage -= (page % blockSize) == 0 ? blockSize : page % blockSize;
		this.endBlockPage = Math.min(lastPage, this.startBlockPage + blockSize - 1);
	}
}
