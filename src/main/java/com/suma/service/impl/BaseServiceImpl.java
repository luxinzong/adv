package com.suma.service.impl;

import com.suma.dao.BaseDAO;
import com.suma.service.BaseService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseServiceImpl<T, Ex, PK extends Serializable> implements BaseService<T, Ex, PK> {

	private BaseDAO<T, Ex, PK> baseDAO;
	
	public void setBaseDao(BaseDAO<T, Ex, PK> baseDao) {
		this.baseDAO = baseDao;
	}
	
	public T findByPK(PK id) {
		return baseDAO.selectByPrimaryKey(id);
	}
	
	@Override
	public List<T> findALL() {
		// TODO Auto-generated method stub
		return baseDAO.selectByExample(null);
	}

	@Override
	public List<T> findByPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findList(@SuppressWarnings("unchecked") PK... ids) {
		// TODO Auto-generated method stub
		List<T> res = new ArrayList<T>();
		if (ids != null) {
			for (PK id : ids) {
				T t = baseDAO.selectByPrimaryKey(id);
				if (t != null) {
					res.add(t);
				}
			}
		}
		return res;
	}

	@Override
	public int save(T t) {
		// TODO Auto-generated method stub
		return baseDAO.insert(t);
	}

	@Override
	public int deleteByPK(PK pk) {
		// TODO Auto-generated method stub
		return baseDAO.deleteByPrimaryKey(pk);
	}

	@Override
	public int update(T t) {
		// TODO Auto-generated method stub
		return baseDAO.updateByPrimaryKey(t);
	}

	@Override
	public long countByExample(Ex ex) {
		// TODO Auto-generated method stub
		return baseDAO.countByExample(ex);
	}

	@Override
	public List<T> selectByExample(Ex ex) {
		// TODO Auto-generated method stub
		return baseDAO.selectByExample(ex);
	}

	@Override
	public int deleteByExample(Ex ex) {
		return baseDAO.deleteByExample(ex);
	}

	@Override
	public int updateByExample(T t, Ex ex) {
		return baseDAO.updateByExample(t,ex);
	}
}
