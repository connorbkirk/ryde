function displaySearch()
{
    var inputText = $("#searchArea").val(); 
    //returns the value of the input area to variable. 
    
    
    $.ajax({url:"Servlet", type:"GET", data: inputText, success:function(){
        
        var listing = undefined; 
        listing = parse(data); 
        
        $("#searchList").val(listing); 
        
        
    }})
    //variable from previous LOC used as param for ajax request. 
    //returns data from the servlet?
    
    
    
    
    
}
