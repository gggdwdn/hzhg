<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
	</head>
<body>
	<div id='fistpage-findEnergyCount' style='widht:100%;height:100%'></div>
	<script type="text/javascript">
	    jQuery(function($){
	    	seajs.use(['datatables', 'confirm', 'dialog','echarts'], function(A){
	    	//发电量请求
           	 var params = {};
	            params.start = 0;
	            params.draw = 1;
	            params.length=2147483647;
	            params.conditions = [];
		    	var energyCountArr = [];//发电量数组
		    	var energyDateArr = [];//日期数组
		    	var energyName = '';//公司名称
		    	 $.ajax({  
		                url: format_url("/engineStatistics/findEnergyCount"),
		                contentType: "application/json",
		                type: "post",  
		                "dataType": "JSON",
		                data : JSON.stringify(params),
		                async: false,  
		                success : function(result){ 
		                	$("#fistpage-findEnergyCount").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
		                	energyName = result.data.stationName;
		                	energyCountArr.push.apply(energyCountArr,result.data.energyCount);
		                	energyDateArr.push.apply(energyDateArr,result.data.energyDate);
		                }
		            });
		    	 getLosePoint =  new A.echarts({
		    		render: '#fistpage-findEnergyCount',
		    		options:{
		    			 title: {
		    			        text: energyName,
		    			        x:'center',
		    			        y:'5',
		    			        textAlign:'left',
		    			       	textStyle:{
		    			       		fontSize: '15',
		    			       	}
		    			    },
		    		    tooltip : {
		    		        trigger: 'axis',
		    		        show:false,
		    		        axisPointer: {
		    		            type: 'cross',
		    		            label: {
		    		                backgroundColor: '#6a7985'
		    		            }
		    		        }
		    		    },
		    		    grid: {
		    		        left: '3%',
		    		        right: '4%',
		    		        bottom: '3%',
		    		        height:'80%',
		    		        containLabel: true
		    		    },
		    		    itemStyle: {
		                    normal: {
		                        color: '#54a2d5'
		                    }
		                },
		    		    xAxis : [
		    		        {
		    		            type : 'category',
		    		            boundaryGap : false,
		    		            data : energyDateArr
		    		        }
		    		    ],
		    		    yAxis : [
		    		        {
		    		            type : 'value'
		    		        }
		    		    ],
		    		    series : [
		    		        {
		    		            name:'',
		    		            type:'line',
		    		            stack: '总量',
		    		            symbolSize:'9',
		    		            label: {
		    		                normal: {
		    		                    show: false,
		    		                    position: 'top'
		    		                }
		    		            },
		    		            areaStyle: {normal: {
		    		            	color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		    	                        offset: 0,
		    	                        color: '#34aaf3'
		    	                    }, {
		    	                        offset: 1,
		    	                        color: '#fff'
		    	                    }])
		    		            }},
		    		            data:energyCountArr
		    		        }
		    		    ]
		    		}
		    	}).render();
            });
	    });
	</script>
	</body>
</html>