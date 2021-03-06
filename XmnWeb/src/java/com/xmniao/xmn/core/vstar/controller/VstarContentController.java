/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Plupload;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.service.CkeditorUpdateService;
import com.xmniao.xmn.core.util.FastFdsFile;
import com.xmniao.xmn.core.util.PluploadUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
import com.xmniao.xmn.core.vstar.constant.VstarConstant;
import com.xmniao.xmn.core.vstar.entity.TVstarContent;
import com.xmniao.xmn.core.vstar.entity.TVstarContentAttachment;
import com.xmniao.xmn.core.vstar.entity.TVstarContentImg;
import com.xmniao.xmn.core.vstar.entity.TVstarContentVideo;
import com.xmniao.xmn.core.vstar.entity.TVstarDict;
import com.xmniao.xmn.core.vstar.service.TVstarContentAttachmentService;
import com.xmniao.xmn.core.vstar.service.TVstarContentImgService;
import com.xmniao.xmn.core.vstar.service.TVstarContentService;
import com.xmniao.xmn.core.vstar.service.TVstarContentVideoService;
import com.xmniao.xmn.core.vstar.service.TVstarDictService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：VstarContentController
 * 
 * 类描述： 新时尚大赛V客学堂Controller
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2017-6-1 下午6:25:34
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "VstarContent/manage")
public class VstarContentController extends BaseController {

	public static final String FileDir = "uploadfile/";

	public static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private CkeditorUpdateService ckeditorUpdateService;

	@Autowired
	private TVstarDictService vstarDictService;

	@Autowired
	private TVstarContentAttachmentService vstarContentAttachmentService;

	@Autowired
	private TVstarContentVideoService contentVideoService;
	/**
	 * 注入V客学堂资料服务
	 */
	@Autowired
	private TVstarContentService vstarContentService;
	
	@Autowired
	private TVstarContentService contentService;
	
	/**
	 * 注入图片素材服务
	 */
	@Autowired
	private TVstarContentImgService vstarImgService;
	
	@RequestMapping(value = "/main/edit/init")
	public Object MainEditInit(Integer id) {
		ModelAndView modelAndView = new ModelAndView("vstar/contentMainEdit");
		if(id!=null){
			TVstarContent content =  contentService.getObject(id.longValue());
			content.setContentAttachments(vstarContentAttachmentService.getListByContentId(content.getId()));
			modelAndView.addObject("content", content);
		}
		modelAndView.addObject("dicts", vstarDictService.getList(new TVstarDict()));
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到V客学堂管理页面 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "vstar/vstarContentManage";
	}
	
	/**
	 * 
	 * 方法描述：加载V客学堂资料列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TVstarContent vstarContent) {
		Pageable<TVstarContent> pageable=new Pageable<TVstarContent>(vstarContent);
		Object json =null;
		try {
			List<TVstarContent> list = vstarContentService.getList(vstarContent);
			long count = vstarContentService.count(vstarContent);
			pageable.setContent(list);
			pageable.setTotal(count);
			json = JSON.toJSON(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 
	 * 方法描述：跳转到新增资料页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午3:19:38 <br/>
	 * @return
	 */
	@RequestMapping(value = "add/init")
	@RequestToken(createToken=true,tokenName="vstarContentToken")
	public String addInit(Model model,TVstarContent vstarContentReq){
		String viewName="";
		model.addAttribute("isType", "add");
		Integer contentType = vstarContentReq.getContentType();
		if(contentType.intValue()==VstarConstant.VSTAR_CONTENT_TYPE.TYPE_2){
			viewName = "vstar/vstarContentH5Edit";
		}else if(contentType.intValue()==VstarConstant.VSTAR_CONTENT_TYPE.TYPE_3){
			viewName = "vstar/vstarContentImgEdit";
		}
		return viewName;
	}
	
	/**
	 * 
	 * 方法描述：新增资料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-25上午10:41:35 <br/>
	 * @param material
	 * @return
	 */
	@RequestMapping(value="add")
	@RequestToken(removeToken=true,tokenName="vstarContentToken")
	@ResponseBody
	public Resultable add(TVstarContent vstarContent){
		Resultable result=new Resultable();
		try {
			vstarContentService.add(vstarContent);
			result.setSuccess(true);
			result.setMsg("操作成功!");
			JSON.toJSONString(result);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("操作失败!");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到批量新增图片页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午3:19:38 <br/>
	 * @return
	 */
	@RequestMapping(value = "add/vstarImageAddBatch/init")
	public String vstarImageAddBatchInit(Model model,TVstarContent vstarContentReq){
		return "vstar/vstarImageAddBatch";
	}
	
	/**
	 * 
	 * 方法描述：删除图片素材-图片 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-27上午11:49:34 <br/>
	 * @return
	 */
	@RequestMapping(value="update/deleteImgs")
	@ResponseBody
	public Object deleteImgs(@RequestParam("ids") String ids){
		Resultable result=new Resultable();
		try {
			if(StringUtils.isNotBlank(ids)){
				vstarImgService.delete(ids.split(","));
			}
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：批量添加图片 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-27下午3:29:18 <br/>
	 * @param id ,t_vstar_content 主键
	 * @param imgUrls 图片路径，多个以";"分隔
	 * @return
	 */
	@RequestMapping(value="add/addImageBatch")
	@ResponseBody
	public Object addImageBatch(@RequestParam(value="id",required=true) Long id,@RequestParam(value="imgUrls",required=true) String imgUrls){
		Resultable result=new Resultable();
		result=vstarImgService.addImgBatch(id,imgUrls);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：加载V客学堂图片素材图片列表 <br/>
	 * 创建人： huang'tao <br/>
	 * 创建时间：2016-9-28下午2:43:23 <br/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "init/vstarImglist")
	@ResponseBody
	public Object initVstarImglist(TVstarContent vstarContent) {
		Object json =null;
		try {
			Long id = vstarContent.getId();
			TVstarContentImg vstarImg= new TVstarContentImg();
			vstarImg.setContentId(id);
			vstarImg.setPage(vstarContent.getPage());
			vstarImg.setLimit(vstarContent.getLimit());
			Pageable<TVstarContentImg> pageable=new Pageable<TVstarContentImg>(vstarImg);
			List<TVstarContentImg> list = vstarImgService.getList(vstarImg);
			long count = vstarImgService.count(vstarImg);
			pageable.setContent(list);
			pageable.setTotal(count);
			json = JSON.toJSON(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到编辑V客学堂资料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:04 <br/>
	 * @param vstarContentReq
	 * @return
	 */
	@RequestMapping(value="update/init")
	public ModelAndView updateInit(TVstarContent vstarContentReq){
		ModelAndView modelAndView = new ModelAndView();
		Long id = vstarContentReq.getId();
		TVstarContent vstarContent = vstarContentService.getObject(id);
		modelAndView.addObject("vstarContent", vstarContent);
		Integer contentType = vstarContent.getContentType();
		if(contentType.intValue()==VstarConstant.VSTAR_CONTENT_TYPE.TYPE_2){//H5活动
			modelAndView.setViewName("vstar/vstarContentH5Edit");
		}else if(contentType.intValue()==VstarConstant.VSTAR_CONTENT_TYPE.TYPE_3){
			modelAndView.setViewName("vstar/vstarContentImgEdit");
		}else if(contentType.intValue()==VstarConstant.VSTAR_CONTENT_TYPE.TYPE_1){
			modelAndView.addObject("id", vstarContentReq.getId());
			modelAndView.setViewName("redirect:/VstarContent/manage/main/edit/init.jhtml");
		}
		return modelAndView;
	}
	
	
	/**
	 * 
	 * 方法描述：修改V客学堂资料 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10下午4:27:53 <br/>
	 * @param fansRank
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TVstarContent vstarContentReq){
		Resultable result=new Resultable();
		try {
			vstarContentReq.setUpdateTime(new Date());
			vstarContentService.update(vstarContentReq);
			result.setSuccess(true);
			result.setMsg("修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("修改失败!");
			this.log.error("修改精彩视频失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：批量更新商品上架状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateStatusBatch")
	@ResponseBody
	public Object updateStatusBatch(@RequestParam("ids") String ids,@RequestParam("status") String status){
		Resultable result=new Resultable();
		result=vstarContentService.updateStatusBatch(ids,status);
		return result;
	}
	
	/**
	 * 
	 * 方法描述：初始化教学分类下拉框
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param vstarDict
	 * @return
	 */
	@RequestMapping(value = "vstarDictIdInit",method=RequestMethod.POST)
	@ResponseBody
	public Object vstarDictIdInit(TVstarDict vstarDict) {
		Pageable<TVstarDict> pageable = new Pageable<TVstarDict>(vstarDict);
		List<TVstarDict> vstarDictList = vstarDictService.getList(vstarDict);
		pageable.setContent(vstarDictList);
		return pageable;
	}

	@RequestMapping(value = "main/edit/ckeditorUpload", method = { RequestMethod.POST })
	public void uploadFile3(@RequestParam("upload") MultipartFile filedata, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("文件上传Controller3");
		ckeditorUpdateService.ckeditorUpdate(filedata, request, response);
	}

	@RequestMapping(value = "fileUpload", method = { RequestMethod.POST })
	@ResponseBody
	public Object fileUpload(Plupload plupload, HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, MyException {
	
		plupload.setRequest(request);
		// 文件存储路径
		File dir = new File(plupload.getRequest().getSession().getServletContext().getRealPath("/") + FileDir);

		System.out.println(dir.getPath());

		try {
			// 上传文件
			File upload = PluploadUtil.upload(plupload, dir);
			// 判断文件是否上传成功（被分成块的文件是否全部上传完成）
			if (PluploadUtil.isUploadFinish(plupload)) {
				System.out.println(plupload.getName() + "----");
				try {
						String filename = plupload.getName();
					String extendStr = filename.substring(filename.lastIndexOf(".")+1);
					String[] filetype = { "pdf", "ppt", "zip", "rar", "doc", "xls" };
					boolean pass = false;
					for (String f : filetype) {
						if (extendStr.toLowerCase().equals(f)) {
							pass = true;
							break;
						}
					}
					if (!pass) {
						HashMap<String, String> resultMap = new HashMap<>();
						resultMap.put("result", "failed");
						resultMap.put("message", "只支持上传" + filetype.toString() + "格式的图片文件");
						return MAPPER.writeValueAsString(resultMap);
					}
					Map<String, String> upload2 = FastFdsFile.getInstance().upload(upload, false);
					TVstarContentAttachment tVstarContentAttachment = new TVstarContentAttachment();
					tVstarContentAttachment.setFileUrl(upload2.get("url"));
					tVstarContentAttachment.setFileName(plupload.getName());
					tVstarContentAttachment.setCreateTime(new Date());
					tVstarContentAttachment.setUpdateTime(new Date());
					tVstarContentAttachment.setFileType(extendStr);
					tVstarContentAttachment.setStatus(1);
					tVstarContentAttachment.setFileSize(upload.length());
					vstarContentAttachmentService.add(tVstarContentAttachment);
					HashMap<String, String> resultMap = new HashMap<>();
					resultMap.put("result", "ok");
					resultMap.put("id", tVstarContentAttachment.getId().toString());
					resultMap.put("url", upload2.get("url"));
					return resultMap;
				} catch (Exception e) {
					log.error("调用上传文件接口出现异常",e);
					throw new RuntimeException();
				}finally{
					upload.delete();
				}
			}
		} catch (Exception e) {
			log.error("调用上传文件接口出现异常",e);
			throw new RuntimeException();
		}

		return "login.upload";
	}
	
	
	@RequestMapping(value = "init/video")
	public Object videoInit(Integer id,Model model){
		if(id!=null){
			model.addAttribute("video", contentVideoService.getObject(id.longValue()));
		}
		return "vstar/contentVideoEdit";
	}
	
	
	@RequestMapping(value = "/main/edit/init/video")
	@ResponseBody
	public Object videoEdit(TVstarContentVideo contentVideo){
		contentVideo.setUpdateTime(new Date());
		if(contentVideo.getId()!=null){
			contentVideoService.update(contentVideo);
		}else{
			contentVideo.setCreateTime(new Date());
			contentVideoService.add(contentVideo);
		}
		return Resultable.success("操作成功", contentVideo);
	}
	
	@RequestMapping(value = "/main/edit/init/addMain")
	@ResponseBody
	public Object addMain(TVstarContent vstarContent){
		vstarContent.setUpdateTime(new Date());
		if(vstarContent.getId()!=null){
			contentService.updateMain(vstarContent);
		}else{
			vstarContent.setCreateTime(new Date());
			contentService.addMain(vstarContent);
		}
		return Resultable.success("操作成功");
	}
	

	@RequestMapping(value = "/main/edit/init/getvideosByIds")
	@ResponseBody
	public Object getvideosByIds(@RequestParam(required=true)String ids){
		List<TVstarContentVideo> list=contentVideoService.getByIds(Arrays.asList(ids.split(",")));
		return Resultable.success("操作成功", list);
	}
	
	@RequestMapping(value = "/main/edit/init/deleteVideo")
	@ResponseBody
	public Object deleteVideo(@RequestParam(required=true)Integer id){
		contentVideoService.deleteById(id);
		return Resultable.success();
	}
	
	@RequestMapping(value = "/main/edit/init/getContentEditMsg")
	@ResponseBody
	public Object getContentEditMsg(@RequestParam(required=true) Integer id){
		Map<String,List> content =  contentService.getContentEditMsg(id);
		return content;
	}
	
	
	@RequestMapping(value = "/fileUpload/deleteId")
	@ResponseBody
	public Object fileDelete(@RequestParam(required=true)Integer id){
		try {
			vstarContentAttachmentService.deleteByPrimaryKey(id.longValue());
			return Resultable.success();
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat();
		}
	}
	
}
