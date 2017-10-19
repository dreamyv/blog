package com.blog.service.music.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.dao.bean.music.TMusic;
import com.blog.dao.bean.music.TMusicType;
import com.blog.dao.mapper.music.TMusicMapper;
import com.blog.dao.mapper.music.TMusicTypeMapper;
import com.blog.service.music.api.IMusic;
import com.blog.util.method.SimpleDateFormatUtils;
import com.blog.util.method.StringUtil;
import com.blog.util.method.TmFileUtil;
import com.blog.util.pojo.PageVo;
import com.blog.util.web.CommonCode;
import com.blog.util.web.Constant;

@Service
public class MusciImpl implements IMusic {

	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TMusicMapper tMusicMapper;
	
	@Autowired
	private TMusicTypeMapper tMusicTypeMapper;
	
	/**
	 * 音乐列表
	 * @param name 音乐名称
	 * @param startDate
	 * @param endDate
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	@Override
	public PageVo<TMusic> musicList(String name, Integer limit, Integer offset, String startDate, String endDate) {
		Date start = null;
		Date end = null;
		if(StringUtil.isNotEmpty(startDate)){
			start =SimpleDateFormatUtils.convert(startDate, false);
		}
		if(StringUtil.isNotEmpty(endDate)){
			end = SimpleDateFormatUtils.convertForce(endDate, true);
		}
		int count = tMusicMapper.getCount(name,start,end);//
		PageVo<TMusic> page= new PageVo<TMusic>(count,offset,limit);
		List<TMusic> rows = tMusicMapper.getDateByPage(name,page.getPageStartNumber(),page.getPageEndNumber(),start,end);//分页获取数据
		page.setRows(rows);
		return page;
	}

	/**
	 * 添加或修改音乐
	 * @param name 音乐名称
	 * @param auther 歌手名称
	 * @param album 专辑名称
	 * @param lyric 歌词
	 * @param type 歌曲类型
	 * @param img  歌曲图片
	 * @param music 歌曲文件
	 * @param path 绝对路径
	 * @return
	 */
	@Override
	public Integer addOrEditMusic(Integer id,String name, String auther, String album, String lyric, Integer type, MultipartFile img, MultipartFile music,String path) {
		String musicImg = null;
		String musicFile = null;
		TMusic m = new TMusic();
		m.setMusicName(name);
		m.setMusicAuther(auther);
		m.setMusicAlbum(album);
		m.setMusicLyric(lyric);
		Integer c=0;
		if(id==null){
			m.setClickAmount(0L);//点击量
			m.setCreateTime(new Date());
			musicImg = updot(img,path+Constant.MUSIC_PICTURE,null);
			musicFile = updot(music,path+Constant.MUSIC_FILE,null);
			m.setMusicImg(musicImg);
			m.setMusicSrc(musicFile);
			c = tMusicMapper.insertSelective(m);
		}else{
			m.setUpdateTime(new Date());
			TMusic t = tMusicMapper.selectByPrimaryKey(id);
			String imgSrc=null;
			String musicSrc=null;
			if(t!=null&&!StringUtil.isEmpty(t.getMusicImg())){
				imgSrc = t.getMusicImg();
			}
			if(t!=null&&!StringUtil.isEmpty(t.getMusicSrc())){
				musicSrc = t.getMusicSrc();
			}
			musicImg = updot(img,path+Constant.MUSIC_PICTURE,imgSrc);
			musicFile = updot(music,path+Constant.MUSIC_FILE,musicSrc);
			m.setMusicImg(musicImg);
			m.setMusicSrc(musicFile);
			m.setId(id);
			c = tMusicMapper.updateByPrimaryKeySelective(m);
		}
		if(c>0){
			return CommonCode.MusciResult.SUCCESS;
		}else{
			return CommonCode.MusciResult.ERROR;
		}
	}
	/**
	 * 文件上传
	 * @param file
	 * @param path 文件存放的文件夹路径
	 * @param oldFileName 有名称就不生成新名字
	 * @return
	 */
	public String updot(MultipartFile file,String path,String oldFileName){
		String newFileName = null;
		if(!file.isEmpty()){
			File parent = new File(path);//流
			if(!parent.exists())parent.mkdirs();//新建文件
			String oldName = file.getOriginalFilename();/*文件名称*/
			long size = file.getSize();
			String sizeString = TmFileUtil.countFileSize(size);//获取文件大小
			String ext = TmFileUtil.getExtNoPoint(oldName);//获取文件后缀
			if(oldFileName==null || oldFileName.isEmpty()){
				//为上传文件自动分配文件名称，避免重复
				newFileName = TmFileUtil.generateFileName(oldName, 10, "yyyyMMddHHmmss");
			}else{
				newFileName = oldFileName;
			}
			try {
				//写入文件
				file.transferTo(new File(parent, newFileName));
				logger.info("文件上传成功!文件名称:"+newFileName+" 文件大小:"+sizeString+" 文件保存路劲:"+parent.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("文件上传失败");
				return null;
			}
		}
		return newFileName;
	}


	/**
	 * 获取音乐信息
	 * @param id
	 * @return
	 */
	@Override
	public TMusic getMusicById(Integer id) {
		return tMusicMapper.selectByPrimaryKey(id);
	}

	/**
	 * 设置音乐状态
	 * @param id
	 * @return
	 */
	@Override
	public Boolean editMusicState(Integer id, Integer state) {
		TMusic m = new TMusic();
		m.setId(id);
		m.setIsState(state);
		Integer c= tMusicMapper.updateByPrimaryKeySelective(m);
		return c==0?false:true;
	}

	/**
	 * 音乐类型列表
	 * @param name 音乐类型名称
	 * @param startDate
	 * @param endDate
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	@Override
	public PageVo<TMusicType> musicTypeList(String name, Integer limit, Integer offset, String startDate, String endDate) {
		Date start = null;
		Date end = null;
		if(StringUtil.isNotEmpty(startDate)){
			start =SimpleDateFormatUtils.convert(startDate, false);
		}
		if(StringUtil.isNotEmpty(endDate)){
			end = SimpleDateFormatUtils.convertForce(endDate, true);
		}
		int count = tMusicTypeMapper.getCount(name,start,end);//
		PageVo<TMusicType> page= new PageVo<TMusicType>(count,offset,limit);
		List<TMusicType> rows = tMusicTypeMapper.getDateByPage(name,page.getPageStartNumber(),page.getPageEndNumber(),start,end);//分页获取数据
		page.setRows(rows);
		return page;
	}

}
