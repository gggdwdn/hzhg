define(function(require, exports, module){
//	需要与（confirm模块）bootbox一起使用
	require("../../css/themes/{theme}/fullcalendar.min.css");
	require("../thirdparty/moment.min.js");
	require("../thirdparty/jquery-ui.custom.min.js");
	require("../thirdparty/fullcalendar.min.js");
	Aptech.calendar = function(config){
		this._config = {};
		if(!config.height){
			config.height=null;
		}
		this._config.events = config.events||[];
		this._config = config;
	};
	Aptech.calendar.prototype = {
		render:  function(){
			var self = this;
			if(self._config.ModulEvent){
				var getEventModel = 
					'<div class="widget-box transparent">\
						<div class="widget-body">\
							<div class="widget-main no-padding">\
								<div id="external-events">\
								</div>\
							</div>\
						</div>\
					</div>';
				var getEventBox = self._config.eventRender;
				$(getEventBox).append(getEventModel);
				var myEventArr = self._config.eventArr;
				for(var i=0;i<myEventArr.length;i++){
					var setEvent = 
						'<div class="external-event label-info" data-class="label-info">\
							<i class="ace-icon fa fa-arrows"></i>\
							'+myEventArr[i]+'\
						</div>';
					$('#external-events').append(setEvent);
				}
				var checkModel = 
				'<label>\
					<input type="checkbox" class="ace ace-checkbox" id="drop-remove" />\
					<span class="lbl">移动后删除事件</span>\
				</label>';
				$('#external-events').append(checkModel);
				$('#external-events div.external-event').each(function() {
					var eventObject = {
						title: $.trim($(this).text())
					};
					$(this).data('eventObject', eventObject);
					$(this).draggable({
						zIndex: 999,
						revert: true,    
						revertDuration: 0  
					});
				});
			}
			var _self = this;
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			if(self._config.events.length>0){
				for(var i=0;i<self._config.events.length;i++){
					self._config.events[i].allDay = true;
				}
			}
			var calendar = $(self._config.render).fullCalendar({
				buttonText: {
			        today: '今天',
			        month: '月视图',
			        week: '周视图',
			        day: '日视图'
			    },
			    titleFormat: {
			        month: 'YYYY年 MMMM月',
			        week: "YYYY年 MMMM 月D日",
			        day: 'YYYY年 MMMM月d日 dddd'
			    },
			    events:self._config.events,
			    disableResizing:true,
			    weekMode: "liquid",
			    monthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
			    dayNames: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
				buttonHtml: {
					prev: '<i class="ace-icon fa fa-chevron-left"></i>',
					next: '<i class="ace-icon fa fa-chevron-right"></i>'
				},
				columnFormat: {
			        month: 'dddd',
			        week: 'dddd M-d',
			        day: 'dddd M-d'
			    },
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'month,agendaWeek,agendaDay'
				},
				aspectRatio:self._config.aspectRatio,
				contentHeight: self._config.height,
				droppable: true, 
				editable:true,
				drop: function(date) { 
					var originalEventObject = $(this).data('eventObject');
					var $extraEventClass = $(this).attr('data-class');
					var copiedEventObject = $.extend({}, originalEventObject);
					copiedEventObject.start = date;
					copiedEventObject.allDay = false;
					if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];
					$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
					if ($('#drop-remove').is(':checked')) {
						$(this).remove();
					}
				},
				selectable: true,
				selectHelper: true,
				select: function(start, end, allDay) {
					var modal = 
						'<div class="modal fade" style="display: flex;align-items: center">\
						  <div class="modal-dialog">\
						   <div class="modal-content">\
							 <div class="modal-body">\
								<div class="modal-header" style="padding:5px 0 10px 0" id="headerModel">\
									<label style="font-size:15px;color:#438eb9;">创建新事件</label>\
										<select id="selectBox">\
											<option value="label-info">默认事件</option>\
											<option value="label-important">重要事件</option>\
											<option value="label-danger">紧急事件</option>\
										</select>\
								</div>\
								  <input  type="text" style="width:100%;margin-top:10px"  value=""/>\
							 </div>\
							 <div class="modal-footer">\
								<button type="button" class="btn btn-sm btn-success" data-action="submit" id="submit"><i class="ace-icon fa fa-check"></i>保存</button>\
								<button type="button" class="btn btn-sm" data-dismiss="modal"><i class="ace-icon fa fa-times"></i>取消</button>\
							 </div>\
						  </div>\
						 </div>\
						</div>';
					var modalEvent = $(modal).appendTo('body');
					modalEvent.find('button[data-action=submit]').on('click', function() {
						var getValue = $(this).parents().parents().children('div').children('input').val();
						var getSelectBox = $(this).parents().parents().children('div').children('div').children('#selectBox').val();
						if(getValue){
							//点击确定回调事件
							calendar.fullCalendar('renderEvent',
								{
									title: getValue,
									start: start,
									end: end,
									allDay: true,
									className: getSelectBox
								},true 
							);
							if(self._config.sureCallBack){
								self._config.sureCallBack(getValue,new Date(start),new Date(end),getSelectBox);
							}
						}
						modalEvent.modal("hide");
					});
					modalEvent.modal('show').on('hidden', function(){
						modalEvent.remove();
					});
					calendar.fullCalendar('unselect');
					
				},
				eventClick: function(calEvent, jsEvent, view) {
					var modal = 
					'<div class="modal fade" style="display: flex;align-items: center">\
					  <div class="modal-dialog">\
					   <div class="modal-content">\
						 <div class="modal-body">\
						   <button type="button" class="close" data-dismiss="modal" style="margin-top:-10px;">&times;</button>\
						   <form class="no-margin" id="calendarForm">\
							  <label>修改事件名称 &nbsp;</label>\
							  <input class="middle" style="width:330px" autocomplete="off" type="text" value="' + calEvent.title + '" />\
							 <button type="submit" class="btn btn-sm btn-success"><i class="ace-icon fa fa-check"style="border-width: 3px;"></i>保存</button>\
						   </form>\
						 </div>\
						 <div class="modal-footer">\
							<button type="button" class="btn btn-sm btn-danger" data-action="delete"><i class="ace-icon fa fa-trash-o"></i>删除</button>\
							<button type="button" class="btn btn-sm" data-dismiss="modal"><i class="ace-icon fa fa-times"></i>取消</button>\
						 </div>\
					  </div>\
					 </div>\
					</div>';
					var modal = $(modal).appendTo('body');
					modal.find('#calendarForm').on('submit', function(ev){
						ev.preventDefault();
						calEvent.title = $(this).find("input[type=text]").val();
						calendar.fullCalendar('updateEvent', calEvent);
						if(self._config.editBack){
							var title = calEvent.title;
							var start = calEvent.start["_i"];
							var end = calEvent.end["_i"];
							var className = calEvent.className[0];
							self._config.editBack(calEvent.id,title,start,end,className);
						}
						modal.modal("hide");
					});
					modal.find('button[data-action=delete]').on('click', function() {
						var getEventId = '';
						calendar.fullCalendar('removeEvents' , function(ev){
							getEventId = calEvent.id;
							return (ev._id == calEvent._id);
						})
						console.log(getEventId)
						if(self._config.deleteBack){
							self._config.deleteBack(getEventId);
						}
						modal.modal("hide");
					});
					modal.modal('show').on('hidden', function(){
						modal.remove();
					});
				},
				//清空数据
				clearDatas:function(){
					var _self = this;
					var getTable = $(_self._config.render);
					getTable.children('tbody').empty();
					getTable.parent().children('.pt5').empty();
					getTable.children('tbody').append('<tr class="odd"><td valign="top" colspan="9" class="dataTables_empty">没有数据</td></tr>');
				}
			});
		},
	};
	return Aptech;
})