package com.suma.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MovieMaterialExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MovieMaterialExample() {
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

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andTimeLengthIsNull() {
            addCriterion("TIME_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andTimeLengthIsNotNull() {
            addCriterion("TIME_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andTimeLengthEqualTo(String value) {
            addCriterion("TIME_LENGTH =", value, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthNotEqualTo(String value) {
            addCriterion("TIME_LENGTH <>", value, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthGreaterThan(String value) {
            addCriterion("TIME_LENGTH >", value, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthGreaterThanOrEqualTo(String value) {
            addCriterion("TIME_LENGTH >=", value, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthLessThan(String value) {
            addCriterion("TIME_LENGTH <", value, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthLessThanOrEqualTo(String value) {
            addCriterion("TIME_LENGTH <=", value, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthLike(String value) {
            addCriterion("TIME_LENGTH like", value, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthNotLike(String value) {
            addCriterion("TIME_LENGTH not like", value, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthIn(List<String> values) {
            addCriterion("TIME_LENGTH in", values, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthNotIn(List<String> values) {
            addCriterion("TIME_LENGTH not in", values, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthBetween(String value1, String value2) {
            addCriterion("TIME_LENGTH between", value1, value2, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeLengthNotBetween(String value1, String value2) {
            addCriterion("TIME_LENGTH not between", value1, value2, "timeLength");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("TIME is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterionForJDBCTime("TIME =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterionForJDBCTime("TIME <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterionForJDBCTime("TIME >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("TIME >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterionForJDBCTime("TIME <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("TIME <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterionForJDBCTime("TIME in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterionForJDBCTime("TIME not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("TIME between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("TIME not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCTime("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCTime("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCTime("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCTime("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCTime("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCTime("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andTitleNameIsNull() {
            addCriterion("TITLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTitleNameIsNotNull() {
            addCriterion("TITLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTitleNameEqualTo(String value) {
            addCriterion("TITLE_NAME =", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotEqualTo(String value) {
            addCriterion("TITLE_NAME <>", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameGreaterThan(String value) {
            addCriterion("TITLE_NAME >", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_NAME >=", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLessThan(String value) {
            addCriterion("TITLE_NAME <", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLessThanOrEqualTo(String value) {
            addCriterion("TITLE_NAME <=", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLike(String value) {
            addCriterion("TITLE_NAME like", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotLike(String value) {
            addCriterion("TITLE_NAME not like", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameIn(List<String> values) {
            addCriterion("TITLE_NAME in", values, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotIn(List<String> values) {
            addCriterion("TITLE_NAME not in", values, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameBetween(String value1, String value2) {
            addCriterion("TITLE_NAME between", value1, value2, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotBetween(String value1, String value2) {
            addCriterion("TITLE_NAME not between", value1, value2, "titleName");
            return (Criteria) this;
        }

        public Criteria andPosterUrlIsNull() {
            addCriterion("POSTER_URL is null");
            return (Criteria) this;
        }

        public Criteria andPosterUrlIsNotNull() {
            addCriterion("POSTER_URL is not null");
            return (Criteria) this;
        }

        public Criteria andPosterUrlEqualTo(String value) {
            addCriterion("POSTER_URL =", value, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlNotEqualTo(String value) {
            addCriterion("POSTER_URL <>", value, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlGreaterThan(String value) {
            addCriterion("POSTER_URL >", value, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlGreaterThanOrEqualTo(String value) {
            addCriterion("POSTER_URL >=", value, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlLessThan(String value) {
            addCriterion("POSTER_URL <", value, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlLessThanOrEqualTo(String value) {
            addCriterion("POSTER_URL <=", value, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlLike(String value) {
            addCriterion("POSTER_URL like", value, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlNotLike(String value) {
            addCriterion("POSTER_URL not like", value, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlIn(List<String> values) {
            addCriterion("POSTER_URL in", values, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlNotIn(List<String> values) {
            addCriterion("POSTER_URL not in", values, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlBetween(String value1, String value2) {
            addCriterion("POSTER_URL between", value1, value2, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andPosterUrlNotBetween(String value1, String value2) {
            addCriterion("POSTER_URL not between", value1, value2, "posterUrl");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdIsNull() {
            addCriterion("EPISODE_ID is null");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdIsNotNull() {
            addCriterion("EPISODE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdEqualTo(String value) {
            addCriterion("EPISODE_ID =", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdNotEqualTo(String value) {
            addCriterion("EPISODE_ID <>", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdGreaterThan(String value) {
            addCriterion("EPISODE_ID >", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdGreaterThanOrEqualTo(String value) {
            addCriterion("EPISODE_ID >=", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdLessThan(String value) {
            addCriterion("EPISODE_ID <", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdLessThanOrEqualTo(String value) {
            addCriterion("EPISODE_ID <=", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdLike(String value) {
            addCriterion("EPISODE_ID like", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdNotLike(String value) {
            addCriterion("EPISODE_ID not like", value, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdIn(List<String> values) {
            addCriterion("EPISODE_ID in", values, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdNotIn(List<String> values) {
            addCriterion("EPISODE_ID not in", values, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdBetween(String value1, String value2) {
            addCriterion("EPISODE_ID between", value1, value2, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeIdNotBetween(String value1, String value2) {
            addCriterion("EPISODE_ID not between", value1, value2, "episodeId");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameIsNull() {
            addCriterion("EPISODE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameIsNotNull() {
            addCriterion("EPISODE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameEqualTo(String value) {
            addCriterion("EPISODE_NAME =", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameNotEqualTo(String value) {
            addCriterion("EPISODE_NAME <>", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameGreaterThan(String value) {
            addCriterion("EPISODE_NAME >", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("EPISODE_NAME >=", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameLessThan(String value) {
            addCriterion("EPISODE_NAME <", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameLessThanOrEqualTo(String value) {
            addCriterion("EPISODE_NAME <=", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameLike(String value) {
            addCriterion("EPISODE_NAME like", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameNotLike(String value) {
            addCriterion("EPISODE_NAME not like", value, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameIn(List<String> values) {
            addCriterion("EPISODE_NAME in", values, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameNotIn(List<String> values) {
            addCriterion("EPISODE_NAME not in", values, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameBetween(String value1, String value2) {
            addCriterion("EPISODE_NAME between", value1, value2, "episodeName");
            return (Criteria) this;
        }

        public Criteria andEpisodeNameNotBetween(String value1, String value2) {
            addCriterion("EPISODE_NAME not between", value1, value2, "episodeName");
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