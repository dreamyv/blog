package com.blog.util.web;

/**
 * 向页面返回的code
 */
public class CommonCode {
	
	/**
	 * 登录 返回code
	 */
	public interface Login{
		public static final Integer SUCCESS = 200;//登录成功
		public final static Integer NACCOUTN = 301;//账号为空
		public final static Integer NPWD = 302;//密码为空
		public final static Integer CODE_ERROR = 500;// 验证码错误
		public final static Integer ERROR = 501;// 账户名或密码错误
		public final static Integer USER_ERROR = 502;// 用户状态已销户
		public final static Integer USER_NO_lOGIN = 503;//该用户不允许登录
		public final static Integer CODE_INVALID = 504;//登录验证码失效
		
		public static final Integer STATUS_AUDIT_WAIT=505;//用户审核中
		public static final Integer STATUS_NOTAUDIT=506;//该用户审核不通过
		public static final Integer STATUS_FROZEN=507;//该用户已冻结
		public static final Integer FREEZE_FAIL=508;//该用户对应经销商已冻结
	}
	public interface LoginCode{
		public static final String USER_BACKDE="user_backde";//后台登录Map键-用户对象
		public static final String USER_CODE="code";//后台登录Map键-code
	}
	/**
	 * 用户状态
	 */
	public interface UserState{
		public static final Integer USER_DEL_NO = 0;//未删除
		public static final Integer USER_DEL_YES = 1;//删除
		public static final Integer USER_IS_FALSE = 0;//0不允许登陆
		public static final Integer USER_IS_TRUE = 1;//1允许登陆
	}
	
	/**
	 * 用户
	 */
	public interface UserResult{
		public final static Integer SUCCESS = 200;//成功
		public final static Integer NACCOUTN = 301;//账号为空
		public final static Integer NPWD = 302;//密码为空
		public final static Integer NNACCOUTN = 401;//账号已存在
		public final static Integer ERRORUSER = 501;//账号添加失败
		public final static Integer ERROR = 500;//参数错误
	}
	
	/**
	 * 角色
	 */
	public interface RoleResult{
		public final static Integer SUCCESS = 200;//成功
		public final static Integer NROLENAME = 301;//角色为空
		public final static Integer ERROR = 501;//失败
		public final static Integer ERRORPARMER = 500;//参数错误
	}
	
	/**
	 * 音乐
	 */
	public interface MusciResult{
		public final static Integer SUCCESS = 200;//成功
		public final static Integer ERROR = 501;//失败
		public final static Integer ERRORPARMER = 500;//参数错误
	}
	
	/**
	 * 音乐
	 */
	public interface Log{
		public final static Integer SUCCESS = 200;//成功
		public final static Integer ERROR = 501;//失败
		public final static Integer ERRORPARMER = 500;//参数错误
		
		public final static Integer RESULT_SUCCESS = 0;/*成功*/
		public final static Integer RESULT_FAIL = 1;/*失败*/
		public final static Integer OPER_PC = 1;/*前台*/
		public final static Integer OPER_BK = 2;/*后台*/
	}
	
	
}
