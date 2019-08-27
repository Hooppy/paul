package com.paul.model;

public class PagingCriteria {
	private int pageNum = 1; // 페이지번호
	private int amount = 10; // 페이지당 데이터 개수
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
