define(function(require, exports, module){
	require("../../css/themes/{theme}/tree/zTreeStyle/zTreeStyle.css");
	require("../thirdparty/ztree/jquery.ztree.all.js");
//	require("../thirdparty/ztree/jquery.ztree.exedit.js");
	Aptech.tree= function(config){
		var _options = {	
			view: {
				dblClickExpand: false,
				showLine: false,
				selectedMulti: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		this._config = config;
		$.extend(true, _options, config.options);
		this._options = _options;
	};
	
	Aptech.tree.prototype = {
			render: function(){
				_self = this;
				var containerNode = $(_self._config.render);
				var inputNode = $('<div id="treeDivId"><input  id="treeInputId"><button  id="treeSearchBtn" class="btn btn-sm btn-primary"><i class="ace-icon glyphicon  glyphicon-search" style="font-size:17px;top:0px"></i></button></div>');
				var dropDownDiv = $('<div class=""><ul id=' + _self._options.treeId +' class="ztree"></ul></div>');
				containerNode.append(inputNode);
				containerNode.append(dropDownDiv);
				var zTreeObj = _self._dropDownTree = $.fn.zTree.init($(_self._config.render + " .ztree"), _self._options, _self._config.datasource);
				var getTreeId = _self._options.treeId;
				var showNodes=[];   //用于存储查询到的结点
				$('#treeSearchBtn').on('click',function(){
					checkTree();
				})
				function checkTree(){
					//清空上一次筛选的节点
					if(showNodes.length>0){
						for(var i=0;i<showNodes.length;i++){
				    		zTreeObj.cancelSelectedNode(showNodes[i]); 
				    	}
					}
				    var keywords=$("#treeInputId").val(); 
				    if(!keywords){
				    	//如果为空清空所有选中状态
				    	zTreeObj.cancelSelectedNode();
				    	return;
				    }
				    var nodes = zTreeObj.getNodesByParamFuzzy("name", keywords, null);  
				    if (nodes.length>0) {
				    	showNodes = [];
				    	for(var i=0;i<nodes.length;i++){
				    		showNodes.push(nodes[i]);
				    		zTreeObj.selectNode(nodes[i],true); 
				    	}
				    } 
				}
				//绑定键盘按下事件  
				$(document).keyup(function(e) {  
				 // 回车键事件  
				    if(e.which == 13) {  
				    	checkTree();
				    }  
				});
				$('.modal-body').niceScroll({
					horizrailenabled:true,
				    cursorcolor: "#ccc",//#CC0071 光标颜色
				    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
				    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
				    cursorwidth: "5px", //像素光标的宽度
				    cursorborder: "0", // 游标边框css定义
				    
				    cursorborderradius: "5px",//以像素为光标边界半径
				    autohidemode: false //是否隐藏滚动条
				});
				$('.widget-body').niceScroll({
					horizrailenabled:true,
				    cursorcolor: "#ccc",//#CC0071 光标颜色
				    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
				    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
				    cursorwidth: "5px", //像素光标的宽度
				    cursorborder: "0", // 游标边框css定义
				    
				    cursorborderradius: "5px",//以像素为光标边界半径
				    autohidemode: false //是否隐藏滚动条
				});
//				setTimeout(function(){
//					$(".widget-body").getNiceScroll(0).doScrollTop(0,10);
//				},300)
				
				return _self;
			},
			
			setValue : function(value){
				var _self = this;
				if(value.length){
					for(var i=0; i<value.length; i++){
						var treeId = value[i];
						var selectNode = _self._dropDownTree.getNodeByParam("id", treeId, null);
						_self._dropDownTree.checkNode(selectNode, true, true, true);
					}
				}else{
					var selectNode = _self._dropDownTree.getNodeByParam("id", value, null);
					 _self._dropDownTree.selectNode(selectNode);
				}
			},
			
			getValue : function(){
				var ids ="";
				 var nodes = _self._dropDownTree.getCheckedNodes(true);
				 if(nodes){
					for (var i=0, l=nodes.length; i<l; i++) {
						if(!nodes[i].isParent){
							if(i == l-1){
								ids += nodes[i].id;
							}else{
								ids += nodes[i].id + ",";	
							}
						}
					}
				 }else{
					 nodes = _self._dropDownTree.getSelectedNodes();
					 if(nodes){
						 ids = nodes[0].id;
					 }
				 }
				return ids;
			},
			setParentValue : function(value){
				var _self = this;
				if(value.length){
					for(var i=0; i<value.length; i++){
						var treeId = value[i];
						var selectNode = _self._dropDownTree.getNodeByParam("id", treeId, null);
						if(selectNode){
							if(!selectNode.isParent){
								_self._dropDownTree.checkNode(selectNode, true, true, true);
							}
						}
					}
				}else{
					var selectNode = _self._dropDownTree.getNodeByParam("id", value, null);
					 _self._dropDownTree.selectNode(selectNode);
				}
			},
			getParentValue : function(){
				var ids ="";
				var nodes = _self._dropDownTree.getCheckedNodes(true);
				if(nodes){
					for (var i=0, l=nodes.length; i<l; i++) {
						if(i == l-1){
							ids += nodes[i].id;
						}else{
							ids += nodes[i].id + ",";	
						}
					}
				}else{
					nodes = _self._dropDownTree.getSelectedNodes();
					if(nodes){
						ids = nodes[0].id;
					}
				}
				return ids;
			},
			getPathValue : function(){
				var ids ="";
				var nodes = _self._dropDownTree.getCheckedNodes(true);
				 if(nodes){
					for (var i=0, l=nodes.length; i<l; i++) {
							if(i == l-1){
								ids += nodes[i].pathCode;
							}else{
								ids += nodes[i].pathCode + ",";	
							}
					}
				 }else{
					 var nodes = _self._dropDownTree.getSelectedNodes();
					 if(nodes){
						 ids = nodes[0].pathCode;
					 }
				 }
				return ids;
			}
	};
	return Aptech;
});
 