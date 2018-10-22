package com.suma.pojo;

import java.util.ArrayList;
import java.util.List;

public class AdvFlyWordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvFlyWordExample() {
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

        public Criteria andAdvInfoIdIsNull() {
            addCriterion("ADV_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdIsNotNull() {
            addCriterion("ADV_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdEqualTo(Long value) {
            addCriterion("ADV_INFO_ID =", value, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdNotEqualTo(Long value) {
            addCriterion("ADV_INFO_ID <>", value, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdGreaterThan(Long value) {
            addCriterion("ADV_INFO_ID >", value, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ADV_INFO_ID >=", value, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdLessThan(Long value) {
            addCriterion("ADV_INFO_ID <", value, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("ADV_INFO_ID <=", value, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdIn(List<Long> values) {
            addCriterion("ADV_INFO_ID in", values, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdNotIn(List<Long> values) {
            addCriterion("ADV_INFO_ID not in", values, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdBetween(Long value1, Long value2) {
            addCriterion("ADV_INFO_ID between", value1, value2, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andAdvInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("ADV_INFO_ID not between", value1, value2, "advInfoId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesIsNull() {
            addCriterion("DISPLAY_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesIsNotNull() {
            addCriterion("DISPLAY_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesEqualTo(Long value) {
            addCriterion("DISPLAY_TIMES =", value, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesNotEqualTo(Long value) {
            addCriterion("DISPLAY_TIMES <>", value, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesGreaterThan(Long value) {
            addCriterion("DISPLAY_TIMES >", value, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesGreaterThanOrEqualTo(Long value) {
            addCriterion("DISPLAY_TIMES >=", value, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesLessThan(Long value) {
            addCriterion("DISPLAY_TIMES <", value, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesLessThanOrEqualTo(Long value) {
            addCriterion("DISPLAY_TIMES <=", value, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesIn(List<Long> values) {
            addCriterion("DISPLAY_TIMES in", values, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesNotIn(List<Long> values) {
            addCriterion("DISPLAY_TIMES not in", values, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesBetween(Long value1, Long value2) {
            addCriterion("DISPLAY_TIMES between", value1, value2, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andDisplayTimesNotBetween(Long value1, Long value2) {
            addCriterion("DISPLAY_TIMES not between", value1, value2, "displayTimes");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeIsNull() {
            addCriterion("INTERVAL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeIsNotNull() {
            addCriterion("INTERVAL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeEqualTo(Long value) {
            addCriterion("INTERVAL_TIME =", value, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeNotEqualTo(Long value) {
            addCriterion("INTERVAL_TIME <>", value, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeGreaterThan(Long value) {
            addCriterion("INTERVAL_TIME >", value, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("INTERVAL_TIME >=", value, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeLessThan(Long value) {
            addCriterion("INTERVAL_TIME <", value, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeLessThanOrEqualTo(Long value) {
            addCriterion("INTERVAL_TIME <=", value, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeIn(List<Long> values) {
            addCriterion("INTERVAL_TIME in", values, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeNotIn(List<Long> values) {
            addCriterion("INTERVAL_TIME not in", values, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeBetween(Long value1, Long value2) {
            addCriterion("INTERVAL_TIME between", value1, value2, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andIntervalTimeNotBetween(Long value1, Long value2) {
            addCriterion("INTERVAL_TIME not between", value1, value2, "intervalTime");
            return (Criteria) this;
        }

        public Criteria andFontColourIsNull() {
            addCriterion("FONT_COLOUR is null");
            return (Criteria) this;
        }

        public Criteria andFontColourIsNotNull() {
            addCriterion("FONT_COLOUR is not null");
            return (Criteria) this;
        }

        public Criteria andFontColourEqualTo(String value) {
            addCriterion("FONT_COLOUR =", value, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourNotEqualTo(String value) {
            addCriterion("FONT_COLOUR <>", value, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourGreaterThan(String value) {
            addCriterion("FONT_COLOUR >", value, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourGreaterThanOrEqualTo(String value) {
            addCriterion("FONT_COLOUR >=", value, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourLessThan(String value) {
            addCriterion("FONT_COLOUR <", value, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourLessThanOrEqualTo(String value) {
            addCriterion("FONT_COLOUR <=", value, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourLike(String value) {
            addCriterion("FONT_COLOUR like", value, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourNotLike(String value) {
            addCriterion("FONT_COLOUR not like", value, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourIn(List<String> values) {
            addCriterion("FONT_COLOUR in", values, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourNotIn(List<String> values) {
            addCriterion("FONT_COLOUR not in", values, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourBetween(String value1, String value2) {
            addCriterion("FONT_COLOUR between", value1, value2, "fontColour");
            return (Criteria) this;
        }

        public Criteria andFontColourNotBetween(String value1, String value2) {
            addCriterion("FONT_COLOUR not between", value1, value2, "fontColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourIsNull() {
            addCriterion("BACKGROUND_COLOUR is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourIsNotNull() {
            addCriterion("BACKGROUND_COLOUR is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourEqualTo(String value) {
            addCriterion("BACKGROUND_COLOUR =", value, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourNotEqualTo(String value) {
            addCriterion("BACKGROUND_COLOUR <>", value, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourGreaterThan(String value) {
            addCriterion("BACKGROUND_COLOUR >", value, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourGreaterThanOrEqualTo(String value) {
            addCriterion("BACKGROUND_COLOUR >=", value, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourLessThan(String value) {
            addCriterion("BACKGROUND_COLOUR <", value, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourLessThanOrEqualTo(String value) {
            addCriterion("BACKGROUND_COLOUR <=", value, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourLike(String value) {
            addCriterion("BACKGROUND_COLOUR like", value, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourNotLike(String value) {
            addCriterion("BACKGROUND_COLOUR not like", value, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourIn(List<String> values) {
            addCriterion("BACKGROUND_COLOUR in", values, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourNotIn(List<String> values) {
            addCriterion("BACKGROUND_COLOUR not in", values, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourBetween(String value1, String value2) {
            addCriterion("BACKGROUND_COLOUR between", value1, value2, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andBackgroundColourNotBetween(String value1, String value2) {
            addCriterion("BACKGROUND_COLOUR not between", value1, value2, "backgroundColour");
            return (Criteria) this;
        }

        public Criteria andSpeedIsNull() {
            addCriterion("SPEED is null");
            return (Criteria) this;
        }

        public Criteria andSpeedIsNotNull() {
            addCriterion("SPEED is not null");
            return (Criteria) this;
        }

        public Criteria andSpeedEqualTo(Long value) {
            addCriterion("SPEED =", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotEqualTo(Long value) {
            addCriterion("SPEED <>", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedGreaterThan(Long value) {
            addCriterion("SPEED >", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedGreaterThanOrEqualTo(Long value) {
            addCriterion("SPEED >=", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedLessThan(Long value) {
            addCriterion("SPEED <", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedLessThanOrEqualTo(Long value) {
            addCriterion("SPEED <=", value, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedIn(List<Long> values) {
            addCriterion("SPEED in", values, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotIn(List<Long> values) {
            addCriterion("SPEED not in", values, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedBetween(Long value1, Long value2) {
            addCriterion("SPEED between", value1, value2, "speed");
            return (Criteria) this;
        }

        public Criteria andSpeedNotBetween(Long value1, Long value2) {
            addCriterion("SPEED not between", value1, value2, "speed");
            return (Criteria) this;
        }

        public Criteria andDirectIsNull() {
            addCriterion("DIRECT is null");
            return (Criteria) this;
        }

        public Criteria andDirectIsNotNull() {
            addCriterion("DIRECT is not null");
            return (Criteria) this;
        }

        public Criteria andDirectEqualTo(Long value) {
            addCriterion("DIRECT =", value, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectNotEqualTo(Long value) {
            addCriterion("DIRECT <>", value, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectGreaterThan(Long value) {
            addCriterion("DIRECT >", value, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectGreaterThanOrEqualTo(Long value) {
            addCriterion("DIRECT >=", value, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectLessThan(Long value) {
            addCriterion("DIRECT <", value, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectLessThanOrEqualTo(Long value) {
            addCriterion("DIRECT <=", value, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectIn(List<Long> values) {
            addCriterion("DIRECT in", values, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectNotIn(List<Long> values) {
            addCriterion("DIRECT not in", values, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectBetween(Long value1, Long value2) {
            addCriterion("DIRECT between", value1, value2, "direct");
            return (Criteria) this;
        }

        public Criteria andDirectNotBetween(Long value1, Long value2) {
            addCriterion("DIRECT not between", value1, value2, "direct");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("DURATION is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("DURATION is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Long value) {
            addCriterion("DURATION =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Long value) {
            addCriterion("DURATION <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Long value) {
            addCriterion("DURATION >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Long value) {
            addCriterion("DURATION >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Long value) {
            addCriterion("DURATION <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Long value) {
            addCriterion("DURATION <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Long> values) {
            addCriterion("DURATION in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Long> values) {
            addCriterion("DURATION not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Long value1, Long value2) {
            addCriterion("DURATION between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Long value1, Long value2) {
            addCriterion("DURATION not between", value1, value2, "duration");
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