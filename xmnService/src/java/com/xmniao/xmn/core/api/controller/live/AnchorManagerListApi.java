package com.xmniao.xmn.core.api.controller.live;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.PageRequest;
import com.xmniao.xmn.core.live.service.AnchorManagerService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：AnchorManagerListApi   
* 类描述：   获取主播管理员列表
* 创建人：yezhiyong   
* 创建时间：2016年10月19日 上午10:24:58   
* @version    
*
 */
@Controller
public class AnchorManagerListApi implements BaseVControlInf{

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(AnchorManagerListApi.class);
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 注入anchorManagerService
	 */
	@Autowired 
	private AnchorManagerService anchorManagerService;

	/**
	 * 
	* @Title: queryManagerList
	* @Description: 获取主播管理员列表
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value = "/live/anchor/anchorManagerList" ,method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public Object queryManagerList(PageRequest pageRequest){
		//日志
		log.info("pageRequest data:" + pageRequest.toString());
		List<ConstraintViolation> result = validator.validate(pageRequest);
		if (result!=null && result.size()>0) {
			log.info("数据有问题："+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
		}
		return versionControl(pageRequest.getApiversion(),pageRequest);
	}
	
	public Object versionOne(Object obj){
		PageRequest pageRequest = (PageRequest)obj;
		return anchorManagerService.queryManagerList(pageRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionOne(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}

}
