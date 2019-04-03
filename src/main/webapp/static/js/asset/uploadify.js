define(function(require, exports, module){
	
	Aptech.uploadify = function(config){
		var chargeSilgle = config.options.single?'修改':'新增';
		var _config = {
			name: "",
			multiple: false,
			allowBlank: false,
			initValue:"",
			url: "",
			type: "file",
			callback: null
		};
		var _options = {
			no_file:'无文件 ...',
			btn_choose:'浏览',
			btn_change:chargeSilgle,
			droppable:false,
			onchange:null,
			thumbnail:false, //| true | large
			before_remove: function(){
				$(this).parent().parent().find("input[name='"+config.name+"Name']").val("");
				$(this).parent().parent().find("input[name='"+config.name+"Url']").val("");
				$(this).parent().parent().parent().find('.col-md-12').children('img').attr('src','');
				var getId ='#'+ $(this).parent().parent().parent().parent().context.id+'FileDiv';
				$(getId).find('.fileImage').remove();
				
				if(config.before_remove){
					config.before_remove();
				}else{
					return true;
				}
			},
			before_change:function(){
				if(config.options.single){
					if(config.before_change){
						config.before_change();
					}else{
						var getId ='#'+ $(this).parent().parent().parent().parent().context.id+'FileDiv';
						$(getId).find('.fileImage').remove();
						return true;
					}
				}else{
					if(config.before_change){
						config.before_change();
					}else{
						return true;
					}
				}
			},
			//whitelist:'gif|png|jpg|jpeg'
			blacklist:'exe|php|jsp'
		};

		this._config = config;
		$.extend(true, _options, config.options);
		this._options = _options;
	};
	
	Aptech.uploadify.prototype = {
			render: function(){
				var _self = this;
				var ace_uplodify = $(_self._config.render).ace_file_input(_self._options);
				ace_uplodify.on('change', function(){
					var fd = new FormData();
					var files = $(this).data('ace_input_files');
					if(files && files.length > 0) {
						for(var f = 0; f < files.length; f++) {
							fd.append('Filedata', files[f]);
						}
					}
					fd.append('type', _self._config.type);
					$.ajax({
				        url: _self._config.url,
				        type: 'POST',
			         	processData: false,//important
				  		contentType: false,//important
				     	dataType: 'JSON',//depending on your server side response
				        data: fd,//our FormData object
				        success: function(result){
				        	if(_self._config.callback){
				        		if(result.result == 'success'){
				        			_self._config.callback(result);
				        		}else{
				        			alert(result.errorMsg);
				        		}
				        	}
				        }
					});
				});
				
				return _self;
			},
	
			getValue: function(){
				return $(_self._config.render).val();
			},
			
			setValue: function(value){
				return $(_self._config.render).val(value);
			}
	};
	
	return Aptech;
});