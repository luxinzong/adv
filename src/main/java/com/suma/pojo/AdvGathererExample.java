package com.suma.pojo;

import java.util.ArrayList;
import java.util.List;

public class AdvGathererExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvGathererExample() {
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

        public Criteria andRegionIdIsNull() {
            addCriterion("REGION_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("REGION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(Integer value) {
            addCriterion("REGION_ID =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(Integer value) {
            addCriterion("REGION_ID <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(Integer value) {
            addCriterion("REGION_ID >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("REGION_ID >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(Integer value) {
            addCriterion("REGION_ID <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(Integer value) {
            addCriterion("REGION_ID <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<Integer> values) {
            addCriterion("REGION_ID in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<Integer> values) {
            addCriterion("REGION_ID not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(Integer value1, Integer value2) {
            addCriterion("REGION_ID between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("REGION_ID not between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeIsNull() {
            addCriterion("HIGH_STANDARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeIsNotNull() {
            addCriterion("HIGH_STANDARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeEqualTo(String value) {
            addCriterion("HIGH_STANDARD_TYPE =", value, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeNotEqualTo(String value) {
            addCriterion("HIGH_STANDARD_TYPE <>", value, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeGreaterThan(String value) {
            addCriterion("HIGH_STANDARD_TYPE >", value, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("HIGH_STANDARD_TYPE >=", value, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeLessThan(String value) {
            addCriterion("HIGH_STANDARD_TYPE <", value, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeLessThanOrEqualTo(String value) {
            addCriterion("HIGH_STANDARD_TYPE <=", value, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeLike(String value) {
            addCriterion("HIGH_STANDARD_TYPE like", value, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeNotLike(String value) {
            addCriterion("HIGH_STANDARD_TYPE not like", value, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeIn(List<String> values) {
            addCriterion("HIGH_STANDARD_TYPE in", values, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeNotIn(List<String> values) {
            addCriterion("HIGH_STANDARD_TYPE not in", values, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeBetween(String value1, String value2) {
            addCriterion("HIGH_STANDARD_TYPE between", value1, value2, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andHighStandardTypeNotBetween(String value1, String value2) {
            addCriterion("HIGH_STANDARD_TYPE not between", value1, value2, "highStandardType");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
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