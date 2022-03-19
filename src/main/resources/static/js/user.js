let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
	},
	
	save : function(){
		let data = {
			userName: $("#userName").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		$.ajax().done().fail();
	}
}

index.init();