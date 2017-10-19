package com.blog.dao.mapper.sys;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.blog.dao.bean.sys.SysRuleOperation;
import com.blog.util.pojo.MenuVo;

public interface SysRuleOperationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_operation
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_operation
     *
     * @mbggenerated
     */
    int insert(SysRuleOperation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_operation
     *
     * @mbggenerated
     */
    int insertSelective(SysRuleOperation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_operation
     *
     * @mbggenerated
     */
    SysRuleOperation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_operation
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SysRuleOperation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_rule_operation
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SysRuleOperation record);

    //获取所有一级权限
	List<SysRuleOperation> getOperByParent(@Param("parentID") Integer parentID);
	//获取所有子权限
	List<SysRuleOperation> getOperNotParent(@Param("parentID") Integer parentID);
	//获取所有权限
	List<SysRuleOperation> getOperAll();

	/**
	 * 
	 * @param i  有值查找父级菜单
	 * @param object 根据父级ID查找子级
	 * @return
	 */
	List<MenuVo> getRoleByMenu(@Param("father")Integer i,@Param("fatherId") Long object);

	//获取菜单数量
	int getCount(@Param("startDate")Date start,@Param("endDate") Date end,@Param("isTrue") Integer isTrue);
	//分页获取菜单数据
	List<SysRuleOperation> getRoleByPage(@Param("begin") int pageStartNumber,@Param("end")  int pageEndNumber,@Param("startDate")  Date start,
			@Param("endDate")  Date end,@Param("isTrue")  Integer isTrue);
}