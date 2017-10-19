package com.blog.dao.mapper.log;

import com.blog.dao.bean.log.LogRequest;
import com.blog.dao.bean.log.LogRequestKey;
import com.blog.dao.bean.log.LogRequestWithBLOBs;

public interface LogRequestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_request
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(LogRequestKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_request
     *
     * @mbggenerated
     */
    int insert(LogRequestWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_request
     *
     * @mbggenerated
     */
    int insertSelective(LogRequestWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_request
     *
     * @mbggenerated
     */
    LogRequestWithBLOBs selectByPrimaryKey(LogRequestKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_request
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LogRequestWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_request
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(LogRequestWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_request
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LogRequest record);
}