package com.blog.service.sys.api;

import java.util.List;
import java.util.Map;

import com.blog.dao.bean.sys.SysUser;
import com.blog.util.pojo.PageVo;
import com.blog.util.pojo.Ztree;

public interface ISysUser {

	Map<String,Object> login(String account, String pwd, String code,String sessionCode,String ip,Integer clientDevice,String ipAddress);

	List<Ztree> getFunction(String id);

	/**
	 * 后台用户列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @param endDate 
	 * @param startDate 
	 * @param isTrue 
	 * @return
	 */
	PageVo<SysUser> getUserList(int limit, int offset, Integer isTrue, String startDate, String endDate);

	/**
	 * 添加用户
	 * @param account
	 * @param password
	 * @param realName
	 * @param position
	 * @param email
	 * @param age
	 * @param isTrue
	 * @param telephone
	 * @param img
	 * @return
	 */
	Integer addUser(String account, String password, String realName, String position, String email, String age, String isTrue, String telephone, String img);
	/*根据ID查找用户*/
	SysUser getUserById(Long id);
	//编辑用户
	Integer editUser(Long id, String account, String password, String realName, String position, String email, String age, String isTrue, String telephone, String img);
	//修改用户状态
	Integer editUserState(Long id, String isTrue);
	// 查找该用户下对应角色
	List<Ztree> getUserRole(Long id);
	//设置该用户角色
	Integer setUserRole(Long id, String roles);

}
