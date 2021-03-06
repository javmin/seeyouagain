package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;
import java.util.List;

import com.xmniao.xmn.core.base.BaseEntity;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarPlayerInfo
 * 
 * 类描述： 星食尚大赛选手信息表实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-2 下午3:57:27 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarPlayerInfo extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1795888521386816565L;

	private Integer id;//选手编号

    private Integer enrollId;//报名表ID
    
    private Integer liverId;//直播用户ID

    private Integer uid;//会员编号

    private String nname;//参赛名称

    private String phone;//参赛手机号

    private Integer provinceId;//报名省份

    private String provinceName;//省份名

    private Integer cityId;//报名城市

    private String cityName;//城市名
    
    private Integer areaId;//区域ID
    
    private String areaName;//区域名称
    
    private String areaText;//区域名称：广东-广州-天河

    private Integer divisionId;//所属赛区

    private String divisionName;//赛区名

    private Integer liveCount;//直播场次
    
    private Integer liveCountInit;//直播场次初始值
    
    private Integer vstarLiveCount;//大赛直播场次

    private Long liveTimeCount;//直播时长统计(分钟)
    
    private Long liveTimeCountInit;//直播时长统计初始值(分钟)
    
    private Long vstarLiveTime;//大赛直播时长(分钟)

    private Integer fansCount;//粉丝统计
    
    private Integer fansCountInit;//APP端用户被关注数
    
    private Integer fansNum;//粉丝合计数

    private Integer eggCount;//鸟蛋统计
    
    private Integer eggCountInit;//鸟蛋统计初始值
    
    private Integer rewardEgg;//大赛鸟蛋统计=eggCount-eggCountInit

    private Integer commentCount;//评论总数

    private Integer likeCount;//点赞总数

    private Integer sellerCount;//签店统计

    private Integer playerType;//选手类型 0.海选选手 1.复赛选手 2.总决赛选手

    private Date createTime;//报名时间
    
    private List<Integer> enrollIds;//报名记录ID集合
    
    private Integer rankNum;//排名序号
    
    private Integer status;//报名信息状态
    
    private String statusText;//参赛状态文本描述
    
    private Integer playStatus;//直播权限
    
    private Integer confining;//受限制的
    
    private Integer limitSize;//统计人数,如10,统计前10名
    
    private Integer orderParam;//排名条件
    
    private Integer referrerUid;//推荐人UID
    
    private String rName;//推荐人名称
    
    private String rPhone;//推荐人手机
    
    private String referrerInfo;//推荐人信息,亚瑟（15962356985）
    
    private Integer sellerNum;//签约商家
    
    private String startTime;//统计开始时间
    
    private String endTime;//统计结束时间
    
    private Integer countRewardEgg;//统计鸟蛋数
    
    private Integer countFansNum;//统计粉丝数
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(Integer enrollId) {
        this.enrollId = enrollId;
    }
    
    

    /**
	 * @return the liverId
	 */
	public Integer getLiverId() {
		return liverId;
	}

	/**
	 * @param liverId the liverId to set
	 */
	public void setLiverId(Integer liverId) {
		this.liverId = liverId;
	}

	public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    
    /**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	
	

	/**
	 * @return the areaText
	 */
	public String getAreaText() {
		return areaText;
	}

	/**
	 * @param areaText the areaText to set
	 */
	public void setAreaText(String areaText) {
		this.areaText = areaText;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName == null ? null : divisionName.trim();
    }

    public Integer getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(Integer liveCount) {
        this.liveCount = liveCount;
    }
    
    

    /**
	 * @return the liveCountInit
	 */
	public Integer getLiveCountInit() {
		return liveCountInit;
	}

	/**
	 * @param liveCountInit the liveCountInit to set
	 */
	public void setLiveCountInit(Integer liveCountInit) {
		this.liveCountInit = liveCountInit;
	}
	
	

	/**
	 * @return the vstarLiveCount
	 */
	public Integer getVstarLiveCount() {
		if(vstarLiveCount != null && vstarLiveCount.intValue()<0){
			return 0;
		}
		return vstarLiveCount;
	}

	/**
	 * @param vstarLiveCount the vstarLiveCount to set
	 */
	public void setVstarLiveCount(Integer vstarLiveCount) {
		this.vstarLiveCount = vstarLiveCount;
	}

	public Long getLiveTimeCount() {
        return liveTimeCount;
    }

    public void setLiveTimeCount(Long liveTimeCount) {
        this.liveTimeCount = liveTimeCount;
    }
    
    

    /**
	 * @return the liveTimeCountInit
	 */
	public Long getLiveTimeCountInit() {
		return liveTimeCountInit;
	}

	/**
	 * @param liveTimeCountInit the liveTimeCountInit to set
	 */
	public void setLiveTimeCountInit(Long liveTimeCountInit) {
		this.liveTimeCountInit = liveTimeCountInit;
	}
	
	

	/**
	 * @return the vstarLiveTime
	 */
	public Long getVstarLiveTime() {
		if(vstarLiveTime !=null &&  vstarLiveTime.intValue()<0){
			return 0l;
		}
		return vstarLiveTime;
	}

	/**
	 * @param vstarLiveTime the vstarLiveTime to set
	 */
	public void setVstarLiveTime(Long vstarLiveTime) {
		this.vstarLiveTime = vstarLiveTime;
	}

	public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }
    
    

    /**
	 * @return the fansCountInit
	 */
	public Integer getFansCountInit() {
		return fansCountInit;
	}

	/**
	 * @param fansCountInit the fansCountInit to set
	 */
	public void setFansCountInit(Integer fansCountInit) {
		this.fansCountInit = fansCountInit;
	}

	/**
	 * @return the fansNum
	 */
	public Integer getFansNum() {
		return fansNum;
	}

	/**
	 * @param fansNum the fansNum to set
	 */
	public void setFansNum(Integer fansNum) {
		this.fansNum = fansNum;
	}

	public Integer getEggCount() {
        return eggCount;
    }

    public void setEggCount(Integer eggCount) {
        this.eggCount = eggCount;
    }
    
    

    /**
	 * @return the eggCountInit
	 */
	public Integer getEggCountInit() {
		return eggCountInit;
	}

	/**
	 * @param eggCountInit the eggCountInit to set
	 */
	public void setEggCountInit(Integer eggCountInit) {
		this.eggCountInit = eggCountInit;
	}

	
	
	/**
	 * @return the rewardEgg
	 */
	public Integer getRewardEgg() {
		if(rewardEgg!=null && rewardEgg <0){
			return 0;
		}
		return rewardEgg;
	}

	/**
	 * @param rewardEgg the rewardEgg to set
	 */
	public void setRewardEgg(Integer rewardEgg) {
		this.rewardEgg = rewardEgg;
	}

	public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getSellerCount() {
        return sellerCount;
    }

    public void setSellerCount(Integer sellerCount) {
        this.sellerCount = sellerCount;
    }

    public Integer getPlayerType() {
        return playerType;
    }

    public void setPlayerType(Integer playerType) {
        this.playerType = playerType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	/**
	 * @return the enrollIds
	 */
	public List<Integer> getEnrollIds() {
		return enrollIds;
	}

	/**
	 * @param enrollIds the enrollIds to set
	 */
	public void setEnrollIds(List<Integer> enrollIds) {
		this.enrollIds = enrollIds;
	}
	
	

	/**
	 * @return the rankNum
	 */
	public Integer getRankNum() {
		return rankNum;
	}

	/**
	 * @param rankNum the rankNum to set
	 */
	public void setRankNum(Integer rankNum) {
		this.rankNum = rankNum;
	}
	
	

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
     * 参赛状态 1.已报名 2.报名审核通过  3.报名审核拒绝 4.实名认证待审核 5.实名认证通过 6.实名认证拒绝 7.试播审核通过 8.试播审核拒绝
	 * @return the statusText
	 */
	public String getStatusText() {
		if(status==null){
			return null;
		}
		
		switch (status) {
		case 1:
			statusText="已报名";
			break;
		case 2:
			statusText="报名审核通过";
			break;
		case 3:
			statusText="报名审核拒绝";
			break;
		case 4:
			statusText="实名认证待审核";
			break;
		case 5:
			statusText="实名认证通过";
			break;
		case 6:
			statusText="实名认证拒绝";
			break;
		case 7:
			statusText="试播审核通过";
			break;
		case 8:
			statusText="试播审核拒绝";
			break;
		default:
			statusText="已报名";
			break;
		}
		
		if(playStatus==null){
			return statusText;
		}
		
		switch (playStatus) {
		case 1:
			statusText="试播通过";
			break;
		default:
			break;
		}
		
		return statusText;
	}

	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
	

	/**
	 * @return the playStatus
	 */
	public Integer getPlayStatus() {
		return playStatus;
	}

	/**
	 * @param playStatus the playStatus to set
	 */
	public void setPlayStatus(Integer playStatus) {
		this.playStatus = playStatus;
	}
	
	

	/**
	 * @return the confining
	 */
	public Integer getConfining() {
		return confining;
	}

	/**
	 * @param confining the confining to set
	 */
	public void setConfining(Integer confining) {
		this.confining = confining;
	}
	
	

	/**
	 * @return the limitSize
	 */
	public Integer getLimitSize() {
		return limitSize;
	}

	/**
	 * @param limitSize the limitSize to set
	 */
	public void setLimitSize(Integer limitSize) {
		this.limitSize = limitSize;
	}
	
	

	/**
	 * @return the orderParam
	 */
	public Integer getOrderParam() {
		return orderParam;
	}

	/**
	 * @param orderParam the orderParam to set
	 */
	public void setOrderParam(Integer orderParam) {
		this.orderParam = orderParam;
	}
	
	
	

	/**
	 * @return the referrerUid
	 */
	public Integer getReferrerUid() {
		return referrerUid;
	}

	/**
	 * @param referrerUid the referrerUid to set
	 */
	public void setReferrerUid(Integer referrerUid) {
		this.referrerUid = referrerUid;
	}

	/**
	 * @return the rName
	 */
	public String getrName() {
		return rName;
	}

	/**
	 * @param rName the rName to set
	 */
	public void setrName(String rName) {
		this.rName = rName;
	}

	/**
	 * @return the rPhone
	 */
	public String getrPhone() {
		return rPhone;
	}

	/**
	 * @param rPhone the rPhone to set
	 */
	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}
	
	

	/**
	 * @return the referrerInfo
	 */
	public String getReferrerInfo() {
		StringBuffer sb=new StringBuffer();
		if(rName!=null){
			sb.append(rName);
		}
		
		if(rPhone !=null){
			sb.append("<br/>(").append(rPhone).append(")");
		}
		referrerInfo=sb.toString();
		
		return referrerInfo;
	}

	/**
	 * @param referrerInfo the referrerInfo to set
	 */
	public void setReferrerInfo(String referrerInfo) {
		this.referrerInfo = referrerInfo;
	}
	
	

	/**
	 * @return the sellerNum
	 */
	public Integer getSellerNum() {
		return sellerNum;
	}

	/**
	 * @param sellerNum the sellerNum to set
	 */
	public void setSellerNum(Integer sellerNum) {
		this.sellerNum = sellerNum;
	}
	
	

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

	/**
	 * @return the countRewardEgg
	 */
	public Integer getCountRewardEgg() {
		return countRewardEgg;
	}

	/**
	 * @param countRewardEgg the countRewardEgg to set
	 */
	public void setCountRewardEgg(Integer countRewardEgg) {
		this.countRewardEgg = countRewardEgg;
	}

	/**
	 * @return the countFansNum
	 */
	public Integer getCountFansNum() {
		return countFansNum;
	}

	/**
	 * @param countFansNum the countFansNum to set
	 */
	public void setCountFansNum(Integer countFansNum) {
		this.countFansNum = countFansNum;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVstarPlayerInfo [id=" + id + ", enrollId=" + enrollId
				+ ", liverId=" + liverId + ", uid=" + uid + ", nname=" + nname
				+ ", phone=" + phone + ", provinceId=" + provinceId
				+ ", provinceName=" + provinceName + ", cityId=" + cityId
				+ ", cityName=" + cityName + ", areaId=" + areaId
				+ ", areaName=" + areaName + ", areaText=" + areaText
				+ ", divisionId=" + divisionId + ", divisionName="
				+ divisionName + ", liveCount=" + liveCount
				+ ", liveCountInit=" + liveCountInit + ", vstarLiveCount="
				+ vstarLiveCount + ", liveTimeCount=" + liveTimeCount
				+ ", liveTimeCountInit=" + liveTimeCountInit
				+ ", vstarLiveTime=" + vstarLiveTime + ", fansCount="
				+ fansCount + ", fansCountInit=" + fansCountInit + ", fansNum="
				+ fansNum + ", eggCount=" + eggCount + ", eggCountInit="
				+ eggCountInit + ", rewardEgg=" + rewardEgg + ", commentCount="
				+ commentCount + ", likeCount=" + likeCount + ", sellerCount="
				+ sellerCount + ", playerType=" + playerType + ", createTime="
				+ createTime + ", enrollIds=" + enrollIds + ", rankNum="
				+ rankNum + ", status=" + status + ", statusText=" + statusText
				+ ", playStatus=" + playStatus + ", confining=" + confining
				+ ", limitSize=" + limitSize + ", orderParam=" + orderParam
				+ ", referrerUid=" + referrerUid + ", rName=" + rName
				+ ", rPhone=" + rPhone + ", referrerInfo=" + referrerInfo
				+ ", sellerNum=" + sellerNum + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", countRewardEgg=" + countRewardEgg
				+ ", countFansNum=" + countFansNum +"]";
	}
    
    
}