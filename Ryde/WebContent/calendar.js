      
var rentalDates; 

$( function() 
{
    //function used to init the calender. 
	//also blocks out certain dates from the server rented dates. 
	
    var dateFormat = "mm/dd/yy";
    var from = $( "#from" ).datepicker({
        	defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1, 
            beforeShowDay: disableDays
            }).on( "change", function() {
              to.datepicker( "option", "minDate", getDate(this) );
            });     
    var to = $( "#to" ).datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1, 
            beforeShowDay: disableDays
          }).on( "change", function() {
            from.datepicker( "option", "maxDate", getDate( this ) );
          });
    
    jqueryCalender();
});

 function disableDays(date)
 {
    var month = date.getMonth()+1; 
    var day = date.getDate();
    var year = date.getFullYear().toString().substr(2,2); 
                               
    var totalDate = month + "/" + day + "/" + year; 
                                
                  
    totalDate = new Date (totalDate); 
                               
    console.log(totalDate); 
    //console.log(x1); 
       
     
     for (i = 0; i<rentalDates.length; i++)
     {
        x1 = rentalDate[i].startDate; 
        x2 = rentalDate[i].endDate; 
             
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

function jqueryCalender()
{

    $.ajax({
    		url: "Servlet?req=calendar", 
    		type: "GET",
    		success:function(data)
    		{
	        data = JSON.parse(data); 
	        
	        rentalDates = data; 
	        //rental date array set to servlet response. 
	        
	        
	        
	        //$("#calendarArea").availabilityCalendar(data);
    		}
    }); 
}
