	Aptech.confirm = function(message,callback){
		var  confirm = bootbox.confirm({
	        buttons: {  
	            confirm: {  
	                label: '确定',  
	                className: 'btn btn-success btn-sm'  
	            },  
	            cancel: {  
	                label: '取消',  
	                className: 'btn btn-danger btn-sm'  
	            }  
	        },
	        className: 'custom-bootbox',
	        message: message,  
	        callback: function(result) {  
	            if(result) {
	            	if(callback)callback();
	            } else {  
	            }  
	        },  
		});
	}
	
	Aptech.alert = function(message){
		bootbox.dialog({
			message: message
		});
	}
