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
		    	var defectDateArr = [];//日期数组
		    	var defectSolveArr = [];//消缺数
		    	var defectArr = [];//缺陷数
		    	 $.ajax({  
		                url: format_url("/defectStatistics/findDefectScale"),
		                contentType: "application/json",
		                type: "post",  
		                "dataType": "JSON",
		                data : JSON.stringify(params),
		                async: false,  
		                success : function(result){ 
		                	$("#fistpage-findEnergyCount").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
		                	defectDateArr = result.data.defectDate;
		                	defectSolveArr = result.data.defectSolveCount;
		                	defectArr=result.data.defectCount;
		                }
		            });
		    	 getLosePoint =  new A.echarts({
		    		render: '#fistpage-findEnergyCount',
		    		options:{
		    			color: ['#12c578', '#ffbf2d'],
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
		    		    legend:{
		    		    	icon: 'rect',
		    		        itemWidth: 6,
		    		        itemHeight: 5,
		    		        itemGap: 13,
		    		        data: ['缺陷数', '消缺数'],
		    		        right: '4%',
		    		        textStyle: {
		    		            fontSize: 12,
		    		            color: '#999'
		    		        }
		    		    },
		    		    grid: {
		    		        left: '3%',
		    		        right: '4%',
		    		        bottom: '3%',
		    		        height:'80%',
		    		        width:'88%',
		    		        containLabel: true
		    		    },
		    		    itemStyle: {
		                    normal: {
		                    }
		                },
		    		    xAxis : [
		    		        {
		    		        	name:"月份",
		    		        	nameTextStyle:{
		    		                color:'#999',
		    		            },
		    		            type : 'category',
		    		            boundaryGap : false,
		    		            data : defectDateArr
		    		        }
		    		    ],
		    		    yAxis : [
		    		        {
		    		        	name:"数量",
		    		        	nameTextStyle:{
		    		                color:'#999',
		    		            },
		    		        	"splitLine": {
			    	                "show": false
			    	            }, 
		    		        	type : 'value'
		    		        }
		    		    ],
		    		    series : [
		    		        {
		    		            name:'缺陷数',
		    		            type:'line',
		    		            symbol:'circle',
		    		            symbolSize:'6',
		    		            label: {
		    		                normal: {
		    		                    show: false,
		    		                    position: 'top'
		    		                }
		    		            },
		    		            areaStyle: {normal: {
		    		            	color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		    	                        offset: 0,
		    	                        color: '#a8ffe9'
		    	                    }, {
		    	                        offset: 0.6,
		    	                        color: '#fff'
		    	                    },{
		    	                        offset: 1,
		    	                        color: '#fff'
		    	                    }])
		    		            }},
		    		            data:defectArr
		    		        },
		    		        {
		    		            name:'消缺数',
		    		            type:'line',
		    		            symbol:'circle',
		    		            symbolSize:'6',
		    		            label: {
		    		                normal: {
		    		                    show: false,
		    		                    position: 'top'
		    		                }
		    		            },
		    		            areaStyle: {normal: {
		    		            	color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		    	                        offset: 0,
		    	                        color: '#ffc12a'
		    	                    },{
		    	                        offset: 0.6,
		    	                        color: '#fff'
		    	                    },{
		    	                        offset: 1,
		    	                        color: '#fff'
		    	                    }])
		    		            }},
		    		            data:defectSolveArr
		    		        }
		    		    ]
		    		}
		    	}).render();
            });
	    });
	</script>
	</body>
</html>