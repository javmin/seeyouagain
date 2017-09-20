$(function() {
	inserTitle('> 新食尚大赛> <a href="vstarEnroll/chart/init.jhtml" target="right">报名统计</a>','enrollConChart',true);
	
	$.post("vstarEnroll/chart/init/getCityRank.jhtml",function(data,status){
		if(status=="success"){
			var texts=new Array();
			$.each(data,function(i,item){
				if(i<30){
					$("<tr><td>"+item.cityName+"</td><td>"+item.count+"</td></tr>").appendTo("#chart1");
				}
				texts[i]=new Array()
				texts[i][1]=item.cityName;
				texts[i][2]=item.count;
			});
		}
		$(document).Table('.table_body','re',20,3,['排名','城市','报名人数'],texts);
		$(document).PaginBar('page_navigation','table_body',20);
		$('#container').highcharts({
	        data: {
	            table: 'datatable'
	        },
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '报名地区统计'
	        },
	        yAxis: {
	            allowDecimals: false,
	            title: {
	                text: '个',
	                rotation: 0
	            }
	        },
	        tooltip: {
	            formatter: function () {
	                return '<b>' + this.series.name + '</b><br/>' +
	                    this.point.y + ' 个' + this.point.name.toLowerCase();
	            }
	        }
	    });
	});
	
	
	$.post("vstarEnroll/chart/init/getCityRankByDate.jhtml",function(data,status){
		if(status=="success"){
			var texts=new Array();
			$.each(data,function(i,item){
				$("<tr><td>"+item.rankDate+"</td><td>"+item.count+"</td></tr>").appendTo("#chart2");
				texts[i]=new Array()
				texts[i][0]=item.rankDate;
				texts[i][1]=item.count;
				texts[i][2]=item.defalt;
				texts[i][3]=item.success;
				texts[i][4]=item.activeCount;
			});
		}
		$(document).Table('.table_body2','re',20,5,['日期','报名人数','无效人数','有效人数','活跃人数'],texts);
		$(document).PaginBar('page_navigation2','table_body2',20);
		$('#container2').highcharts({
	        data: {
	            table: 'datatable2'
	        },
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '按时间统计报名人数'
	        },
	        yAxis: {
	            allowDecimals: false,
	            title: {
	                text: '个',
	                rotation: 0
	            }
	        },
	        tooltip: {
	            formatter: function () {
	                return '<b>' + this.series.name + '</b><br/>' +
	                    this.point.y + ' 个' + this.point.name.toLowerCase();
	            }
	        }
	    });
	});
	
	
	
	
	
	});