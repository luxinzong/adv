package com.suma.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TsInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TsInfoExample() {
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

        public Criteria andTsIdIsNull() {
            addCriterion("TS_ID is null");
            return (Criteria) this;
        }

        public Criteria andTsIdIsNotNull() {
            addCriterion("TS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTsIdEqualTo(Long value) {
            addCriterion("TS_ID =", value, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdNotEqualTo(Long value) {
            addCriterion("TS_ID <>", value, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdGreaterThan(Long value) {
            addCriterion("TS_ID >", value, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TS_ID >=", value, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdLessThan(Long value) {
            addCriterion("TS_ID <", value, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdLessThanOrEqualTo(Long value) {
            addCriterion("TS_ID <=", value, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdIn(List<Long> values) {
            addCriterion("TS_ID in", values, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdNotIn(List<Long> values) {
            addCriterion("TS_ID not in", values, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdBetween(Long value1, Long value2) {
            addCriterion("TS_ID between", value1, value2, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsIdNotBetween(Long value1, Long value2) {
            addCriterion("TS_ID not between", value1, value2, "tsId");
            return (Criteria) this;
        }

        public Criteria andTsNameIsNull() {
            addCriterion("TS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTsNameIsNotNull() {
            addCriterion("TS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTsNameEqualTo(String value) {
            addCriterion("TS_NAME =", value, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameNotEqualTo(String value) {
            addCriterion("TS_NAME <>", value, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameGreaterThan(String value) {
            addCriterion("TS_NAME >", value, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameGreaterThanOrEqualTo(String value) {
            addCriterion("TS_NAME >=", value, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameLessThan(String value) {
            addCriterion("TS_NAME <", value, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameLessThanOrEqualTo(String value) {
            addCriterion("TS_NAME <=", value, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameLike(String value) {
            addCriterion("TS_NAME like", value, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameNotLike(String value) {
            addCriterion("TS_NAME not like", value, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameIn(List<String> values) {
            addCriterion("TS_NAME in", values, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameNotIn(List<String> values) {
            addCriterion("TS_NAME not in", values, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameBetween(String value1, String value2) {
            addCriterion("TS_NAME between", value1, value2, "tsName");
            return (Criteria) this;
        }

        public Criteria andTsNameNotBetween(String value1, String value2) {
            addCriterion("TS_NAME not between", value1, value2, "tsName");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIsNull() {
            addCriterion("NETWORK_ID is null");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIsNotNull() {
            addCriterion("NETWORK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNetworkIdEqualTo(Long value) {
            addCriterion("NETWORK_ID =", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotEqualTo(Long value) {
            addCriterion("NETWORK_ID <>", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdGreaterThan(Long value) {
            addCriterion("NETWORK_ID >", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdGreaterThanOrEqualTo(Long value) {
            addCriterion("NETWORK_ID >=", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdLessThan(Long value) {
            addCriterion("NETWORK_ID <", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdLessThanOrEqualTo(Long value) {
            addCriterion("NETWORK_ID <=", value, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdIn(List<Long> values) {
            addCriterion("NETWORK_ID in", values, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotIn(List<Long> values) {
            addCriterion("NETWORK_ID not in", values, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdBetween(Long value1, Long value2) {
            addCriterion("NETWORK_ID between", value1, value2, "networkId");
            return (Criteria) this;
        }

        public Criteria andNetworkIdNotBetween(Long value1, Long value2) {
            addCriterion("NETWORK_ID not between", value1, value2, "networkId");
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