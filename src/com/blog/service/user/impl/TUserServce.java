package com.blog.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.bean.user.TUsers;
import com.blog.dao.mapper.user.TUsersMapper;
import com.blog.service.user.api.TUserServceApi;

@Service
public class TUserServce implements TUserServceApi {

	@Autowired
	private TUsersMapper tUsersMapper;
	
	public TUsers getAll() {
		return tUsersMapper.selectByPrimaryKey(1L);
	}

}
