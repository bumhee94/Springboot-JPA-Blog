let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		$("#btn-login").on("click", ()=>{
			this.login();
		});
	},
	
	save : function(){
		let data = {
			userName: $("#userName").val(),
			passWord: $("#password").val(),
			email: $("#email").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/api/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utr-8",
			dataType: "json"
		}).done(function(resp){
			alert("회원가입이 완료 되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringfy(error));
		});


		
	},
		login : function(){
		let data = {
			userName: $("#userName").val(),
			passWord: $("#password").val(),
		};
		
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utr-8",
			dataType: "json"
		}).done(function(resp){
			alert("로그인이 완료 되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringfy(error));
		});


		
	}
}

index.init();