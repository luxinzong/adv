package com.suma.pojo;

import java.util.ArrayList;
import java.util.List;

public class AdvTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvTypeExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAdvtypeIsNull() {
            addCriterion("advType is null");
            return (Criteria) this;
        }

        public Criteria andAdvtypeIsNotNull() {
            addCriterion("advType is not null");
            return (Criteria) this;
        }

        public Criteria andAdvtypeEqualTo(String value) {
            addCriterion("advType =", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeNotEqualTo(String value) {
            addCriterion("advType <>", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeGreaterThan(String value) {
            addCriterion("advType >", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeGreaterThanOrEqualTo(String value) {
            addCriterion("advType >=", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeLessThan(String value) {
            addCriterion("advType <", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeLessThanOrEqualTo(String value) {
            addCriterion("advType <=", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeLike(String value) {
            addCriterion("advType like", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeNotLike(String value) {
            addCriterion("advType not like", value, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeIn(List<String> values) {
            addCriterion("advType in", values, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeNotIn(List<String> values) {
            addCriterion("advType not in", values, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeBetween(String value1, String value2) {
            addCriterion("advType between", value1, value2, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypeNotBetween(String value1, String value2) {
            addCriterion("advType not between", value1, value2, "advtype");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameIsNull() {
            addCriterion("advTypeName is null");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameIsNotNull() {
            addCriterion("advTypeName is not null");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameEqualTo(String value) {
            addCriterion("advTypeName =", value, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameNotEqualTo(String value) {
            addCriterion("advTypeName <>", value, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameGreaterThan(String value) {
            addCriterion("advTypeName >", value, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameGreaterThanOrEqualTo(String value) {
            addCriterion("advTypeName >=", value, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameLessThan(String value) {
            addCriterion("advTypeName <", value, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameLessThanOrEqualTo(String value) {
            addCriterion("advTypeName <=", value, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameLike(String value) {
            addCriterion("advTypeName like", value, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameNotLike(String value) {
            addCriterion("advTypeName not like", value, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameIn(List<String> values) {
            addCriterion("advTypeName in", values, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameNotIn(List<String> values) {
            addCriterion("advTypeName not in", values, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameBetween(String value1, String value2) {
            addCriterion("advTypeName between", value1, value2, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvtypenameNotBetween(String value1, String value2) {
            addCriterion("advTypeName not between", value1, value2, "advtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeIsNull() {
            addCriterion("advSubType is null");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeIsNotNull() {
            addCriterion("advSubType is not null");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeEqualTo(String value) {
            addCriterion("advSubType =", value, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeNotEqualTo(String value) {
            addCriterion("advSubType <>", value, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeGreaterThan(String value) {
            addCriterion("advSubType >", value, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeGreaterThanOrEqualTo(String value) {
            addCriterion("advSubType >=", value, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeLessThan(String value) {
            addCriterion("advSubType <", value, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeLessThanOrEqualTo(String value) {
            addCriterion("advSubType <=", value, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeLike(String value) {
            addCriterion("advSubType like", value, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeNotLike(String value) {
            addCriterion("advSubType not like", value, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeIn(List<String> values) {
            addCriterion("advSubType in", values, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeNotIn(List<String> values) {
            addCriterion("advSubType not in", values, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeBetween(String value1, String value2) {
            addCriterion("advSubType between", value1, value2, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypeNotBetween(String value1, String value2) {
            addCriterion("advSubType not between", value1, value2, "advsubtype");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameIsNull() {
            addCriterion("advSubTypeName is null");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameIsNotNull() {
            addCriterion("advSubTypeName is not null");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameEqualTo(String value) {
            addCriterion("advSubTypeName =", value, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameNotEqualTo(String value) {
            addCriterion("advSubTypeName <>", value, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameGreaterThan(String value) {
            addCriterion("advSubTypeName >", value, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameGreaterThanOrEqualTo(String value) {
            addCriterion("advSubTypeName >=", value, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameLessThan(String value) {
            addCriterion("advSubTypeName <", value, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameLessThanOrEqualTo(String value) {
            addCriterion("advSubTypeName <=", value, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameLike(String value) {
            addCriterion("advSubTypeName like", value, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameNotLike(String value) {
            addCriterion("advSubTypeName not like", value, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameIn(List<String> values) {
            addCriterion("advSubTypeName in", values, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameNotIn(List<String> values) {
            addCriterion("advSubTypeName not in", values, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameBetween(String value1, String value2) {
            addCriterion("advSubTypeName between", value1, value2, "advsubtypename");
            return (Criteria) this;
        }

        public Criteria andAdvsubtypenameNotBetween(String value1, String value2) {
            addCriterion("advSubTypeName not between", value1, value2, "advsubtypename");
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