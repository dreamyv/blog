package com.blog.service.music.api;

import org.springframework.web.multipart.MultipartFile;

import com.blog.dao.bean.music.TMusic;
import com.blog.dao.bean.music.TMusicType;
import com.blog.util.pojo.PageVo;

public interface IMusic {

	/**
	 * 音乐列表
	 * @param name 音乐名称
	 * @param startDate
	 * @param endDate
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	PageVo<TMusic> musicList(String name, Integer limit, Integer offset, String startDate, String endDate);

	/**
	 * 添加音乐
	 * @param name 音乐名称
	 * @param auther 歌手名称
	 * @param album 专辑名称
	 * @param lyric 歌词
	 * @param type 歌曲类型
	 * @param img  歌曲图片
	 * @param music 歌曲文件
	 * @return
	 */
	Integer addOrEditMusic(Integer id,String name, String auther, String album, String lyric, Integer type, MultipartFile img, MultipartFile music,String path);

	/**
	 * 获取音乐信息
	 * @param id
	 * @return
	 */
	TMusic getMusicById(Integer id);

	/**
	 * 设置音乐状态
	 * @param id
	 * @return
	 */
	Boolean editMusicState(Integer id, Integer state);

	/**
	 * 音乐类型列表
	 * @param name 音乐类型名称
	 * @param startDate
	 * @param endDate
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	PageVo<TMusicType> musicTypeList(String name, Integer limit, Integer offset, String startDate, String endDate);

}
