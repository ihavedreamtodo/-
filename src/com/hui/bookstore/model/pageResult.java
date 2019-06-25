package com.hui.bookstore.model;

import java.util.List;

//封装数据在一个list里面
public class pageResult<T> {

	List<T> list;
	long totalCount;//商品总条数
	int pageSize = 4;//每页显示条数
	int totalPage;//总页数
	int currentPage;//当前页数
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "pageResult [totalCount=" + totalCount + ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", currentPage=" + currentPage + "]";
	}
	
	
	
}
