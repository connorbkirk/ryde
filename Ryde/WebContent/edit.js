window.onload=init;

function init(){
	$(".deleteImage").click(deleteImage);
	$("#upload").change(uploadImage);
}

function deleteImage(){
	var id = $(this).attr('id');//get id of image to delete
	$.ajax({url: "Servlet?req=deleteImage", 
		data: "id="+id, 
		success: function(){
			$("#img-"+id).hide("slow");
		}
	});
	
}

function uploadImage(){
	$("#image-upload").submit(function(e){
	    var formObj = $(this);
	    var formURL = formObj.attr("action");
	    var formData = new FormData(this);
	    $.ajax({
	        url: formURL,
	        type: 'POST',
	        data:  formData,
	        mimeType:"multipart/form-data",
	        contentType: false,
	        cache: false,
	        processData:false,
	        success: function(data){
	        	var result = JSON.parse(data);
	        	
	        	//create list item
	        	var li = $(document.createElement('li'));
	        	li.attr("id", "img-" + result.image.id);
	        	li.css("background-image", "url("+result.image.image+")");
	        	
	        	//create overlay
	        	var div = $(document.createElement('div'));
	        	div.attr("class", "overlay");
	        	
	        	//create button in overlay
	        	var inp = $(document.createElement('input'));
	        	inp.attr("type", "button");
	        	inp.val("X");
	        	inp.attr("class", "deleteImage");
	        	inp.click(deleteImage);
	        	inp.attr("id", result.image.id);
	        	
	        	//append items
	        	div.append(inp);
	        	li.append(div);
	        	$("#current-images").append(li);
		    },
		    error: function(){
		    	var error = $(document.createElement('p'));
		    	error.val("Error. Please try again");
		    	$("#image-upload").append(error);
		    }          
	    });
	    e.preventDefault(); //Prevent Default action. 
	}); 
	$("#image-upload").submit(); //Submit the form
	
}