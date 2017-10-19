package com.blog.service.sys.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.bean.sys.SysRuleRole;
import com.blog.dao.bean.sys.SysUser;
import com.blog.dao.mapper.sys.SysRuleRoleMapper;
import com.blog.service.sys.api.ISysRole;
import com.blog.util.method.SimpleDateFormatUtils;
import com.blog.util.method.StringUtil;
import com.blog.util.pojo.PageVo;
import com.blog.util.web.CommonCode;

@Service
public class SysRoleImpl implements ISysRole{

	@Autowired
	private SysRuleRoleMapper sysRuleRoleMapper;
	
	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 后台用户列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @param state 是否启用0未启用1启用
	 * @return
	 */
	@Override
	public PageVo<SysRuleRole> getRoleList(Integer state,int limit, int offset,String startDate,String endDate) {
		if(state==null ||(state!=null&&state==-1)){
			state=null;
		}
		Date start = null;
		Date end = null;
		if(StringUtil.isNotEmpty(startDate)){
			start =SimpleDateFormatUtils.convert(startDate, false);
		}
		if(StringUtil.isNotEmpty(endDate)){
			end = SimpleDateFormatUtils.convertForce(endDate, true);
		}
		int count = sysRuleRoleMapper.getCount(start,end,state);//获取数量
		PageVo<SysRuleRole> page= new PageVo<SysRuleRole>(count,offset,limit);
		List<SysRuleRole> rows = sysRuleRoleMapper.getRoleByPage(page.getPageStartNumber(),page.getPageEndNumber(),start,end,state);//分页获取所有数据
		page.setRows(rows);
		return page;
	}

	//添加角色
	@Override
	public Integer addRole(String roleName, String roleDesc, SysUser user) {
		if(StringUtil.isEmpty(roleName)){
			return CommonCode.RoleResult.NROLENAME;
		}
		SysRuleRole role = new SysRuleRole();
		role.setCreateTime(new Date());
		role.setCreateUser(user.getId());
		role.setRoleDesc(roleDesc);
		role.setRoleName(roleName);
		int c = sysRuleRoleMapper.insertSelective(role);
		if(c>0){
			return CommonCode.RoleResult.SUCCESS;
		}
		return CommonCode.RoleResult.ERROR;
	}
	
	//修改角色
	@Override
	public Integer editRole(Long id, String roleName, String roleDesc, SysUser user) {
		if(id==null){
			return CommonCode.RoleResult.ERROR;
		}
		if(StringUtil.isEmpty(roleName)){
			return CommonCode.RoleResult.NROLENAME;
		}
		SysRuleRole role = new SysRuleRole();
		role.setId(id);
		role.setUpdateTime(new Date());
		role.setCreateUser(user.getId());
		role.setRoleDesc(roleDesc);
		role.setRoleName(roleName);
		int c = sysRuleRoleMapper.updateByPrimaryKeySelective(role);
		if(c>0){
			return CommonCode.RoleResult.SUCCESS;
		}
		return CommonCode.RoleResult.ERROR;
	}
	
	//根据ID查找角色
	@Override
	public SysRuleRole getRoleById(Long id) {
		return sysRuleRoleMapper.getRoleById(id);
	}
	
	//修改角色状态
	@Override
	public Integer editRoleState(Long id, String isTrue) {
		try {
			if(id==null || StringUtil.isEmpty(isTrue)){
				return CommonCode.RoleResult.ERRORPARMER;
			}
			Integer isEnble = Integer.valueOf(isTrue);
			SysRuleRole role = new SysRuleRole();
			role.setId(id);
			role.setIsEnable(isEnble);
			role.setUpdateTime(new Date());
			int c = sysRuleRoleMapper.updateByPrimaryKeySelective(role);
			if(c>0){
				return CommonCode.RoleResult.SUCCESS;
			}
			return CommonCode.RoleResult.ERROR;
		} catch (Exception e) {
			logger.info("修改角色状态失败:"+e.getMessage());
			return CommonCode.RoleResult.ERROR;
		}
	}

}
