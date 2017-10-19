package com.blog.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.bean.log.LogLogin;
import com.blog.dao.bean.sys.SysRuleOperation;
import com.blog.dao.bean.sys.SysRuleRole;
import com.blog.dao.bean.sys.SysRuleUserrole;
import com.blog.dao.bean.sys.SysUser;
import com.blog.dao.mapper.log.LogLoginMapper;
import com.blog.dao.mapper.sys.SysRuleOperationMapper;
import com.blog.dao.mapper.sys.SysRuleRoleMapper;
import com.blog.dao.mapper.sys.SysRuleUserroleMapper;
import com.blog.dao.mapper.sys.SysUserMapper;
import com.blog.service.sys.api.ISysUser;
import com.blog.util.ip.TmIpUtil;
import com.blog.util.method.KeyUtil;
import com.blog.util.method.SimpleDateFormatUtils;
import com.blog.util.method.StringUtil;
import com.blog.util.pojo.PageVo;
import com.blog.util.pojo.Ztree;
import com.blog.util.web.CommonCode;

@Service
public class SysUserImpl implements ISysUser{

	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SysUserMapper sysUserMapper;//用户
	@Autowired
	private SysRuleOperationMapper sysRuleOperationMapper;//功能
	@Autowired
	private SysRuleRoleMapper sysRuleRoleMapper;//角色
	@Autowired
	private SysRuleUserroleMapper sysRuleUserroleMapper;//用户对应角色
	
	@Autowired
	private LogLoginMapper logLoginMapper;
	
	@Override
	public Map<String,Object> login(String account, String pwd, String code,String sessionCode,String ip,Integer clientDevice,String ipAddress) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(sessionCode==null){
			map.put(CommonCode.LoginCode.USER_CODE,CommonCode.Login.CODE_INVALID);
			return map;
		}else if(!sessionCode.equals(code)){
			map.put(CommonCode.LoginCode.USER_CODE,CommonCode.Login.CODE_ERROR);
			return map;
		}
		SysUser user = sysUserMapper.findUser(account,pwd);
		if(user==null){
			map.put(CommonCode.LoginCode.USER_CODE,CommonCode.Login.ERROR);
			return map;
		}
		Integer del = user.getIsDelete();
		if(del!=null && del.equals(CommonCode.UserState.USER_DEL_YES)){
			map.put(CommonCode.LoginCode.USER_CODE,CommonCode.Login.USER_ERROR);
			return map;
		}
		Integer isTrue = user.getIsTrue();
		if(del != isTrue && isTrue.equals(CommonCode.UserState.USER_IS_TRUE)){
			map.put(CommonCode.LoginCode.USER_CODE,CommonCode.Login.USER_NO_lOGIN);
			return map;
		}
		map.put(CommonCode.LoginCode.USER_CODE,CommonCode.Login.SUCCESS);
		map.put(CommonCode.LoginCode.USER_BACKDE,user);
		/*保存登录日志*/
		saveLoginLog(ip,ipAddress,account,clientDevice,CommonCode.Log.OPER_BK,CommonCode.Log.RESULT_SUCCESS,null);
		return map;
	}

	
	@Override
	public List<Ztree> getFunction(String id) {
//		List<SysRuleOperation> listParent = sysRuleOperationMapper.getOperByParent(0);//获取所有一级权限
//		List<SysRuleOperation> listCharent = sysRuleOperationMapper.getOperNotParent(0);//获取所有子权限
		List<SysRuleOperation> listOper = sysRuleOperationMapper.getOperAll();//获取所有权限
		List<Ztree> listTree = new ArrayList<Ztree>();
		for (int i = 0; i < listOper.size(); i++) {
			SysRuleOperation operP = listOper.get(i);
			Ztree tree = new Ztree();
			tree.setId(operP.getId());
			tree.setName(operP.getOperName());
			tree.setpId(operP.getParentId());
			listTree.add(tree);
		}
		return listTree;
	}

	/**
	 * 后台用户列表
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	@Override
	public PageVo<SysUser> getUserList(int limit, int offset, Integer isTrue, String startDate, String endDate) {
		if(isTrue==null||isTrue<0)isTrue=null;
		Date start = null;
		Date end = null;
		if(StringUtil.isNotEmpty(startDate)){
			start =SimpleDateFormatUtils.convert(startDate, false);
		}
		if(StringUtil.isNotEmpty(endDate)){
			end = SimpleDateFormatUtils.convertForce(endDate, true);
		}
		int count = sysUserMapper.getCount(isTrue,start,end);//获取用户数量
		PageVo<SysUser> page= new PageVo<SysUser>(count,offset,limit);
		List<SysUser> rows = sysUserMapper.getUserByPage(page.getPageStartNumber(),page.getPageEndNumber(),isTrue,start,end);//分页获取所有用户
		page.setRows(rows);
		return page;
	}


	/**
	 * 添加后台用户
	 */
	@Override
	public Integer addUser(String account, String password, String realName, String position, String email, String age, String isTrue, String telephone, String img) {
		Integer code = 0;
		try {
			if(StringUtil.isEmpty(account)){
				return CommonCode.UserResult.NACCOUTN;
			}
			if(StringUtil.isEmpty(password)){
				return CommonCode.UserResult.NPWD;
			}
			SysUser userAccount = sysUserMapper.findUserByAccount(account);//账号名不允许重复
			if(userAccount!=null){
				return CommonCode.UserResult.NNACCOUTN;
			}
			SysUser user = new SysUser();
			user.setAccount(account);
			if(StringUtil.isNotEmpty(age)){
				user.setAge(Integer.valueOf(age));
			}
			user.setCreateTime(new Date());
			user.setEmail(email);
			user.setImg(img);
			user.setIsDelete(0);
			user.setIsTrue(Integer.valueOf(isTrue));
			user.setPassword(password);
			user.setPosition(position);
			user.setRealName(realName);
			user.setTelephone(telephone);
			code = sysUserMapper.insert(user);
			if(code>0){
				return CommonCode.UserResult.SUCCESS;
			}else{
				return CommonCode.UserResult.ERRORUSER;
			}
		} catch (Exception e) {
			logger.info("添加后台用户报错:"+e.getMessage());
			return CommonCode.UserResult.ERRORUSER;
		}
	}

	/*根据ID查找用户*/
	@Override
	public SysUser getUserById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	/*编辑用户*/
	@Override
	public Integer editUser(Long id, String account, String password, String realName, String position, String email, String age, String isTrue, String telephone, String img) {
		try {
			if(id==null){
				return CommonCode.UserResult.ERRORUSER;
			}
			SysUser user = new SysUser();
			user.setId(id);
			user.setAccount(account);
			user.setPassword(password);
			if(StringUtil.isNotEmpty(age)){
				user.setAge(Integer.valueOf(age));
			}
			user.setEmail(email);
			user.setImg(img);
			user.setIsTrue(Integer.valueOf(isTrue));
			user.setPosition(position);
			user.setRealName(realName);
			user.setTelephone(telephone);
			user.setUpdateTime(new Date());
			Integer c = sysUserMapper.updateByPrimaryKeySelective(user);
			if(c>0){
				return CommonCode.UserResult.SUCCESS;
			}
			return CommonCode.UserResult.ERRORUSER;
		} catch (Exception e) {
			logger.info("编辑后台用户报错:"+e.getMessage());
			return CommonCode.UserResult.ERRORUSER;
		}
		
	}

	//修改用户状态
	@Override
	public Integer editUserState(Long id, String isTrue) {
		try {
			if(id==null || StringUtil.isEmpty(isTrue)){
				return CommonCode.UserResult.ERRORUSER;
			}
			Integer isEnble = Integer.valueOf(isTrue);
			if(!isEnble.equals(CommonCode.UserState.USER_IS_FALSE)){
				isEnble = CommonCode.UserState.USER_IS_TRUE;//1允许登陆
			}
			SysUser user = new SysUser();
			user.setId(id);
			user.setIsTrue(isEnble);
			user.setUpdateTime(new Date());
			int c = sysUserMapper.updateByPrimaryKeySelective(user);
			if(c>0){
				return CommonCode.UserResult.SUCCESS;
			}
			return CommonCode.UserResult.ERRORUSER;
		} catch (Exception e) {
			logger.info("修改用户状态失败:"+e.getMessage());
			return CommonCode.UserResult.ERRORUSER;
		}
		
	}

	// 查找该用户下对应角色
	@Override
	public List<Ztree> getUserRole(Long id) {
		List<Ztree> listZtree = new ArrayList<Ztree>();
		List<SysRuleRole> list = sysRuleRoleMapper.getAll();//获取所有角色
		List<SysRuleUserrole> listUser = sysRuleUserroleMapper.getAllByUserID(id);//获取该用户所有角色
		for (SysRuleRole l : list) {
			Ztree tree = new Ztree();
			tree.setId(l.getId());
			tree.setName(l.getRoleName());
			tree.setpId(0L);//0:父节点
			for (SysRuleUserrole users : listUser) {
				Long roleId = users.getRoleId();
				if(roleId.equals(l.getId())){
					tree.setChecked(true);//选中
					break;
				}
			}
			listZtree.add(tree);
		}
		return listZtree;
	}
	//设置该用户角色
	@Override
	public Integer setUserRole(Long id, String roles) {
		try {
			if(id==null){
				return CommonCode.UserResult.ERRORUSER;
			}
			sysRuleUserroleMapper.deleteByUserId(id);//删除该用户所有角色
			if(StringUtil.isNotEmpty(roles)){
				String[] roleId = roles.split(",");
				for (String s : roleId) {
					SysRuleUserrole userRole = new SysRuleUserrole();
					userRole.setCreateTime(new Date());
					userRole.setRoleId(Long.valueOf(s));
					userRole.setUserId(id);
					sysRuleUserroleMapper.insertSelective(userRole);
				}
			}
			SysUser user = new SysUser();
			user.setId(id);
			user.setUpdateTime(new Date());
			Integer code = sysUserMapper.updateByPrimaryKeySelective(user);
			return CommonCode.UserResult.SUCCESS;
		} catch (Exception e) {
			logger.info("设置用户角色失败:"+e.getMessage());
			return CommonCode.UserResult.ERRORUSER;
		}
	}

	
	/**
	 * 保存登录日志
	 * @param ip 
	 * @param loginName 用户名
	 * @param clientDevice 设备1PC,2手机,3微信,4ios,5andoird,99未知
	 * @param oper 1前台2后台
	 * @param result 0=成功,1=失败
	 * @param resultContent 失败原因
	 * @return
	 */
	public Boolean saveLoginLog(String ip,String ipAddress,String loginName,int clientDevice,int oper,int result,String resultContent){
		LogLogin log = new LogLogin();
		log.setId(KeyUtil.generateDBKey());
		log.setClientDevice(clientDevice);
		log.setCreateTime(new Date());
		log.setIp(ip);
		log.setIpAddress(ipAddress);
		log.setLoginName(loginName);
		log.setOper(oper);
		log.setResult(result);
		log.setResultContent(resultContent);
		return logLoginMapper.insertSelective(log)>0?true:false;
	}
	
}
