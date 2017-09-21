package com.xmniao.domain.vstar;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：TVstarRewardRecord
 * 
 * 类描述： 新时尚大赛推荐奖励记录实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-7-27 上午9:55:13 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class TVstarRewardRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -683915102533876790L;

	private Long id;//业务主键

    private Integer playerId;//选手id,(t_vstar_player_info主键)

    private Integer playerUid;//主播UID

    private Integer referrerUid;//推荐人UID

    private BigDecimal receiveCoin;//获得鸟币

    private Date receiveTime;//获得时间

    private Integer receiveStatus;//领取状态，1领取成功，2领取失败

    private Integer readStatus;//阅读状态，1未读，2已读

    private Integer status;//有效状态，1有效，2无效。默认1

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerUid() {
        return playerUid;
    }

    public void setPlayerUid(Integer playerUid) {
        this.playerUid = playerUid;
    }

    public Integer getReferrerUid() {
        return referrerUid;
    }

    public void setReferrerUid(Integer referrerUid) {
        this.referrerUid = referrerUid;
    }

    public BigDecimal getReceiveCoin() {
        return receiveCoin;
    }

    public void setReceiveCoin(BigDecimal receiveCoin) {
        this.receiveCoin = receiveCoin;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TVstarRewardRecord [id=" + id + ", playerId=" + playerId
				+ ", playerUid=" + playerUid + ", referrerUid=" + referrerUid
				+ ", receiveCoin=" + receiveCoin + ", receiveTime="
				+ receiveTime + ", receiveStatus=" + receiveStatus
				+ ", readStatus=" + readStatus + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
    
    
}