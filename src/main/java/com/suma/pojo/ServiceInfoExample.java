package com.suma.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ServiceInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNull() {
            addCriterion("SERVICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("SERVICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(String value) {
            addCriterion("SERVICE_ID =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(String value) {
            addCriterion("SERVICE_ID <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(String value) {
            addCriterion("SERVICE_ID >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_ID >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(String value) {
            addCriterion("SERVICE_ID <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_ID <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLike(String value) {
            addCriterion("SERVICE_ID like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotLike(String value) {
            addCriterion("SERVICE_ID not like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<String> values) {
            addCriterion("SERVICE_ID in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<String> values) {
            addCriterion("SERVICE_ID not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(String value1, String value2) {
            addCriterion("SERVICE_ID between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(String value1, String value2) {
            addCriterion("SERVICE_ID not between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andTidIsNull() {
            addCriterion("TID is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("TID is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Long value) {
            addCriterion("TID =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Long value) {
            addCriterion("TID <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Long value) {
            addCriterion("TID >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Long value) {
            addCriterion("TID >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Long value) {
            addCriterion("TID <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Long value) {
            addCriterion("TID <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Long> values) {
            addCriterion("TID in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Long> values) {
            addCriterion("TID not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Long value1, Long value2) {
            addCriterion("TID between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Long value1, Long value2) {
            addCriterion("TID not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNull() {
            addCriterion("SERVICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNotNull() {
            addCriterion("SERVICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andServiceNameEqualTo(String value) {
            addCriterion("SERVICE_NAME =", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotEqualTo(String value) {
            addCriterion("SERVICE_NAME <>", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThan(String value) {
            addCriterion("SERVICE_NAME >", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_NAME >=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThan(String value) {
            addCriterion("SERVICE_NAME <", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_NAME <=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLike(String value) {
            addCriterion("SERVICE_NAME like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotLike(String value) {
            addCriterion("SERVICE_NAME not like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameIn(List<String> values) {
            addCriterion("SERVICE_NAME in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotIn(List<String> values) {
            addCriterion("SERVICE_NAME not in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameBetween(String value1, String value2) {
            addCriterion("SERVICE_NAME between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotBetween(String value1, String value2) {
            addCriterion("SERVICE_NAME not between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("MARK is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("MARK is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("MARK =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("MARK <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("MARK >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("MARK >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("MARK <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("MARK <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("MARK like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("MARK not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("MARK in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("MARK not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("MARK between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("MARK not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIsNull() {
            addCriterion("CREATED_USER is null");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIsNotNull() {
            addCriterion("CREATED_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedUserEqualTo(String value) {
            addCriterion("CREATED_USER =", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserNotEqualTo(String value) {
            addCriterion("CREATED_USER <>", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserGreaterThan(String value) {
            addCriterion("CREATED_USER >", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserGreaterThanOrEqualTo(String value) {
            addCriterion("CREATED_USER >=", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserLessThan(String value) {
            addCriterion("CREATED_USER <", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserLessThanOrEqualTo(String value) {
            addCriterion("CREATED_USER <=", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserLike(String value) {
            addCriterion("CREATED_USER like", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserNotLike(String value) {
            addCriterion("CREATED_USER not like", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIn(List<String> values) {
            addCriterion("CREATED_USER in", values, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserNotIn(List<String> values) {
            addCriterion("CREATED_USER not in", values, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserBetween(String value1, String value2) {
            addCriterion("CREATED_USER between", value1, value2, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserNotBetween(String value1, String value2) {
            addCriterion("CREATED_USER not between", value1, value2, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("CREATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("CREATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("CREATED_TIME =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("CREATED_TIME <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("CREATED_TIME >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATED_TIME >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("CREATED_TIME <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATED_TIME <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("CREATED_TIME in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("CREATED_TIME not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("CREATED_TIME between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATED_TIME not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeIsNull() {
            addCriterion("LAST_EDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeIsNotNull() {
            addCriterion("LAST_EDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeEqualTo(Date value) {
            addCriterion("LAST_EDIT_TIME =", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeNotEqualTo(Date value) {
            addCriterion("LAST_EDIT_TIME <>", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeGreaterThan(Date value) {
            addCriterion("LAST_EDIT_TIME >", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_EDIT_TIME >=", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeLessThan(Date value) {
            addCriterion("LAST_EDIT_TIME <", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeLessThanOrEqualTo(Date value) {
            addCriterion("LAST_EDIT_TIME <=", value, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeIn(List<Date> values) {
            addCriterion("LAST_EDIT_TIME in", values, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeNotIn(List<Date> values) {
            addCriterion("LAST_EDIT_TIME not in", values, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeBetween(Date value1, Date value2) {
            addCriterion("LAST_EDIT_TIME between", value1, value2, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditTimeNotBetween(Date value1, Date value2) {
            addCriterion("LAST_EDIT_TIME not between", value1, value2, "lastEditTime");
            return (Criteria) this;
        }

        public Criteria andLastEditUserIsNull() {
            addCriterion("LAST_EDIT_USER is null");
            return (Criteria) this;
        }

        public Criteria andLastEditUserIsNotNull() {
            addCriterion("LAST_EDIT_USER is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditUserEqualTo(String value) {
            addCriterion("LAST_EDIT_USER =", value, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserNotEqualTo(String value) {
            addCriterion("LAST_EDIT_USER <>", value, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserGreaterThan(String value) {
            addCriterion("LAST_EDIT_USER >", value, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_EDIT_USER >=", value, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserLessThan(String value) {
            addCriterion("LAST_EDIT_USER <", value, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserLessThanOrEqualTo(String value) {
            addCriterion("LAST_EDIT_USER <=", value, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserLike(String value) {
            addCriterion("LAST_EDIT_USER like", value, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserNotLike(String value) {
            addCriterion("LAST_EDIT_USER not like", value, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserIn(List<String> values) {
            addCriterion("LAST_EDIT_USER in", values, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserNotIn(List<String> values) {
            addCriterion("LAST_EDIT_USER not in", values, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserBetween(String value1, String value2) {
            addCriterion("LAST_EDIT_USER between", value1, value2, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andLastEditUserNotBetween(String value1, String value2) {
            addCriterion("LAST_EDIT_USER not between", value1, value2, "lastEditUser");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("REGION is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("REGION is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(Integer value) {
            addCriterion("REGION =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(Integer value) {
            addCriterion("REGION <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(Integer value) {
            addCriterion("REGION >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(Integer value) {
            addCriterion("REGION >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(Integer value) {
            addCriterion("REGION <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(Integer value) {
            addCriterion("REGION <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<Integer> values) {
            addCriterion("REGION in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<Integer> values) {
            addCriterion("REGION not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(Integer value1, Integer value2) {
            addCriterion("REGION between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(Integer value1, Integer value2) {
            addCriterion("REGION not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andFreqIsNull() {
            addCriterion("FREQ is null");
            return (Criteria) this;
        }

        public Criteria andFreqIsNotNull() {
            addCriterion("FREQ is not null");
            return (Criteria) this;
        }

        public Criteria andFreqEqualTo(String value) {
            addCriterion("FREQ =", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqNotEqualTo(String value) {
            addCriterion("FREQ <>", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqGreaterThan(String value) {
            addCriterion("FREQ >", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqGreaterThanOrEqualTo(String value) {
            addCriterion("FREQ >=", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqLessThan(String value) {
            addCriterion("FREQ <", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqLessThanOrEqualTo(String value) {
            addCriterion("FREQ <=", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqLike(String value) {
            addCriterion("FREQ like", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqNotLike(String value) {
            addCriterion("FREQ not like", value, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqIn(List<String> values) {
            addCriterion("FREQ in", values, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqNotIn(List<String> values) {
            addCriterion("FREQ not in", values, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqBetween(String value1, String value2) {
            addCriterion("FREQ between", value1, value2, "freq");
            return (Criteria) this;
        }

        public Criteria andFreqNotBetween(String value1, String value2) {
            addCriterion("FREQ not between", value1, value2, "freq");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}