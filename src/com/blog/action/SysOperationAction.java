package com.blog.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.blog.dao.bean.sys.SysRuleOperation;
import com.blog.dao.bean.sys.SysRuleRole;
import com.blog.dao.bean.sys.SysUser;
import com.blog.service.sys.api.ISysRole;
import com.blog.service.sys.api.ISysRuleOperation;
import com.blog.service.sys.api.ISysUser;
import com.blog.util.pojo.MenuVo;
import com.blog.util.pojo.PageVo;
import com.blog.util.pojo.Ztree;
import com.blog.util.web.SessionUtils;
import com.blog.util.web.UrlRegulation;

/**
 * 系统
 */
@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.BACKGROUONT+UrlRegulation.RequestPrefix.REQ_LOGIN+UrlRegulation.BizPrefixBackend.SYS)
public class SysOperationAction extends BaseAction {
	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISysUser sysUserImpl;
	
	@Autowired
	private ISysRole sysRoleImpl;
	
	@Autowired
	private OtherAction otherAction;
	
	@Autowired
	private ISysRuleOperation sysRuleOperationImpl;
	
	
	/**
	 * 到用户管理页面
	 * @return
	 */
	@RequestMapping("/toUser")
	public ModelAndView toUser(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin) {
		return new ModelAndView("/backend/sys/user");
	}
	
	/**
	 * 到角色管理页面
	 * @return
	 */
	@RequestMapping("/toRole")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin) {
		return new ModelAndView("/backend/sys/role");
	}
	
	/**
	 * 后台用户列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	@RequestMapping("userList")
	@ResponseBody
	public PageVo<SysUser> userList(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="isTrue",required=false)Integer isTrue,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="pageSize",defaultValue="20")Integer limit,
			@RequestParam(value="pageNumber",defaultValue="1")Integer offset){
		PageVo<SysUser> list = sysUserImpl.getUserList(limit,offset,isTrue,startDate,endDate);
		return list;
	}
	
	
	//添加用户
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="account",required=false) String account,
			@RequestParam(value="password",required=false) String password,
			@RequestParam(value="realName",required=false) String realName,
			@RequestParam(value="position",required=false) String position,
			@RequestParam(value="emails",required=false) String email,
			@RequestParam(value="age",required=false) String age,
			@RequestParam(value="isTrue",required=false) String isTrue,
			@RequestParam(value="phone",required=false) String telephone,
			@RequestParam(value="img",required=false) String img){
		Integer code = sysUserImpl.addUser( account, password, realName,position, email, age, isTrue, telephone, img);
		return code.toString();
	}
	//根据ID查找用户
	@RequestMapping("/getUserById")
	@ResponseBody
	public SysUser getUserById(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id){
		SysUser user = sysUserImpl.getUserById(id);
		return user;
	}
	//编辑用户
	@RequestMapping("/editUser")
	@ResponseBody
	public String editUser(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="account",required=false) String account,
			@RequestParam(value="password",required=false) String password,
			@RequestParam(value="realName",required=false) String realName,
			@RequestParam(value="position",required=false) String position,
			@RequestParam(value="emails",required=false) String email,
			@RequestParam(value="age",required=false) String age,
			@RequestParam(value="isTrue",required=false) String isTrue,
			@RequestParam(value="phone",required=false) String telephone,
			@RequestParam(value="img",required=false) String img){
		Integer code = sysUserImpl.editUser(id,account, password, realName,position, email, age, isTrue, telephone, img);
		return code.toString();
	}
	
	//修改用户状态
	@RequestMapping("/editUserState")
	@ResponseBody
	public String editUserState(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="isTrue",required=false) String isTrue){
		Integer code = sysUserImpl.editUserState(id, isTrue);
		return code.toString();
	}
	
	/**
	 * 查找该用户下对应角色
	 */
	@RequestMapping("/getUserRole")
	@ResponseBody
	public List<Ztree> getUserRole(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id){
		return sysUserImpl.getUserRole(id);
	}
	/**
	 * 设置该用户角色
	 */
	@RequestMapping("/setUserRole")
	@ResponseBody
	public Integer setUserRole(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="roles",required=false) String roles){
		return sysUserImpl.setUserRole(id,roles);
	}
	
	/*******************************************************角色**********************************************************************/
	
	/**
	 * 后台角色列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	@RequestMapping("roleList")
	@ResponseBody
	public PageVo<SysRuleRole> roleList(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="state",required=false)Integer state,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="pageSize",defaultValue="20")Integer limit,
			@RequestParam(value="pageNumber",defaultValue="1")Integer offset){
		PageVo<SysRuleRole> list = sysRoleImpl.getRoleList(state,limit,offset,startDate,endDate);
		return list;
	}
	
	
	//添加角色
	@RequestMapping("/addRole")
	@ResponseBody
	public String addRole(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="roleName",required=false) String roleName,
			@RequestParam(value="roleDesc",required=false) String roleDesc){
		SysUser user = SessionUtils.getUserBackend(request);
		Integer code = sysRoleImpl.addRole( roleName, roleDesc,user);
		return code.toString();
	}
	
	//修改角色
	@RequestMapping("/editRole")
	@ResponseBody
	public String eidtRole(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="roleName",required=false) String roleName,
			@RequestParam(value="roleDesc",required=false) String roleDesc){
		SysUser user = SessionUtils.getUserBackend(request);
		Integer code = sysRoleImpl.editRole(id,roleName, roleDesc,user);
		return code.toString();
	}
	
	//修改角色状态
	@RequestMapping("/editRoleState")
	@ResponseBody
	public String editRoleState(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="isTrue",required=false) String isTrue){
		Integer code = sysRoleImpl.editRoleState(id, isTrue);
		return code.toString();
	}
		
	//根据ID查找角色
	@RequestMapping("/getRoleById")
	@ResponseBody
	public SysRuleRole getRoleById(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id){
		SysRuleRole role = sysRoleImpl.getRoleById(id);
		return role;
	}
	
	//根据用户查找菜单
	@RequestMapping("/getRoleByMenu")
	@ResponseBody
	public List<MenuVo> getRoleByMenu(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin){
		SysUser user = SessionUtils.getUserBackend(request);
		return sysRuleOperationImpl.getRoleByMenu(user);
	}
	
	
	
	/**
	 * 根据ID获取当前用户权限
	 * @param id 用户ID
	 * @return
	 */
	@RequestMapping("/ztree")
	@ResponseBody
	public List<Ztree> ztree(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) String id) {
		List<Ztree> list = sysUserImpl.getFunction(id);
		return list;
	}
	
	/**********************************************************菜单**********************************************************************/
	
	
	/**
	 * 后台菜单列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @param isTrue 是否启用 0未启用1启用
	 * @return
	 */
	@RequestMapping("menuList")
	@ResponseBody
	public PageVo<SysRuleOperation> menuList(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="isTrue",required=false)Integer isTrue,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="pageSize",defaultValue="20")Integer limit,
			@RequestParam(value="pageNumber",defaultValue="1")Integer offset){
		PageVo<SysRuleOperation> list = sysRuleOperationImpl.menuList(limit,offset,isTrue,startDate,endDate);
		return list;
	}
	
	/**
	 * 到角色管理页面
	 * @return
	 */
	@RequestMapping("/toMenu")
	public ModelAndView toMenu(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin) {
		return new ModelAndView("/backend/sys/menu");
	}
	
	/**
	 * 根据ID查询菜单
	 * @param id
	 * @return
	 */
	@RequestMapping("/getMenuId")
	@ResponseBody
	public SysRuleOperation getMenuId(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false) Long id){
		SysRuleOperation menu = sysRuleOperationImpl.getMenuId(id);
		return menu;
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
	@RequestMapping("/editMenuId")
	@ResponseBody
	public Integer editMenuId(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id") Long id,
			@RequestParam(value="menuName",required=false) String menuName,
			@RequestParam(value="url",required=false) String url,
			@RequestParam(value="menuDesc",required=false) String desc,
			@RequestParam(value="isTrue",required=false) Integer isTrue,
			@RequestParam(value="parentId",required=false) Long parendtId,
			@RequestParam(value="topNum",required=false) Long topNum,
			@RequestParam(value="icon",required=false) String icon){
		return sysRuleOperationImpl.editMenuId(id,menuName,url,desc,isTrue,parendtId,topNum,icon);
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
	@RequestMapping("/addMenu")
	@ResponseBody
	public Integer addMenu(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="menuName",required=false) String menuName,
			@RequestParam(value="url",required=false) String url,
			@RequestParam(value="menuDesc",required=false) String desc,
			@RequestParam(value="isTrue",required=false) Integer isTrue,
			@RequestParam(value="parentId",required=false) Long parendtId,
			@RequestParam(value="topNum",required=false) Long topNum,
			@RequestParam(value="icon",required=false) String icon){
		return sysRuleOperationImpl.addMenu(menuName,url,desc,isTrue,parendtId,topNum,icon);
	}
	
	
}
