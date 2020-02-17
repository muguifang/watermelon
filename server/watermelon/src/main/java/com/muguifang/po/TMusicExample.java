package com.muguifang.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TMusicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TMusicExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMusicnameIsNull() {
            addCriterion("musicName is null");
            return (Criteria) this;
        }

        public Criteria andMusicnameIsNotNull() {
            addCriterion("musicName is not null");
            return (Criteria) this;
        }

        public Criteria andMusicnameEqualTo(String value) {
            addCriterion("musicName =", value, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameNotEqualTo(String value) {
            addCriterion("musicName <>", value, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameGreaterThan(String value) {
            addCriterion("musicName >", value, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameGreaterThanOrEqualTo(String value) {
            addCriterion("musicName >=", value, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameLessThan(String value) {
            addCriterion("musicName <", value, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameLessThanOrEqualTo(String value) {
            addCriterion("musicName <=", value, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameLike(String value) {
            addCriterion("musicName like", value, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameNotLike(String value) {
            addCriterion("musicName not like", value, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameIn(List<String> values) {
            addCriterion("musicName in", values, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameNotIn(List<String> values) {
            addCriterion("musicName not in", values, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameBetween(String value1, String value2) {
            addCriterion("musicName between", value1, value2, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicnameNotBetween(String value1, String value2) {
            addCriterion("musicName not between", value1, value2, "musicname");
            return (Criteria) this;
        }

        public Criteria andMusicphotoIsNull() {
            addCriterion("musicPhoto is null");
            return (Criteria) this;
        }

        public Criteria andMusicphotoIsNotNull() {
            addCriterion("musicPhoto is not null");
            return (Criteria) this;
        }

        public Criteria andMusicphotoEqualTo(String value) {
            addCriterion("musicPhoto =", value, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoNotEqualTo(String value) {
            addCriterion("musicPhoto <>", value, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoGreaterThan(String value) {
            addCriterion("musicPhoto >", value, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoGreaterThanOrEqualTo(String value) {
            addCriterion("musicPhoto >=", value, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoLessThan(String value) {
            addCriterion("musicPhoto <", value, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoLessThanOrEqualTo(String value) {
            addCriterion("musicPhoto <=", value, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoLike(String value) {
            addCriterion("musicPhoto like", value, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoNotLike(String value) {
            addCriterion("musicPhoto not like", value, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoIn(List<String> values) {
            addCriterion("musicPhoto in", values, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoNotIn(List<String> values) {
            addCriterion("musicPhoto not in", values, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoBetween(String value1, String value2) {
            addCriterion("musicPhoto between", value1, value2, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicphotoNotBetween(String value1, String value2) {
            addCriterion("musicPhoto not between", value1, value2, "musicphoto");
            return (Criteria) this;
        }

        public Criteria andMusicplayIsNull() {
            addCriterion("musicPlay is null");
            return (Criteria) this;
        }

        public Criteria andMusicplayIsNotNull() {
            addCriterion("musicPlay is not null");
            return (Criteria) this;
        }

        public Criteria andMusicplayEqualTo(String value) {
            addCriterion("musicPlay =", value, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayNotEqualTo(String value) {
            addCriterion("musicPlay <>", value, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayGreaterThan(String value) {
            addCriterion("musicPlay >", value, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayGreaterThanOrEqualTo(String value) {
            addCriterion("musicPlay >=", value, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayLessThan(String value) {
            addCriterion("musicPlay <", value, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayLessThanOrEqualTo(String value) {
            addCriterion("musicPlay <=", value, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayLike(String value) {
            addCriterion("musicPlay like", value, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayNotLike(String value) {
            addCriterion("musicPlay not like", value, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayIn(List<String> values) {
            addCriterion("musicPlay in", values, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayNotIn(List<String> values) {
            addCriterion("musicPlay not in", values, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayBetween(String value1, String value2) {
            addCriterion("musicPlay between", value1, value2, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMusicplayNotBetween(String value1, String value2) {
            addCriterion("musicPlay not between", value1, value2, "musicplay");
            return (Criteria) this;
        }

        public Criteria andMvplayIsNull() {
            addCriterion("mvPlay is null");
            return (Criteria) this;
        }

        public Criteria andMvplayIsNotNull() {
            addCriterion("mvPlay is not null");
            return (Criteria) this;
        }

        public Criteria andMvplayEqualTo(String value) {
            addCriterion("mvPlay =", value, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayNotEqualTo(String value) {
            addCriterion("mvPlay <>", value, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayGreaterThan(String value) {
            addCriterion("mvPlay >", value, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayGreaterThanOrEqualTo(String value) {
            addCriterion("mvPlay >=", value, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayLessThan(String value) {
            addCriterion("mvPlay <", value, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayLessThanOrEqualTo(String value) {
            addCriterion("mvPlay <=", value, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayLike(String value) {
            addCriterion("mvPlay like", value, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayNotLike(String value) {
            addCriterion("mvPlay not like", value, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayIn(List<String> values) {
            addCriterion("mvPlay in", values, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayNotIn(List<String> values) {
            addCriterion("mvPlay not in", values, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayBetween(String value1, String value2) {
            addCriterion("mvPlay between", value1, value2, "mvplay");
            return (Criteria) this;
        }

        public Criteria andMvplayNotBetween(String value1, String value2) {
            addCriterion("mvPlay not between", value1, value2, "mvplay");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNull() {
            addCriterion("recommend is null");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNotNull() {
            addCriterion("recommend is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendEqualTo(String value) {
            addCriterion("recommend =", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotEqualTo(String value) {
            addCriterion("recommend <>", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThan(String value) {
            addCriterion("recommend >", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThanOrEqualTo(String value) {
            addCriterion("recommend >=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThan(String value) {
            addCriterion("recommend <", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThanOrEqualTo(String value) {
            addCriterion("recommend <=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLike(String value) {
            addCriterion("recommend like", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotLike(String value) {
            addCriterion("recommend not like", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendIn(List<String> values) {
            addCriterion("recommend in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotIn(List<String> values) {
            addCriterion("recommend not in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendBetween(String value1, String value2) {
            addCriterion("recommend between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotBetween(String value1, String value2) {
            addCriterion("recommend not between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andPlaynumIsNull() {
            addCriterion("playNum is null");
            return (Criteria) this;
        }

        public Criteria andPlaynumIsNotNull() {
            addCriterion("playNum is not null");
            return (Criteria) this;
        }

        public Criteria andPlaynumEqualTo(Integer value) {
            addCriterion("playNum =", value, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumNotEqualTo(Integer value) {
            addCriterion("playNum <>", value, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumGreaterThan(Integer value) {
            addCriterion("playNum >", value, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumGreaterThanOrEqualTo(Integer value) {
            addCriterion("playNum >=", value, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumLessThan(Integer value) {
            addCriterion("playNum <", value, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumLessThanOrEqualTo(Integer value) {
            addCriterion("playNum <=", value, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumIn(List<Integer> values) {
            addCriterion("playNum in", values, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumNotIn(List<Integer> values) {
            addCriterion("playNum not in", values, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumBetween(Integer value1, Integer value2) {
            addCriterion("playNum between", value1, value2, "playnum");
            return (Criteria) this;
        }

        public Criteria andPlaynumNotBetween(Integer value1, Integer value2) {
            addCriterion("playNum not between", value1, value2, "playnum");
            return (Criteria) this;
        }

        public Criteria andIssuedateIsNull() {
            addCriterion("issueDate is null");
            return (Criteria) this;
        }

        public Criteria andIssuedateIsNotNull() {
            addCriterion("issueDate is not null");
            return (Criteria) this;
        }

        public Criteria andIssuedateEqualTo(Date value) {
            addCriterionForJDBCDate("issueDate =", value, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("issueDate <>", value, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateGreaterThan(Date value) {
            addCriterionForJDBCDate("issueDate >", value, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("issueDate >=", value, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateLessThan(Date value) {
            addCriterionForJDBCDate("issueDate <", value, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("issueDate <=", value, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateIn(List<Date> values) {
            addCriterionForJDBCDate("issueDate in", values, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("issueDate not in", values, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("issueDate between", value1, value2, "issuedate");
            return (Criteria) this;
        }

        public Criteria andIssuedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("issueDate not between", value1, value2, "issuedate");
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