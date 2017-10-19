package com.blog.service.sys.api;

import java.util.List;

import com.blog.dao.bean.sys.SysRuleOperation;
import com.blog.dao.bean.sys.SysUser;
import com.blog.util.pojo.MenuVo;
import com.blog.util.pojo.PageVo;

//菜单
public interface ISysRuleOperation {

	List<MenuVo> getRoleByMenu(SysUser user);

	/**
	 * 后台菜单列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @param isTrue 是否启用 0未启用1启用
	 * @return
	 */
	PageVo<SysRuleOperation> menuList(Integer limit, Integer offset, Integer isTrue, String startDate, String endDate);

	/**
	 * 根据ID查询菜单
	 * @param id
	 * @return
	 */
	SysRuleOperation getMenuId(Long id);

	/**
	 * 修改菜单
	 * @param id
	 * @param menuName 菜单名称
	 * @param url url
	 * @param desc 描述
	 * @param isTrue 是否启用 0未启用1启用
	 * @param parendtId 父节点
	 * @param topNum 排序
	 * @param icon 字体图标
	 * @return
	 */
	Integer editMenuId(Long id, String menuName, String url, String desc, Integer isTrue, Long parendtId, Long topNum, String icon);

	
	/**
	 * 添加菜单
	 * @param menuName 菜单名称
	 * @param url url
	 * @param desc 描述
	 * @param isTrue 是否启用 0未启用1启用
	 * @param parendtId 父节点
	 * @param topNum 排序
	 * @param icon 字体图标
	 * @return
	 */
	Integer addMenu(String menuName, String url, String desc, Integer isTrue, Long parendtId, Long topNum, String icon);

}
