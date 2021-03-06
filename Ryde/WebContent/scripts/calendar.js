/**
 * This javascript file is responsible for all 
 * functionalities of the datepicker.
 */


var rentalDates; 

$( function() 
{
    //function used to init the calender. 
	//also blocks out certain dates from the server rented dates. 
	
    var dateFormat = "mm/dd/yy";
    var from = $( "#from" ).datepicker({
        	changeYear: true,
    		onSelect: getPrice,
        	minDate: 0,
    		defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1, 
            beforeShowDay: disableDays
            }).on( "change", function() {
              to.datepicker( "option", "minDate", getDate(this) );
            });     
    var to = $( "#to" ).datepicker({
            onSelect: getPrice,
            changeYear: true,
            minDate: 0,
    		defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1, 
            beforeShowDay: disableDays
          }).on( "change", function() {
            from.datepicker( "option", "maxDate", getDate( this ) );
          });
    
    jqueryCalendar();
});

 function disableDays(date)
 {
    var month = date.getMonth()+1; 
    var day = date.getDate();
    var year = date.getFullYear().toString().substr(2,2); 
                               
    var totalDate = month + "/" + day + "/" + year; 
                                
                  
    totalDate = new Date (totalDate); 
                               
    //console.log(totalDate); 
    //console.log(x1); 
       
     
     for (i = 0; i<rentalDates.length; i++)
     {
        x1 = rentalDates[i].startDate; 
        x2 = rentalDates[i].endDate; 
             
        x1 = new Date(x1); 
        x2 = new Date(x2); 
             
             
        if ((totalDate.getTime() > x1.getTime()) && (totalDate.getTime() < x2.getTime()))
        {
            return [false, "", "not valid"];
        }

     }
       return [true];
}

function getDate( element ) {
    //returns the date from the calender. 
      
    var date;
    try {
      date = $.datepicker.parseDate( dateFormat, element.value );
    } catch( error ) {
      date = null;
    }

    return date;
} 

function jqueryCalendar()
{

    $.ajax({
    		url: "Servlet?req=calendar", 
    		data: "carId="+$("#carId").val(), 
    		success:function(data)
    		{
    			//console.log(data);
		        data = JSON.parse(data);
		        
		        rentalDates = data; 
		        //rental date array set to servlet response. 
		        
		        //$("#calendarArea").availabilityCalendar(data);
    		}
    }); 
}

function getPrice()
{   
    var startDate = $("#from").val(); 
    var endDate = $("#to").val(); 
    var price = $("#price").text();
 
    //console.log("start " + startDate);
    //console.log("end " + endDate);
    
    if(startDate.trim()=="" || endDate.trim()=="")
    	return;
    
    var msDay = 1000*60*60*24; 
    
    var d1 = new Date(startDate); 
    var d2 = new Date(endDate); 
    
    var totalTime = d2-d1; 
    //difference in milliseconds between two dates.
    
    if(totalTime <= 0){
    	$("#priceBox").text("");
    }else{
    
	    totalTime = totalTime / msDay; 
	    
	    var totalPrice = price * totalTime; 
	    
	    $("#priceBox").text(totalPrice);
    }
    
    doCheck();
    
    //calculates the price and returns. 
    
}

function doCheck(){
	var params = "from="+$("#from").val()+"&to="+$("#to").val();
	console.log(params);
	$.ajax({
		url: "Servlet?req=verifyDates", 
		type: 'POST',
		data: params, 
		success:function(data)
		{
			console.log(data)
	        if(data=="true"){
	        	$('input[type=submit]').removeClass("disabled");
				$('input[type=submit]').attr('disabled', false);
	        }
			else{
				$('input[type=submit]').addClass("disabled");
				$('input[type=submit]').attr('disabled', true);
			}
	        
		}
	}); 

    	
}
