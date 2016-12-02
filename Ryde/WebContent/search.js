$(function(){
	$("#makeSearchbox").keypress(function(){
		var inputText = $("#makeSearchbox").val();
		//returns the value of the input area to variable
		
		$.ajax({
			url:"Servlet?req=autoMake",
			type:"GET",
			data:"inputText="+inputText,
			success: function(data){
				if(data!="")
					$("#makeSearchbox").val(data);
			}
		});
	})
});


