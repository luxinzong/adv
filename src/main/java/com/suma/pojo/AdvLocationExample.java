package com.suma.pojo;

import java.util.ArrayList;
import java.util.List;

public class AdvLocationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvLocationExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdIsNull() {
            addCriterion("ADV_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdIsNotNull() {
            addCriterion("ADV_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdEqualTo(Long value) {
            addCriterion("ADV_TYPE_ID =", value, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdNotEqualTo(Long value) {
            addCriterion("ADV_TYPE_ID <>", value, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdGreaterThan(Long value) {
            addCriterion("ADV_TYPE_ID >", value, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ADV_TYPE_ID >=", value, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdLessThan(Long value) {
            addCriterion("ADV_TYPE_ID <", value, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("ADV_TYPE_ID <=", value, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdIn(List<Long> values) {
            addCriterion("ADV_TYPE_ID in", values, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdNotIn(List<Long> values) {
            addCriterion("ADV_TYPE_ID not in", values, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdBetween(Long value1, Long value2) {
            addCriterion("ADV_TYPE_ID between", value1, value2, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("ADV_TYPE_ID not between", value1, value2, "advTypeId");
            return (Criteria) this;
        }

        public Criteria andXPositionIsNull() {
            addCriterion("X_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andXPositionIsNotNull() {
            addCriterion("X_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andXPositionEqualTo(Long value) {
            addCriterion("X_POSITION =", value, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionNotEqualTo(Long value) {
            addCriterion("X_POSITION <>", value, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionGreaterThan(Long value) {
            addCriterion("X_POSITION >", value, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionGreaterThanOrEqualTo(Long value) {
            addCriterion("X_POSITION >=", value, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionLessThan(Long value) {
            addCriterion("X_POSITION <", value, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionLessThanOrEqualTo(Long value) {
            addCriterion("X_POSITION <=", value, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionIn(List<Long> values) {
            addCriterion("X_POSITION in", values, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionNotIn(List<Long> values) {
            addCriterion("X_POSITION not in", values, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionBetween(Long value1, Long value2) {
            addCriterion("X_POSITION between", value1, value2, "xPosition");
            return (Criteria) this;
        }

        public Criteria andXPositionNotBetween(Long value1, Long value2) {
            addCriterion("X_POSITION not between", value1, value2, "xPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionIsNull() {
            addCriterion("Y_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andYPositionIsNotNull() {
            addCriterion("Y_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andYPositionEqualTo(Long value) {
            addCriterion("Y_POSITION =", value, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionNotEqualTo(Long value) {
            addCriterion("Y_POSITION <>", value, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionGreaterThan(Long value) {
            addCriterion("Y_POSITION >", value, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionGreaterThanOrEqualTo(Long value) {
            addCriterion("Y_POSITION >=", value, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionLessThan(Long value) {
            addCriterion("Y_POSITION <", value, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionLessThanOrEqualTo(Long value) {
            addCriterion("Y_POSITION <=", value, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionIn(List<Long> values) {
            addCriterion("Y_POSITION in", values, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionNotIn(List<Long> values) {
            addCriterion("Y_POSITION not in", values, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionBetween(Long value1, Long value2) {
            addCriterion("Y_POSITION between", value1, value2, "yPosition");
            return (Criteria) this;
        }

        public Criteria andYPositionNotBetween(Long value1, Long value2) {
            addCriterion("Y_POSITION not between", value1, value2, "yPosition");
            return (Criteria) this;
        }

        public Criteria andMWidthIsNull() {
            addCriterion("M_WIDTH is null");
            return (Criteria) this;
        }

        public Criteria andMWidthIsNotNull() {
            addCriterion("M_WIDTH is not null");
            return (Criteria) this;
        }

        public Criteria andMWidthEqualTo(Long value) {
            addCriterion("M_WIDTH =", value, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthNotEqualTo(Long value) {
            addCriterion("M_WIDTH <>", value, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthGreaterThan(Long value) {
            addCriterion("M_WIDTH >", value, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthGreaterThanOrEqualTo(Long value) {
            addCriterion("M_WIDTH >=", value, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthLessThan(Long value) {
            addCriterion("M_WIDTH <", value, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthLessThanOrEqualTo(Long value) {
            addCriterion("M_WIDTH <=", value, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthIn(List<Long> values) {
            addCriterion("M_WIDTH in", values, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthNotIn(List<Long> values) {
            addCriterion("M_WIDTH not in", values, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthBetween(Long value1, Long value2) {
            addCriterion("M_WIDTH between", value1, value2, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMWidthNotBetween(Long value1, Long value2) {
            addCriterion("M_WIDTH not between", value1, value2, "mWidth");
            return (Criteria) this;
        }

        public Criteria andMHeightIsNull() {
            addCriterion("M_HEIGHT is null");
            return (Criteria) this;
        }

        public Criteria andMHeightIsNotNull() {
            addCriterion("M_HEIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andMHeightEqualTo(Long value) {
            addCriterion("M_HEIGHT =", value, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightNotEqualTo(Long value) {
            addCriterion("M_HEIGHT <>", value, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightGreaterThan(Long value) {
            addCriterion("M_HEIGHT >", value, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightGreaterThanOrEqualTo(Long value) {
            addCriterion("M_HEIGHT >=", value, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightLessThan(Long value) {
            addCriterion("M_HEIGHT <", value, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightLessThanOrEqualTo(Long value) {
            addCriterion("M_HEIGHT <=", value, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightIn(List<Long> values) {
            addCriterion("M_HEIGHT in", values, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightNotIn(List<Long> values) {
            addCriterion("M_HEIGHT not in", values, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightBetween(Long value1, Long value2) {
            addCriterion("M_HEIGHT between", value1, value2, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMHeightNotBetween(Long value1, Long value2) {
            addCriterion("M_HEIGHT not between", value1, value2, "mHeight");
            return (Criteria) this;
        }

        public Criteria andMaskIsNull() {
            addCriterion("MASK is null");
            return (Criteria) this;
        }

        public Criteria andMaskIsNotNull() {
            addCriterion("MASK is not null");
            return (Criteria) this;
        }

        public Criteria andMaskEqualTo(String value) {
            addCriterion("MASK =", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskNotEqualTo(String value) {
            addCriterion("MASK <>", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskGreaterThan(String value) {
            addCriterion("MASK >", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskGreaterThanOrEqualTo(String value) {
            addCriterion("MASK >=", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskLessThan(String value) {
            addCriterion("MASK <", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskLessThanOrEqualTo(String value) {
            addCriterion("MASK <=", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskLike(String value) {
            addCriterion("MASK like", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskNotLike(String value) {
            addCriterion("MASK not like", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskIn(List<String> values) {
            addCriterion("MASK in", values, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskNotIn(List<String> values) {
            addCriterion("MASK not in", values, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskBetween(String value1, String value2) {
            addCriterion("MASK between", value1, value2, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskNotBetween(String value1, String value2) {
            addCriterion("MASK not between", value1, value2, "mask");
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