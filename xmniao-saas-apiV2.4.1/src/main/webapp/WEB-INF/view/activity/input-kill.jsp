<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
    <meta charset="UTF-8">
    <title>创建秒杀活动</title>
<%@ include file="/common/taglibs.jsp"%>
    <meta name="renderer" content="webkit"> <meta name="fragment" content="!">
    <meta name="format-detection" content="telephone=no">
    <meta name="google" value="notranslate">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/css/normalize.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/swiper.css">
    <link rel="stylesheet" href="${ctx}/css/marketing.css"/>
    <link rel="stylesheet" href="${ctx}/js/pickadate.js-3.5.6/lib/themes/default.css">
    <link rel="stylesheet" href="${ctx}/css/component.css">
<link rel="stylesheet" href="${ctx}/js/pickadate.js-3.5.6/lib/themes/default.date.css">
</head>
<body class="padd-fill-tb bg-color-01">
<form id="formid" method="post">
	<c:forEach items="${kill.awardRelations}" var="awardRelation"
			varStatus="status">
			<input type="hidden" name="sellerCouponDetails[${status.index}].cid"
				value="${awardRelation.id}">
			<input type="hidden"
				name="sellerCouponDetails[${status.index}].sendNum"
				value="${awardRelation.amount}">
			<input type="hidden"
				name="sellerCouponDetails[${status.index}].couponType"
				value="${awardRelation.awardType}">
			<input type="hidden"
				name="sellerCouponDetails[${status.index}].couponName"
				value="${awardRelation.awardName}">
			<input type="hidden" name="sellerCouponDetails[${status.index}].couponEndDate" value="${awardRelation.couponEndDate}">
		</c:forEach>
	
 <section id="step1" style="display: none;">   
    <div class="container-wrap">
        <div class="activitysum-module">
            <div class="activitysum-wrap">
                <div class="activitysum-name">秒杀金额</div>
                <div class="activitysum-input"><span><input name="secKillAmount" id="secKillAmount" class="amount" value="${kill.secKillAmount}" type="text" placeholder="请填写5000元以内的金额" /></span></div>
            </div>
        </div>
        		<div class="activeity-date-module">
            <div class="activeity-dived">活动时间</div>
            <div class="activeity-date-con">
                <span class="aciveity-data-con aciveity-data-start">
                	<!-- <input  type="text" class="datepicker" id="activity-starttime" value="${currentDate}"   placeholder="选择日期" readonly/> -->
                	<a href="javascript:;" class="activity-time-select" id="activity-starttime" initTime="${initTime}">
                        <c:choose>
                            <c:when test="${empty kill.beginDate}">
                            <p>${currentDate}</p>
                            </c:when>
                            <c:when test="${empty currentDate}">
                            	<i>选择日期</i>
                            </c:when>
                            <c:otherwise>
	                        <p><fmt:formatDate value="${kill.beginDate}" pattern="MM月dd日" /></p>
                            </c:otherwise>
                        </c:choose>
                    </a>
                     <c:choose>
                            <c:when test="${empty kill.beginDate}">
                            <input type="hidden" name="beginDate" id="beginDate" value="${nowDateFont}"/>
                            </c:when>
                            <c:when test="${empty currentDate}">
                            	<input type="hidden" name="beginDate" id="beginDate" value="${nowDateFont}"/>
                            </c:when>
                            <c:otherwise>
	                        <input type="hidden" name="beginDate" id="beginDate" value="<fmt:formatDate value="${kill.beginDate}" pattern="yyyy-MM-dd" />"/>
                            </c:otherwise>
                        </c:choose>
                	<i class="icon-wrap icon-arrow-right"></i>
                </span>
                <span class="aciveity-data-desc">至</span>
                <span class="aciveity-data-con aciveity-data-end">
                	<!-- <input  type="text" class="datepicker" id="activity-endtime" placeholder="选择日期"  readonly/> -->
                	<a href="javascript:;" class="activity-time-select" id="activity-endtime">
                        <c:choose>
                        	<c:when test="${empty kill.endDate}">
		                        <i>选择日期</i> 
		                        <p></p>
                        	</c:when>
                        	<c:otherwise>
                        		<p><fmt:formatDate value="${kill.endDate}" pattern="MM月dd日" /></p>
                        	</c:otherwise>
                        </c:choose>
                    </a>
                     <input type="hidden" name="endDate" id="endDate" value="<fmt:formatDate value="${kill.endDate}" pattern="yyyy-MM-dd" />"/>
                	<i class="icon-wrap icon-arrow-right" ></i>
                </span>
            </div>
        </div>

        <div class="activeity-date-module">
			<div class="activeity-dived">领取时段</div>
			<div class="activeity-date-con">
				<span class="aciveity-data-con aciveity-data-start"> 
					<!-- <input type="text" placeholder="选择时间" readonly id="activity-selstartTime" value="00:00"  readonly/>  -->
					<a href="javascript:;" class="activity-time-select" id="activity-selstartTime" initTime="00:00">
					  <c:choose>
					  	<c:when test="${empty kill.beginTime}">
	                        <p>00:00</p>
					  	</c:when>
					  	<c:otherwise>
					  		<p><fmt:formatDate value="${kill.beginTime}" pattern="HH:mm" /></p>
					  	</c:otherwise>
					  </c:choose>                       
                    </a>
					<i class="icon-wrap icon-arrow-right"></i>
				</span> 
				<c:choose>
					  	<c:when test="${empty kill.beginTime}">
	                        <input type="hidden" name="beginTime" id="beginTime" value="00:00"/>
					  	</c:when>
					  	<c:otherwise>
					  		<input type="hidden" name="beginTime" id="beginTime" value="<fmt:formatDate value="${kill.beginTime}" pattern="HH:mm" />"/>
					  	</c:otherwise>
					  </c:choose>      
				<span class="aciveity-data-desc">至</span>
				<span class="aciveity-data-con aciveity-data-end"> 
					<!-- <input type="text" placeholder="选择时间" readonly id="activity-selendTime" value="23:59" readonly/>  -->
					<a href="javascript:;" class="activity-time-select" id="activity-selendTime" initTime="23:59">
                        <c:choose>
					  	<c:when test="${empty kill.endTime}">
	                        <p>23:59</p>
					  	</c:when>
					  	<c:otherwise>
					  		<p><fmt:formatDate value="${kill.endTime}" pattern="HH:mm" /></p>
					  	</c:otherwise>
					  </c:choose>    
                    </a>
					<i class="icon-wrap icon-arrow-right"></i>
					<c:choose>
					  	<c:when test="${empty kill.endTime}">
	                        <input type="hidden" name="endTime" id="endTime" value="23:59"/>
					  	</c:when>
					  	<c:otherwise>
					  		<input type="hidden" name="endTime" id="endTime" value="<fmt:formatDate value="${kill.endTime}" pattern="HH:mm" />"/>
					  	</c:otherwise>
					  </c:choose>      
				</span>
			</div>
		</div>
        <div class="ativety-data-name">
            <div class="activeity-dived">限时秒杀活动名称</div>
            <div class="activeity-input"><input type="text" name="name" value="${kill.name}" id="activityName" placeholder="例如：1元免费吃午餐" /></div>
        </div>
    </div>
    <div class="floor-module">
        <a class="floor-links links-disabled" id="step1submit" href="javascript:;">下一步</a>
    </div>
    <div id="container"></div>
</section>
<section id="step2" style="display: none;">
		<div class="container-wrap">
			<div class="fill-list-module">
				<div class="list-divhead">奖品设置</div>
				<div class="list-wrap">
					<div class="list-item in-icon-box">
						<i class="icon-wrap icon-arrow-right"></i> <span
							class="item-input-wrap"> <a id="list_award" href="javascript:;">已设置奖品为<font
								class="list-defind">${awaryCount==null?0:awaryCount}</font>件
						</a>
						</span><span class="item-name">设置奖品</span>
					</div>
				</div>
				<div id="item-content">
					<div class="list-wrap">
						<div class="list-item">
							<span class="item-input-wrap"><input id="prizeDrawNumber"
								type="text" placeholder="0" value="${kill.secKillAmount}"
								readonly="readonly" /></span><span class="item-name">秒杀金额</span>
						</div>
					</div>
				</div>
				<input type="hidden" name="limitNumber" id="limitNumber"
					value="${kill.limitNumber}" />
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">
							<div id="every-switch" class="sass-switch"
								style="margin-top: -4px; height: 5px"></div>
						</span> <span class="item-name">是否限制每人领取一次</span>
					</div>
				</div>
			</div>
			<p class="fill-list-desc-activity">*秒杀金额为所有奖品的抢购价格；</p>
			<p class="fill-list-desc-activity">*奖品已领取完毕后，将不会再有活动，会自动跳过；</p>
			<p class="fill-list-desc-activity">*奖品在活动期间有效。</p>
		</div>
		<div class="floor-module">
			<a id="step2submit" class="floor-links links-type2" href="javascript:;">提交浏览</a>
		</div>
</section>
	

	<section id="step3" style="display: none;">
		<div class="container-wrap">
			<div class="fill-list-module resetfill-list">
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3name">${kill.name}</span><span
							class="item-name">获得名称</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap">${awarySum!=null?awarySum:0}份</span><span
							class="item-name">奖品数量</span>
					</div>
				</div>
				<div class="list-wrap">
					<c:forEach items="${kill.awardRelations}" var="sellerCouponDetail"
						varStatus="status">
						<c:choose>
							<c:when test="${status.index==0}">
								<div class="list-item">
									<span class="item-input-wrap">${sellerCouponDetail.awardName}</span><span
										class="item-name">秒杀奖品</span>
								</div>
							</c:when>
							<c:otherwise>
								<span class="item-input-wrap">${sellerCouponDetail.awardName}</span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3limitNumber"><c:if
								test="${kill.limitNumber==1}">${kill.limitNumber}次</c:if> <c:if
								test="${kill.limitNumber==0}">无限制</c:if></span><span class="item-name">每人限领</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3secKillAmount">￥${kill.secKillAmount}</span><span
							class="item-name">秒杀价格</span>
					</div>
				</div>
				<div class="list-wrap">
					<div class="list-item">
						<span class="item-input-wrap" id="step3date"><fmt:formatDate
								value='${kill.beginDate}' type="date" dateStyle="medium" /> 至 <fmt:formatDate
								value='${kill.endDate}' type="date" dateStyle="medium" /></span><span
							class="item-name">活动时间</span>
					</div>
				</div>
				<p class="fill-list-desc-activity">*秒杀金额为所有奖品的抢购价格；</p>
			<p class="fill-list-desc-activity">*奖品已领取完毕后，将不会再有活动，会自动跳过；</p>
			<p class="fill-list-desc-activity">*奖品在活动期间有效。</p>
			</div>
			<div class="floor-module">
				<a class="floor-links links-type2" id="submitButton" href="javascript:;">提交</a>
			</div>
	</div>
	</section>
	</form>
	<input type="hidden" id="awaryCount" value="${awaryCount}">
</body>
<script src="${ctx}/js/jquery-1.8.0.min.js"></script>
<script src="${ctx}/js/component.js"></script>
<script src="${ctx}/js/activity/input-kill.js"></script>
<script type="text/javascript" src="${ctx}/js/activity/common.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>

<script type="text/javascript">	
	var path="${ctx}";
	var switchStatus = "${kill.limitNumber==1}" === "true";
	
	$("#limitNumber").val(switchStatus?1:0);
	var everySwitch = new sassSwitch({
	    ele: '#every-switch',
	    open: switchStatus
	});
	$("#every-switch").bind('switchchange',function(){
		switchStatus = everySwitch.getSwitchStatus();
		$("#limitNumber").val(switchStatus?1:0);
		if(switchStatus){
			$("#prizeDrawNumber").attr("readonly","readonly");
		}else{
			$("#prizeDrawNumber").removeAttr("readonly");
		}
	});
	var beginDate=new Date(Date.parse($("#beginDate").val().replace(/-/g, "/")));
$('#activity-starttime').attr("initTime",beginDate.getFullYear() + '年' + (parseInt(beginDate.getMonth())+parseInt(1)) + '月' + beginDate.getDate() + '日')
if(($("#endDate").val())){
	var endDate=new Date(Date.parse($("#endDate").val().replace(/-/g, "/")));
	$('#activity-endtime').attr("initTime",endDate.getFullYear() + '年' + (parseInt(endDate.getMonth())+parseInt(1))+ '月' + endDate.getDate() + '日')
}
	
</script>

</html>