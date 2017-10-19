package com.blog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.blog.dao.bean.music.TMusic;
import com.blog.dao.bean.music.TMusicType;
import com.blog.dao.bean.sys.SysUser;
import com.blog.service.music.api.IMusic;
import com.blog.util.pojo.PageVo;
import com.blog.util.web.UrlRegulation;

@Controller
@RequestMapping(UrlRegulation.SecurityPrefix.BACKGROUONT+UrlRegulation.RequestPrefix.REQ_LOGIN+UrlRegulation.BizPrefixPc.MUSIC)
public class MusicAction extends BaseAction {

	@Autowired
	private IMusic musciImpl;
	
	/**
	 * 到音乐界面
	 */
	@RequestMapping("/toMusic")
	public ModelAndView music(HttpServletRequest request, HttpServletResponse response) {
		return forword("/music/music", null);
	}
	
	/**
	 * 音乐列表
	 * @param name 音乐名称
	 * @param startDate
	 * @param endDate
	 * @param limit 一页数量
	 * @param offset 当前页码
	 * @return
	 */
	@RequestMapping("/musicList")
	@ResponseBody
	public PageVo<TMusic> musicList(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="pageSize",defaultValue="20")Integer limit,
			@RequestParam(value="pageNumber",defaultValue="1")Integer offset){
		PageVo<TMusic> list = musciImpl.musicList(name,limit,offset,startDate,endDate);
		return list;
	}
	
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
	@RequestMapping("/addOrEditMusic")
	@ResponseBody
	public Integer addOrEditMusic(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false)Integer id,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="auther",required=false)String auther,
			@RequestParam(value="album",required=false)String album,
			@RequestParam(value="lyric",required=false)String lyric,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="img",required=false)MultipartFile img,
			@RequestParam(value="music",required=false)MultipartFile music){
//		String path = request.getSession().getServletContext().getRealPath("/");;//获取服务器路径
		String path = "E:\\";
		return musciImpl.addOrEditMusic(id,name,auther,album,lyric,type,img,music,path);
	}
	
	/**
	 * 获取音乐信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/getMusicById")
	@ResponseBody
	public TMusic getMusicById(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false)Integer id){
		return musciImpl.getMusicById( id);
	}
	
	/**
	 * 设置音乐状态
	 * @param id
	 * @return
	 */
	@RequestMapping("/editMusicState")
	@ResponseBody
	public Boolean editMusicState(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="id",required=false)Integer id,
			@RequestParam(value="state",required=false,defaultValue="0")Integer state){
		return musciImpl.editMusicState(id,state);
	}
	
	/**
	 * 到音乐类型界面
	 */
	@RequestMapping("/toType")
	public ModelAndView toMusicType(HttpServletRequest request, HttpServletResponse response) {
		return forword("/music/musicType", null);
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
	@RequestMapping("/musicTypeList")
	@ResponseBody
	public PageVo<TMusicType> musicTypeList(HttpServletRequest request, HttpServletResponse response,HttpSession sessoin,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="startDate",required=false)String startDate,
			@RequestParam(value="endDate",required=false)String endDate,
			@RequestParam(value="pageSize",defaultValue="20")Integer limit,
			@RequestParam(value="pageNumber",defaultValue="1")Integer offset){
		PageVo<TMusicType> list = musciImpl.musicTypeList(name,limit,offset,startDate,endDate);
		return list;
	}
	

}
