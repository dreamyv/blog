package com.blog.service.sys.api;

import com.blog.dao.bean.sys.SysRuleRole;
import com.blog.dao.bean.sys.SysUser;
import com.blog.util.pojo.PageVo;

public interface ISysRole {


	/**
	 * 后台用户列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @param offset2 
	 * @return
	 */
	PageVo<SysRuleRole> getRoleList(Integer state,int limit, int offset, String startDate,String endDate);

	//添加角色
	Integer addRole(String roleName, String roleDesc, SysUser user);
	//修改角色
	Integer editRole(Long id, String roleName, String roleDesc, SysUser user);
	//根据ID查找角色
	SysRuleRole getRoleById(Long id);
	//修改角色状态
	Integer editRoleState(Long id, String isTrue);

}
