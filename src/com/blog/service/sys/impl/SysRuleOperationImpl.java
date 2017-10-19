package com.blog.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.bean.sys.SysRuleOperation;
import com.blog.dao.bean.sys.SysRuleRole;
import com.blog.dao.bean.sys.SysUser;
import com.blog.dao.mapper.sys.SysRuleOperationMapper;
import com.blog.service.sys.api.ISysRuleOperation;
import com.blog.util.method.SimpleDateFormatUtils;
import com.blog.util.method.StringUtil;
import com.blog.util.pojo.MenuVo;
import com.blog.util.pojo.PageVo;

/**
 * 菜单
 */
@Service
public class SysRuleOperationImpl implements ISysRuleOperation {
	
	@Autowired
	private SysRuleOperationMapper sysRuleOperationMapper;
	
	public List<MenuVo> getRoleByMenu(SysUser user) {
		//根据用户据角色 查询对应的 权限。 这先查全部
		List<MenuVo> listF = sysRuleOperationMapper.getRoleByMenu(0,null);//获取父级权限
		List<MenuVo> listC = sysRuleOperationMapper.getRoleByMenu(null,0L);;//获取所有子权限
		for (MenuVo m : listF) {
			List<MenuVo> list = new ArrayList<MenuVo>();
			for(int i=0;i<listC.size();i++){
				Long id = m.getId();
				Long parentId = listC.get(i).getParentId();
				if(id!=null&&id.equals(parentId)){
					list.add(listC.get(i));
				}
			}
			m.setMenuList(list);
		}
		return listF;
	}

	/**
	 * 后台菜单列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @param isTrue 是否启用 0未启用1启用
	 * @return
	 */
	@Override
	public PageVo<SysRuleOperation> menuList(Integer limit, Integer offset, Integer isTrue, String startDate, String endDate) {
		if(isTrue==null ||(isTrue!=null&&isTrue==-1)){
			isTrue=null;
		}
		Date start = null;
		Date end = null;
		if(StringUtil.isNotEmpty(startDate)){
			start =SimpleDateFormatUtils.convert(startDate, false);
		}
		if(StringUtil.isNotEmpty(endDate)){
			end = SimpleDateFormatUtils.convertForce(endDate, true);
		}
		int count = sysRuleOperationMapper.getCount(start,end,isTrue);//获取数量
		PageVo<SysRuleOperation> page= new PageVo<SysRuleOperation>(count,offset,limit);
		//分页获取所有数据
		List<SysRuleOperation> rows = sysRuleOperationMapper.getRoleByPage(page.getPageStartNumber(),page.getPageEndNumber(),start,end,isTrue);
		page.setRows(rows);
		return page;
	}

	/**
	 * 根据ID查询菜单
	 * @param id
	 * @return
	 */
	@Override
	public SysRuleOperation getMenuId(Long id) {
		return sysRuleOperationMapper.selectByPrimaryKey(id);
	}

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
	@Override
	public Integer editMenuId(Long id, String menuName, String url, String desc, Integer isTrue, Long parendtId, Long topNum, String icon) {
		SysRuleOperation opt = new SysRuleOperation();
		opt.setId(id);
		opt.setUrl(url);
		opt.setOperName(menuName);
		opt.setUpdateTime(new Date());
		opt.setIsEnable(isTrue);
		opt.setOperDesc(desc);
		opt.setTopNum(topNum);
		opt.setIcon(icon);
		return sysRuleOperationMapper.updateByPrimaryKeySelective(opt);
	}

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
	@Override
	public Integer addMenu(String menuName, String url, String desc, Integer isTrue, Long parendtId, Long topNum, String icon) {
		SysRuleOperation opt = new SysRuleOperation();
		opt.setOperName(menuName);
		opt.setUrl(url);
		opt.setCreateTime(new Date());
		opt.setIsEnable(isTrue);
		opt.setOperDesc(desc);
		opt.setTopNum(topNum);
		opt.setIcon(icon);
		return sysRuleOperationMapper.insertSelective(opt);
	}

}
