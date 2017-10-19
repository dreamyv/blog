package com.blog.core.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


/**
 * aop 切面类
 * 日志记录
 */
@Aspect
public class LogAspect {

	private Logger log = Logger.getLogger(LogAspect.class);
	
	
	private static final String[] handlerName = new String[] { "org.springframework.web.servlet.HandlerInterceptor" };
	private static final String[] fileName = new String[] { "preHandle", "postHandle", "afterCompletion" };
	
	@Around(value = "execution(* com.blog.action.*.*(..))") // 环绕通知
	public Object doBasicProfiling(ProceedingJoinPoint point) throws Throwable {
		Object result = null;
		try {
			long start = System.currentTimeMillis();
			result = point.proceed();
			long end = System.currentTimeMillis();
			long time = (end - start);
			exinfo(point,result,time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public void exinfo(ProceedingJoinPoint point, Object result,long time) {
		boolean isNext = true;
		String methodName = point.getSignature().getName();//方法名称
		String packageName = point.getSignature().getDeclaringTypeName();//包名
		//如果是拦截器包名,就不运行业务方法
		for (String string : handlerName) {
			if (string.equals(packageName)) {
				isNext = false;
			}
		}
		//如果是拦截器方法,就不运行业务方法
		for (String string : fileName) {
			if (string.equals(methodName)) {
				isNext = false;
			}
		}
		if(isNext){
			//获取参数集合
			Object[] params = point.getArgs();
			StringBuilder builder = new StringBuilder("");
			HttpServletRequest request = null;
			if (params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					// 通过分析aop监听参数分析出request等信息
					if (params[i] instanceof HttpServletRequest) {
						request = (HttpServletRequest)params[i];
					}
					builder.append(String.valueOf(params[i]));
					if (i < params.length - 1) {
						builder.append(",");
					}
				}
			}
			// 执行该方法的对象
			Object classObj = point.getTarget();
			// 执行的类名称
			String className = classObj.getClass().getName();
			// 返回类型
			String returnType = null;
			if (result != null) {
				returnType = result.getClass().getName();
			}
			String ss = result != null ? result.toString() : "null";
			double times = time/1000;//秒
			log.info("【AOP拦截】【类名：" + className + "】【方法名：" + methodName + "】【返回类型：" + returnType + "】"+ "【包名：" + packageName + "】【用时：" + times + "秒】"+ "\n【参数:"+builder+"】");
			if(request!=null){
				//获取用户信息存入数据库
			}
		}
		
	}

}
