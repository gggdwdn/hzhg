<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
	</head>
<body>
	<div id='findScale' style='widht:100%;height:100%'></div>
	<script type="text/javascript">
	    jQuery(function($){
	    	seajs.use(['datatables', 'confirm', 'dialog','echarts'], function(A){
	            	//消缺率请求
			        var solveArray = [];//百分比数组
			        var unitNameArray = [];//公司名称数组
			        var params = {};
		            params.start = 0;
		            params.draw = 1;
		            params.length=2147483647;
		            params.conditions = [];
			        $.ajax({  
		                url: format_url("/defectStatistics/findScale"),
		                contentType: "application/json",
		                type: "post",  
		                "dataType": "JSON",
		                data : JSON.stringify(params),
		                async: false,  
		                success : function(result){ 
		                	$("#findScale").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
		                	solveArray.push.apply(solveArray,result.data.solve);
		                	unitNameArray.push.apply(unitNameArray,result.data.unitName);
		                }
		            });  
			    	getPowerEcharts = new A.echarts({
			    		render: '#findScale',
			    		options:{
			    		    color: ['#3398DB'],
			    		    tooltip : {
			    		        trigger: 'axis',
			    		        show:false,
			    		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			    		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			    		        }
			    		    },
			    		    grid: {
			    		        left: '3%',
			    		        right: '6%',
			    		        bottom: '6%',
			    		        width:'88%',
			    		        height:'78%',
			    		        containLabel: true
			    		    },
			    		    xAxis : [
			    		        {
			    		            type : 'category',
			    		            name:"风场",
			    		        	nameTextStyle:{
			    		                color:'#999',
			    		            },
// 			    		            data :unitNameArray,
									data:['浑南风场','新民风场','阜新风场','康平风场','锦州风场','兴城风场'],
			    		            axisTick: {
			    		                alignWithLabel: true,
			    		            },
			    		            "axisTick": {
			    		                "show": false
			    		            }, 
				    		        "axisLabel": {
				    	                "rotate": 16, 
				    	                "margin":14,
				    	                "show": true, 
				    	                "textStyle": {
				    	                    "fontFamily": "微软雅黑", 
				    	                    "fontSize": 10,
				    	                    align: 'center',
				    	                }
				    	            }, 
			    		        }
			    		    ],
			    		    yAxis : [
			    		        {
			    		        	name:"百分比",
			    		        	nameTextStyle:{
			    		                color:'#999',
			    		            },
			    		            type : 'value',
			    		            "splitLine": {
				    	                "show": false
				    	            }, 
			    		        }
			    		       
			    		    ],
			    		    series : [
			    		        {
			    		            name:'',
			    		            type:'bar',
			    		            barWidth: '15%',
			    		            itemStyle: {
			    		                normal: {
			    		                	barBorderRadius:[2, 2, 0, 0],
			    		                	borderWidth:0,
			    		                	cursor:'point',
			    		                	borderColor: '#188df0',
			    		                    color: new echarts.graphic.LinearGradient(
			    		                        0, 0, 0, 1,
			    		                        [
			    		                            {offset: 0, color: '#40efe7'},
			    		                            {offset: 1, color: '#3C6BFF'}
			    		                        ]
			    		                    )
			    		                }
			    		            },
// 			    		            data:solveArray
									data:[50,55,32,80,56,78]		    		       
			    		            }
			    		    ]
			    		}
			    	}).render();
            });
	    });
	</script>
	</body>
</html>