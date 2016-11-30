      
var rentalDates; 


 function disableDays(date)
 {
    var month = date.getMonth(); 
    var day = date.getDate();
    var year = date.getFullYear().toString().substr(2,2); 
                               
    var totalDate = month + "/" + day + "/" + year; 
                                
                  
    totalDate = new Date (totalDate); 
                               
    console.log(totalDate); 
    console.log(x1); 
                  
    if (totalDate.getTime() == x1.getTime())
    {
                                       
        console.log("FOUND IT"); 
            return [false, "", "not valid"];
    }
    else
        return [true]; 
  }
      





$( function() 
{
          //function used to init the calender. 
          //also blocks out certain dates from the server rented dates. 
    
    var dateFormat = "mm/dd/yy",
        from = $( "#from" )
            .datepicker({
              defaultDate: "+1w",
              changeMonth: true,
              numberOfMonths: 1, 
                beforeShowDay: disableDays
            })
            .on( "change", function() {
              to.datepicker( "option", "minDate", getDate(this) );
            }),
          to = $( "#to" ).datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 1, 
              beforeShowDay: disableDays
          })
          .on( "change", function() {
            from.datepicker( "option", "maxDate", getDate( this ) );
          });

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
      } );

function jqueryCalender()
{
    var startDate = document.getElementById("from"); 
    var endDate = document.getElementById("to"); 
    
    
    $.ajax(url: "Servlet", data: {calenderVal : "calender" }, success:function(data)
    {
        data = jQuery.parseJSON(data); 
        
        rentalDate = data; 
        //rental date array set to servlet response. 
        
        
        
        //$("#calendarArea").availabilityCalendar(data);
        
    }); 
}
