package com.suma.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, Ex, PK extends Serializable> {

	int save(T t);
	
	int deleteByPK(PK pk);
	
	int update(T t);
	
	T findByPK(PK id);
	
	List<T> findALL();
	
	List<T> findByPage();
	
	List<T> findList(@SuppressWarnings("unchecked") PK... ids);
	
	long countByExample(Ex ex);
	
	List<T> selectByExample(Ex ex);
}
