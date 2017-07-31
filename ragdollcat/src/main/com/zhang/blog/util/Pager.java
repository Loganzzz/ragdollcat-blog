package main.com.zhang.blog.util;

import java.util.List;

/** * @author zhang_chl 
    * @date 2017年7月21日下午9:39:25
    */

//工具类，通过这个类返回结果
public class Pager<T> {
	//结果集
	private List<T> list;
	//结果数
	private int totalRecords;
	//总页数
	private int totalPages;
	//每页的记录数
	private int pageSize;
	//第几页
	private int currentPage;
	//下一页
	private int nextPage;
	//上一页
	private int lastPage;

	public Pager() {
	}
	
	public Pager(List<T> list, int totalRecords, int totalPages, int pageSize, int currentPage, int nextPage,
			int lastPage) {
		this.list = list;
		this.totalRecords = totalRecords;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.nextPage = nextPage;
		this.lastPage = lastPage;
	}
	
	
	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	
}
