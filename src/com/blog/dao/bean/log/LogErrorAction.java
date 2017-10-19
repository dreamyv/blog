package com.blog.dao.bean.log;

import java.util.Date;

public class LogErrorAction {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_error_action.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_error_action.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_error_action.action
     *
     * @mbggenerated
     */
    private String action;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_error_action.ip
     *
     * @mbggenerated
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_error_action.ip_address
     *
     * @mbggenerated
     */
    private String ipAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_error_action.oper
     *
     * @mbggenerated
     */
    private Integer oper;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_error_action.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_error_action.notes
     *
     * @mbggenerated
     */
    private String notes;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_error_action.id
     *
     * @return the value of log_error_action.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_error_action.id
     *
     * @param id the value for log_error_action.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_error_action.user_id
     *
     * @return the value of log_error_action.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_error_action.user_id
     *
     * @param userId the value for log_error_action.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_error_action.action
     *
     * @return the value of log_error_action.action
     *
     * @mbggenerated
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_error_action.action
     *
     * @param action the value for log_error_action.action
     *
     * @mbggenerated
     */
    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_error_action.ip
     *
     * @return the value of log_error_action.ip
     *
     * @mbggenerated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_error_action.ip
     *
     * @param ip the value for log_error_action.ip
     *
     * @mbggenerated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_error_action.ip_address
     *
     * @return the value of log_error_action.ip_address
     *
     * @mbggenerated
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_error_action.ip_address
     *
     * @param ipAddress the value for log_error_action.ip_address
     *
     * @mbggenerated
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_error_action.oper
     *
     * @return the value of log_error_action.oper
     *
     * @mbggenerated
     */
    public Integer getOper() {
        return oper;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_error_action.oper
     *
     * @param oper the value for log_error_action.oper
     *
     * @mbggenerated
     */
    public void setOper(Integer oper) {
        this.oper = oper;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_error_action.create_time
     *
     * @return the value of log_error_action.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_error_action.create_time
     *
     * @param createTime the value for log_error_action.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_error_action.notes
     *
     * @return the value of log_error_action.notes
     *
     * @mbggenerated
     */
    public String getNotes() {
        return notes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_error_action.notes
     *
     * @param notes the value for log_error_action.notes
     *
     * @mbggenerated
     */
    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }
}