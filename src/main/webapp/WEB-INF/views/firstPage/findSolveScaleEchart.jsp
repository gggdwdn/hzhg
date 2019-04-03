<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tag/Purview.tld" prefix="p"%> 
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/views/common/meta.jsp" %>
	</head>
<body>
	<div id='fistpage-findSolveScale' style='widht:100%;height:100%'></div>
	<script type="text/javascript">
	    jQuery(function($){
	    	seajs.use(['datatables', 'confirm', 'dialog','echarts'], function(A){
	    	//缺陷占比
           	var params = {};
           	var scaleArr = [];//缺陷占比数组
        	var params = {};
            params.start = 0;
            params.draw = 1;
            params.length=2147483647;
            params.conditions = [];
            innerArray = [];
	    	 $.ajax({  
	                url: format_url("/defectStatistics/findSolveScale"),
	                contentType: "application/json",
	                type: "post",  
	                "dataType": "JSON",
	                data : JSON.stringify(params),
	                async: false,  
	                success : function(result){ 
	                	$("#fistpage-findSolveScale").parents('.widgetContent').children('.loginClass').hide();//隐藏等待图标
	                	var indexArr = result.data;
	                	var x_arr = [];//x轴数组
	                	for(var i=0;i<indexArr.length;i++){
	                		x_arr.push(indexArr[i].name+'占比');
	                		var setObj = {};
	                		var setInnerObj = {};
	                		var setStr = indexArr[i].name+'占比';
	                		var setValue = indexArr[i].value;
	                		setObj.name = setStr;
	                		setObj.value = setValue;
	                		scaleArr.push(setObj);
	                		setInnerObj.name = '';
	                		setInnerObj.value = setValue;
	                		innerArray.push(setInnerObj);
	                	}
	                	var radius = ['35%','50%'];
	        	    	var getCompare = new A.echarts({
	        	    		render: '#fistpage-findSolveScale',
	        	    		options:{
	        	    	        tooltip: {
	        	    	            trigger: 'item',
	        	    	            formatter: function(params, ticket, callback) {
	        	    	                var res = params.seriesName;
	        	    	                res += '<br/>' + params.name + ' : ' + params.percent + '%';
	        	    	                return res;
	        	    	            }
	        	    	        },
	        	    	        
	        	    	        grid: {
	        	    	            bottom: "25%"
	        	    	        },
	        	    	        xAxis: [{
	        	    	            type: 'category',
	        	    	            axisLine: {
	        	    	                show: false
	        	    	            },
	        	    	            axisTick: {
	        	    	                show: false
	        	    	            },
	        	    	            axisLabel: {
	        	    	                interval: 0,
	        	    	                textStyle:{
	        	    	                	fontSize:'11',
	        	    	                }
	        	    	            }, 
	        	    	            data: x_arr
	        	    	        }],
	        	    	        yAxis: [{
	        	    	            show: false
	        	    	        }],
	        	    	        series: [{
	        	    	            name: scaleArr[0].name,
	        	    	            center: [
	        	    	                '20.0%',
	        	    	                '45%'
	        	    	            ],
	        	    	            radius: [
	        	    	                '25%',
	        	    	                '36%'
	        	    	            ],
	        	    	            type: 'pie',
	        	    	            labelLine: {
	        	    	                normal: {
	        	    	                    show: false
	        	    	                }
	        	    	            },
	        	    	            data: [{
	        	    	                value:scaleArr[0].value,
	        	    	                name: scaleArr[0].name,
	        	    	                itemStyle: {
	        	    	                    normal: {
	            	                        	color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
	        	    	                                offset: 0,
	        	    	                                color: 'rgba(20,205,233,1)'
	        	    	                            }, {
	        	    	                                offset: 1,
	        	    	                                color: 'rgba(20,205,233,0.2)'
	        	    	                            }]),
	        	    	                    } 
	        	    	                },
	        	    	                hoverAnimation: false,
	        	    	                label: {
	        	    	                    normal: {
	        	    	                        formatter: '{d} %',
	        	    	                        position: 'center',
	        	    	                        show: true,
	        	    	                        textStyle: {
	        	    	                            fontSize: '16',
	        	    	                            fontWeight: 'bold',
	        	    	                            color: '#000'
	        	    	                        }
	        	    	                    }
	        	    	                }
	        	    	            }, {
	        	    	                value: 100-scaleArr[0].value,
	        	    	                name: '',
	        	    	                tooltip: {
	        	    	                    show: false
	        	    	                },
	        	    	                itemStyle: {
	        	    	                    normal: {
	        	    	                    	color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
	            	                                offset: 0,
	            	                                color: 'rgba(20,205,233,0.2)'
	            	                            }, {
	            	                                offset: 1,
	            	                                color: '#FFF'
	            	                            }]),
	        	    	                    },
	        	    	                    emphasis: {
	        	    	                        color: '#aaa'
	        	    	                    }
	        	    	                },
	        	    	                hoverAnimation: false
	        	    	            }]
	        	    	        }, {
	        	    	            name: scaleArr[1].name,
	        	    	            center: [
	        	    	                '40.0%',
	        	    	                '45%'
	        	    	            ],
	        	    	            radius: [
	        	    	                     '25%',
	        	 	    	                '36%'
	        	    	            ],
	        	    	            type: 'pie',
	        	    	            labelLine: {
	        	    	                normal: {
	        	    	                    show: false
	        	    	                }
	        	    	            },
	        	    	            itemStyle: {
	            	                    normal: {
	            	                        	color: new echarts.graphic.LinearGradient(1, 0,0,0, [{
	            	                                offset: 0,
	            	                                color: 'rgba(240,177,25,1)'
	            	                            }, {
	            	                                offset: 1,
	            	                                color: 'rgba(240,177,25,0.2)'
	            	                            }]),
	            	                    } 
	            	                },
	            	                hoverAnimation: false,
	        	    	            data: [{
	        	    	                value: scaleArr[1].value,
	        	    	                name: scaleArr[1].name,
	        	    	                label: {
	        	    	                    normal: {
	        	    	                        formatter: '{d} %',
	        	    	                        position: 'center',
	        	    	                        show: true,
	        	    	                        textStyle: {
	        	    	                            fontSize: '16',
	        	    	                            fontWeight: 'bold',
	        	    	                            color: '#000'
	        	    	                        }
	        	    	                    }
	        	    	                }
	        	    	            }, {
	        	    	                value: 100-scaleArr[1].value,
	        	    	                name: '',
	        	    	                tooltip: {
	        	    	                    show: false
	        	    	                },
	        	    	                itemStyle: {
	        	    	                    normal: {
	        	    	                    	color: new echarts.graphic.LinearGradient(1, 0,0,0, [{
	            	                                offset: 0,
	            	                                color: '#fff'
	            	                            }, {
	            	                                offset: 1,
	            	                                color: 'rgba(240,177,25,0.2)'
	            	                            }]),
	        	    	                    },
	        	    	                    emphasis: {
	        	    	                        color: '#aaa'
	        	    	                    }
	        	    	                },
	        	    	                
	        	    	                hoverAnimation: false
	        	    	            }]
	        	    	        }, {
	        	    	            name: scaleArr[2].name,
	        	    	            center: [
	        	    	                '60.0%',
	        	    	                '45%'
	        	    	            ],
	        	    	            radius: [
	        	    	                     '25%',
	        	 	    	                '36%'
	        	    	            ],
	        	    	            type: 'pie',
	        	    	            labelLine: {
	        	    	                normal: {
	        	    	                    show: false
	        	    	                }
	        	    	            },
	        	    	            itemStyle: {
	            	                    normal: {
	            	                    	color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
	        	                                offset: 0,
	        	                                color: 'rgba(239,130,247,1)'
	        	                            }, {
	        	                                offset: 1,
	        	                                color: 'rgba(239,130,247,0.2)'
	        	                            }]),
	            	                    } 
	            	                },
	            	                hoverAnimation: false,
	        	    	            data: [{
	        	    	                value: scaleArr[2].value,
	        	    	                name: scaleArr[2].name,
	        	    	                label: {
	        	    	                    normal: {
	        	    	                        formatter: '{d} %',
	        	    	                        position: 'center',
	        	    	                        show: true,
	        	    	                        textStyle: {
	        	    	                            fontSize: '16',
	        	    	                            fontWeight: 'bold',
	        	    	                            color: '#000'
	        	    	                        }
	        	    	                    }
	        	    	                }
	        	    	            }, {
	        	    	                value: 100-scaleArr[2].value,
	        	    	                name: '',
	        	    	                tooltip: {
	        	    	                    show: false
	        	    	                },
	        	    	                itemStyle: {
	        	    	                    normal: {
	        	    	                    	color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
	        		                                offset: 0,
	        		                                color: '#fff'
	        		                            }, {
	        		                                offset: 1,
	        		                                color: 'rgba(239,130,247,0.2)'
	        		                            }]),
	        	    	                    },
	        	    	                    emphasis: {
	        	    	                        color: '#aaa'
	        	    	                    }
	        	    	                },
	        	    	                hoverAnimation: false
	        	    	            }]
	        	    	        }, {
	        	    	            name: scaleArr[3].name,
	        	    	            center: [
	        	    	                '80.0%',
	        	    	                '45%'
	        	    	            ],
	        	    	            radius: [
	            	                     '25%',
	         	    	                '36%'
	        	    	            ],
	        	    	            type: 'pie',
	        	    	            labelLine: {
	        	    	                normal: {
	        	    	                    show: false
	        	    	                }
	        	    	            },
	        	    	            itemStyle: {
	            	                    normal: {
	            	                    	color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
	        	                                offset: 0,
	        	                                color: 'rgba(188,244,42,1)'
	        	                            }, {
	        	                                offset: 1,
	        	                                color: 'rgba(188,244,42,0.2)'
	        	                            }]),
	            	                    } 
	            	                },
	            	                hoverAnimation: false,
	        	    	            data: [{
	        	    	                value: scaleArr[3].value,
	        	    	                name: scaleArr[3].name,
	        	    	                label: {
	        	    	                    normal: {
	        	    	                        formatter: '{d} %',
	        	    	                        position: 'center',
	        	    	                        show: true,
	        	    	                        textStyle: {
	        	    	                            fontSize: '16',
	        	    	                            fontWeight: 'bold',
	        	    	                            color: '#000'
	        	    	                        },
	        	    	                        
	        	    	                    }
	        	    	                }
	        	    	            }, {
	        	    	                value: 100-scaleArr[3].value,
	        	    	                name: '',
	        	    	                tooltip: {
	        	    	                    show: false
	        	    	                },
	        	    	                itemStyle: {
	        	    	                    normal: {
	        	    	                    	color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [{
	        		                                offset: 0,
	        		                                color: '#fff'
	        		                            }, {
	        		                                offset: 1,
	        		                                color: 'rgba(188,244,42,0.2)'
	        		                            }]),
	        	    	                    },
	        	    	                    emphasis: {
	        	    	                        color: '#aaa'
	        	    	                    }
	        	    	                },
	        	    	                hoverAnimation: false
	        	    	            }]
	        	    	        }]
	        	    		}
	        	    	}).render();
	                }
	            });
	    	 
            });
	    });
	</script>
	</body>
</html>