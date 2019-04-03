define(function(require, exports, module){
	require("../../css/themes/{theme}/bootstrap-duallistbox.min.css");
	require("../thirdparty/jquery.bootstrap-duallistbox.min");	
	Aptech.duallistbox = function(config){
		var _config = {
			name: "",
			datasource:"",
			multiple: false,
			initValue:"",
			container:"",
		};
		
		
		this._config = config;
		if(this._config.render){
			this._config.container = $(config.render);
		}
	};

	Aptech.duallistbox.prototype = {
		render:  function(){
			var _self = this;
			_self._config.container.attr("name", _self._config.name);
			if(_self._config.datasource){
				_self._config.container.empty();
				for(var i=0; i<_self._config.datasource.length; i++){
					var option = $("<option value="+ _self._config.datasource[i].code +">"+ _self._config.datasource[i].name+"</option>");
					_self._config.container.append(option);
				}
			}
			if(_self._config.multiple){
				_self._config.container.attr("multiple", "multiple");
			}
			if(_self._config.initValue){
				_self.setValue(_self._config.initValue);
			}
			var demo1 = _self._config.container.bootstrapDualListbox({infoTextFiltered: '<span class="label label-purple label-lg">Filtered</span>'});
			var container1 = demo1.bootstrapDualListbox('getContainer');
			container1.find('.btn').addClass('btn-white btn-info btn-bold');
			return _self;
		},
		
		getValue: function(){
			var _self = this;
			if(_self._config.multiple){
				var resultValues = [];
                var options = $(_self._config.render + " option:selected");
                for (i = 0; i < options.length; i++) {
                	resultValues.push($(options[i]).val());
                }
                return resultValues.join(",");
			}
			return $(this._config.render).val();
		},
		
		setValue: function(value){
			var _self = this;
			if(value){
				if(_self._config.multiple){
					if(typeof value=='string'){
						var arr = value.split(',');
			            var length = arr.length;
			            var value = '';
			            var setArr = [];
			            for (i = 0; i < length; i++) {
			                value = arr[i];
			                setArr.push(value);
			            }
		                _self._config.container.val(setArr);

					}else{
			             _self._config.container.val(value);
					}
				}else{
					_self._config.container.val(value);
				}
			}	
		},
	};

	return Aptech;
});