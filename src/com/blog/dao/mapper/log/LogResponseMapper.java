package com.blog.dao.mapper.log;

import com.blog.dao.bean.log.LogResponse;

public interface LogResponseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_response
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_response
     *
     * @mbggenerated
     */
    int insert(LogResponse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_response
     *
     * @mbggenerated
     */
    int insertSelective(LogResponse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_response
     *
     * @mbggenerated
     */
    LogResponse selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_response
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LogResponse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_response
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(LogResponse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_response
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LogResponse record);
}