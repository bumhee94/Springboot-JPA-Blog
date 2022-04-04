let index = {
	init:function(){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		
		$("#btn-delete").on("click", ()=>{
			this.deleteById();
		});
		
		$("#btn-update").on("click", ()=>{
			this.update();
		});
		
		$("#btn-reply-save").on("click", ()=>{
			this.replySave();
		});
	},
	
	save : function(){
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
			
		};
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utr-8",
			dataType: "json"
		}).done(function(resp){
			alert("글쓰기가 완료 되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringfy(error));
		});


		
	},
	
		deleteById : function(){
	
		let id = $("#id").text();
		

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp){
			alert("삭제 완료 되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringfy(error));
		});


		
	},
	
	update : function(){
		
		let id=$("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
			
		};
		
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utr-8",
			dataType: "json"
		}).done(function(resp){
			alert("글쓰기가 수정 되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringfy(error));
		});


		
	},
	
	
	replySave : function(){
		let data = {
			content: $("#reply-content").val(),
			
		};
		
		let boardId = $("#boardId").val();
		
		$.ajax({
			type: "POST",
			url: "/api/board/"+boardId+"/reply",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utr-8",
			dataType: "json"
		}).done(function(resp){
			alert("댓글 작성이 완료 되었습니다.");
			location.href = "/board/"+boardId;
		}).fail(function(error){
			alert(JSON.stringfy(error));
		});


		
	},
	replyDelete : function(boardId, replyId){

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+boardId+"/reply/"+replyId,
			dataType: "json"
		}).done(function(resp){
			alert("댓글 삭제 완료 되었습니다.");
			location.href = "/board/"+boardId;
		}).fail(function(error){
		});


		
	}
	
}

index.init();