package com.blog.core.schedele;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.blog.util.method.MySqlUtil;
import com.blog.util.method.TmDateUtil;

/**
 * 定时任务
 */
@Component
public class JobSchedele {
	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());
	
	public void execute(){
		logger.info("定时任务开始执行:"+TmDateUtil.getNowDayStr(new Date()));
		MySqlUtil.backup(); // 备份数据库
		logger.info("定时任务执行完毕:"+TmDateUtil.getNowDayStr(new Date()));
	}
}
