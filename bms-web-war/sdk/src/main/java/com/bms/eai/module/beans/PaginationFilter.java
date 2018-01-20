package com.bms.eai.module.beans;

import java.util.List;

import com.bms.eai.module.core.AbstractSdkEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author kul_sudhakar
 *
 */
@JsonRootName("Pagination")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationFilter extends AbstractSdkEntity {

	@JsonProperty("first")
	private Boolean first;

	@JsonProperty("last")
	private Boolean last;

	@JsonProperty("numberOfElements")
	private Integer numberOfElements;

	@JsonProperty("totalPages")
	private Integer totalPages;

	@JsonProperty("totalElements")
	private Integer totalElements;

	@JsonProperty("size")
	private Integer size;

	@JsonProperty("number")
	private Integer number;

	@JsonProperty("sort")
	private List<SortPageFilter> sort;

	public Boolean getFirst() {
		return first;
	}

	public void setFirst(Boolean first) {
		this.first = first;
	}

	public Boolean getLast() {
		return last;
	}

	public void setLast(Boolean last) {
		this.last = last;
	}

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<SortPageFilter> getSort() {
		return sort;
	}

	public void setSort(List<SortPageFilter> sort) {
		this.sort = sort;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

}
