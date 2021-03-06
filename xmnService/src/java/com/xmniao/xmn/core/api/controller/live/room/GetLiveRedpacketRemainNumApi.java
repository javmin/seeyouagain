package com.xmniao.xmn.core.api.controller.live.room;

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
import com.xmniao.xmn.core.common.request.live.GetLiveRedpacketRequest;
import com.xmniao.xmn.core.live.service.LiveRoomService;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：GetLiveRedpacketRemainNumApi   
* 类描述：   获取直播间红包的剩余个数
* 创建人：yezhiyong   
* 创建时间：2016年12月24日 下午4:11:58   
* @version    
*
 */
@RequestMapping("/live")
@Controller
public class GetLiveRedpacketRemainNumApi implements BaseVControlInf {

	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(GetLiveRedpacketRemainNumApi.class);
	
	/**
	 * 注入直播间处理liveRoomService
	 */
	@Autowired
	private LiveRoomService liveRoomService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	/**
	 * 
	* @Title: getLiveRedpacket
	* @Description: 获取直播间红包的剩余个数
	* @return Object    返回类型
	* @throws
	 */
	@RequestMapping(value="/GetLiveRedpacketRemainNum",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Object queryLiveRedpacketRemainNum(GetLiveRedpacketRequest getLiveRedpacketRequest){
		//日志
		log.info("GetLiveRedpacketRemainNumApi getLiveRedpacketRequest Data:" + getLiveRedpacketRequest.toString());
		//验证
		List<ConstraintViolation> result = validator.validate(getLiveRedpacketRequest);
		if(result.size() >0 && result != null){
			log.info("提交的数据有问题"+result.get(0).getMessage());
			return new BaseResponse(ResponseCode.DATAERR,"提交的数据有问题");
		}
		return versionControl(getLiveRedpacketRequest.getApiversion(), getLiveRedpacketRequest);
	}
	
	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return versionControlOne(object);
			default :
				return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}
	
	private Object versionControlOne(Object object) {
		GetLiveRedpacketRequest getLiveRedpacketRequest = (GetLiveRedpacketRequest) object;
		return liveRoomService.queryLiveRedpacketRemainNum(getLiveRedpacketRequest);
	}

}
