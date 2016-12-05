/**
 * This javascript file is responsible for the auto
 * complete done on text boxes with the following
 * ids: make, model, type.
 */
$(function(){
	$("#make").autocomplete({
		width: 300,
		max: 10,
		delay: 100,
		minLength: 0,
		autoFocus: false,
		cacheLength: 1,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
            	url:"Servlet?req=autoMake",
    			type:"GET",
    			dataType: "json",
    			data:"inputText="+$("#make").val(),
                success: function( data) {
                    //console.log( data);
                    var items = data;
                    response(items);
                },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                }
            });
        }
	}).focus(function(){
		$(this).autocomplete("search");
	});
	
	$("#type").autocomplete({
		width: 300,
		max: 10,
		delay: 100,
		minLength: 0,
		autoFocus: false,
		cacheLength: 1,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
            	url:"Servlet?req=autoType",
    			type:"GET",
    			dataType: "json",
    			data:"inputText="+$("#type").val(),
                success: function( data) {
                    //console.log( data);
                    var items = data;
                    response(items);
                },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                }
            });
        }
	}).focus(function(){
		$(this).autocomplete("search");
	});
	
	$("#model").autocomplete({
		width: 300,
		max: 10,
		delay: 100,
		minLength: 0,
		autoFocus: false,
		cacheLength: 1,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
            	url:"Servlet?req=autoModel",
    			type:"GET",
    			dataType: "json",
    			data:"inputText="+$("#model").val(),
                success: function( data) {
                    //console.log( data);
                    var items = data;
                    response(items);
                },
                error: function(jqXHR, textStatus, errorThrown){
                     console.log( textStatus);
                }
            });
        }
	}).focus(function(){
		$(this).autocomplete("search");
	});
});