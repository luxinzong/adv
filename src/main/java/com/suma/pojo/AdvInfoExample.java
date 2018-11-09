package com.suma.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AdvInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvInfoExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
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

        public Criteria andAdvLocationIdIsNull() {
            addCriterion("ADV_LOCATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdIsNotNull() {
            addCriterion("ADV_LOCATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdEqualTo(Long value) {
            addCriterion("ADV_LOCATION_ID =", value, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdNotEqualTo(Long value) {
            addCriterion("ADV_LOCATION_ID <>", value, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdGreaterThan(Long value) {
            addCriterion("ADV_LOCATION_ID >", value, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ADV_LOCATION_ID >=", value, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdLessThan(Long value) {
            addCriterion("ADV_LOCATION_ID <", value, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdLessThanOrEqualTo(Long value) {
            addCriterion("ADV_LOCATION_ID <=", value, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdIn(List<Long> values) {
            addCriterion("ADV_LOCATION_ID in", values, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdNotIn(List<Long> values) {
            addCriterion("ADV_LOCATION_ID not in", values, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdBetween(Long value1, Long value2) {
            addCriterion("ADV_LOCATION_ID between", value1, value2, "advLocationId");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIdNotBetween(Long value1, Long value2) {
            addCriterion("ADV_LOCATION_ID not between", value1, value2, "advLocationId");
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

        public Criteria andMaterialTypeIsNull() {
            addCriterion("MATERIAL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIsNotNull() {
            addCriterion("MATERIAL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeEqualTo(Integer value) {
            addCriterion("MATERIAL_TYPE =", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotEqualTo(Integer value) {
            addCriterion("MATERIAL_TYPE <>", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeGreaterThan(Integer value) {
            addCriterion("MATERIAL_TYPE >", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MATERIAL_TYPE >=", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeLessThan(Integer value) {
            addCriterion("MATERIAL_TYPE <", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeLessThanOrEqualTo(Integer value) {
            addCriterion("MATERIAL_TYPE <=", value, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeIn(List<Integer> values) {
            addCriterion("MATERIAL_TYPE in", values, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotIn(List<Integer> values) {
            addCriterion("MATERIAL_TYPE not in", values, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeBetween(Integer value1, Integer value2) {
            addCriterion("MATERIAL_TYPE between", value1, value2, "materialType");
            return (Criteria) this;
        }

        public Criteria andMaterialTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("MATERIAL_TYPE not between", value1, value2, "materialType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterionForJDBCDate("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_DATE not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartIsNull() {
            addCriterion("PERIOD_TIME_START is null");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartIsNotNull() {
            addCriterion("PERIOD_TIME_START is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartEqualTo(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_START =", value, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartNotEqualTo(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_START <>", value, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartGreaterThan(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_START >", value, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_START >=", value, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartLessThan(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_START <", value, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_START <=", value, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartIn(List<Date> values) {
            addCriterionForJDBCTime("PERIOD_TIME_START in", values, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartNotIn(List<Date> values) {
            addCriterionForJDBCTime("PERIOD_TIME_START not in", values, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("PERIOD_TIME_START between", value1, value2, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeStartNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("PERIOD_TIME_START not between", value1, value2, "periodTimeStart");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndIsNull() {
            addCriterion("PERIOD_TIME_END is null");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndIsNotNull() {
            addCriterion("PERIOD_TIME_END is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndEqualTo(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_END =", value, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndNotEqualTo(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_END <>", value, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndGreaterThan(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_END >", value, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_END >=", value, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndLessThan(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_END <", value, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("PERIOD_TIME_END <=", value, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndIn(List<Date> values) {
            addCriterionForJDBCTime("PERIOD_TIME_END in", values, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndNotIn(List<Date> values) {
            addCriterionForJDBCTime("PERIOD_TIME_END not in", values, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("PERIOD_TIME_END between", value1, value2, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andPeriodTimeEndNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("PERIOD_TIME_END not between", value1, value2, "periodTimeEnd");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNull() {
            addCriterion("CHECK_USER is null");
            return (Criteria) this;
        }

        public Criteria andCheckUserIsNotNull() {
            addCriterion("CHECK_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCheckUserEqualTo(String value) {
            addCriterion("CHECK_USER =", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotEqualTo(String value) {
            addCriterion("CHECK_USER <>", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThan(String value) {
            addCriterion("CHECK_USER >", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_USER >=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThan(String value) {
            addCriterion("CHECK_USER <", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLessThanOrEqualTo(String value) {
            addCriterion("CHECK_USER <=", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserLike(String value) {
            addCriterion("CHECK_USER like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotLike(String value) {
            addCriterion("CHECK_USER not like", value, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserIn(List<String> values) {
            addCriterion("CHECK_USER in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotIn(List<String> values) {
            addCriterion("CHECK_USER not in", values, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserBetween(String value1, String value2) {
            addCriterion("CHECK_USER between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckUserNotBetween(String value1, String value2) {
            addCriterion("CHECK_USER not between", value1, value2, "checkUser");
            return (Criteria) this;
        }

        public Criteria andCheckNoteIsNull() {
            addCriterion("CHECK_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andCheckNoteIsNotNull() {
            addCriterion("CHECK_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andCheckNoteEqualTo(String value) {
            addCriterion("CHECK_NOTE =", value, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteNotEqualTo(String value) {
            addCriterion("CHECK_NOTE <>", value, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteGreaterThan(String value) {
            addCriterion("CHECK_NOTE >", value, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_NOTE >=", value, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteLessThan(String value) {
            addCriterion("CHECK_NOTE <", value, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteLessThanOrEqualTo(String value) {
            addCriterion("CHECK_NOTE <=", value, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteLike(String value) {
            addCriterion("CHECK_NOTE like", value, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteNotLike(String value) {
            addCriterion("CHECK_NOTE not like", value, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteIn(List<String> values) {
            addCriterion("CHECK_NOTE in", values, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteNotIn(List<String> values) {
            addCriterion("CHECK_NOTE not in", values, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteBetween(String value1, String value2) {
            addCriterion("CHECK_NOTE between", value1, value2, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckNoteNotBetween(String value1, String value2) {
            addCriterion("CHECK_NOTE not between", value1, value2, "checkNote");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("CHECK_TIME =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("CHECK_TIME <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("CHECK_TIME >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHECK_TIME >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("CHECK_TIME <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHECK_TIME <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("CHECK_TIME in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("CHECK_TIME not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("CHECK_TIME between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHECK_TIME not between", value1, value2, "checkTime");
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

        public Criteria andLastEditModuleIsNull() {
            addCriterion("LAST_EDIT_MODULE is null");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleIsNotNull() {
            addCriterion("LAST_EDIT_MODULE is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleEqualTo(String value) {
            addCriterion("LAST_EDIT_MODULE =", value, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleNotEqualTo(String value) {
            addCriterion("LAST_EDIT_MODULE <>", value, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleGreaterThan(String value) {
            addCriterion("LAST_EDIT_MODULE >", value, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_EDIT_MODULE >=", value, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleLessThan(String value) {
            addCriterion("LAST_EDIT_MODULE <", value, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleLessThanOrEqualTo(String value) {
            addCriterion("LAST_EDIT_MODULE <=", value, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleLike(String value) {
            addCriterion("LAST_EDIT_MODULE like", value, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleNotLike(String value) {
            addCriterion("LAST_EDIT_MODULE not like", value, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleIn(List<String> values) {
            addCriterion("LAST_EDIT_MODULE in", values, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleNotIn(List<String> values) {
            addCriterion("LAST_EDIT_MODULE not in", values, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleBetween(String value1, String value2) {
            addCriterion("LAST_EDIT_MODULE between", value1, value2, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andLastEditModuleNotBetween(String value1, String value2) {
            addCriterion("LAST_EDIT_MODULE not between", value1, value2, "lastEditModule");
            return (Criteria) this;
        }

        public Criteria andReservedStringIsNull() {
            addCriterion("RESERVED_STRING is null");
            return (Criteria) this;
        }

        public Criteria andReservedStringIsNotNull() {
            addCriterion("RESERVED_STRING is not null");
            return (Criteria) this;
        }

        public Criteria andReservedStringEqualTo(String value) {
            addCriterion("RESERVED_STRING =", value, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringNotEqualTo(String value) {
            addCriterion("RESERVED_STRING <>", value, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringGreaterThan(String value) {
            addCriterion("RESERVED_STRING >", value, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringGreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED_STRING >=", value, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringLessThan(String value) {
            addCriterion("RESERVED_STRING <", value, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringLessThanOrEqualTo(String value) {
            addCriterion("RESERVED_STRING <=", value, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringLike(String value) {
            addCriterion("RESERVED_STRING like", value, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringNotLike(String value) {
            addCriterion("RESERVED_STRING not like", value, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringIn(List<String> values) {
            addCriterion("RESERVED_STRING in", values, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringNotIn(List<String> values) {
            addCriterion("RESERVED_STRING not in", values, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringBetween(String value1, String value2) {
            addCriterion("RESERVED_STRING between", value1, value2, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedStringNotBetween(String value1, String value2) {
            addCriterion("RESERVED_STRING not between", value1, value2, "reservedString");
            return (Criteria) this;
        }

        public Criteria andReservedIntIsNull() {
            addCriterion("RESERVED_INT is null");
            return (Criteria) this;
        }

        public Criteria andReservedIntIsNotNull() {
            addCriterion("RESERVED_INT is not null");
            return (Criteria) this;
        }

        public Criteria andReservedIntEqualTo(Long value) {
            addCriterion("RESERVED_INT =", value, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntNotEqualTo(Long value) {
            addCriterion("RESERVED_INT <>", value, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntGreaterThan(Long value) {
            addCriterion("RESERVED_INT >", value, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntGreaterThanOrEqualTo(Long value) {
            addCriterion("RESERVED_INT >=", value, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntLessThan(Long value) {
            addCriterion("RESERVED_INT <", value, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntLessThanOrEqualTo(Long value) {
            addCriterion("RESERVED_INT <=", value, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntIn(List<Long> values) {
            addCriterion("RESERVED_INT in", values, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntNotIn(List<Long> values) {
            addCriterion("RESERVED_INT not in", values, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntBetween(Long value1, Long value2) {
            addCriterion("RESERVED_INT between", value1, value2, "reservedInt");
            return (Criteria) this;
        }

        public Criteria andReservedIntNotBetween(Long value1, Long value2) {
            addCriterion("RESERVED_INT not between", value1, value2, "reservedInt");
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