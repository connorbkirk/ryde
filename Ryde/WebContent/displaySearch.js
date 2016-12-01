

$(document).ready(function(){
  $("#makeSearchbox").keypress(function displaySearch()
{
    var inputText = $("#makeSearchbox").val();
    //returns the value of the input area to variable.


    $.ajax({url:"Searchlet", type:"GET", data:{"inputText": inputText}, success:function(){

        var listing = undefined;
        listing = parse(data);

        $("#makeSearchbox").val(listing);


    }}) )//makeSearchbox
  })//ready
    //variable from previous LOC used as param for ajax request.
    //returns data from the servlet?





}
