function sendInfo()
{
    var request;
    
    if (window.XMLHttpRequest)
        {
                request = new XMLHttpRequest(); 
        }
    
    
    return request; 

}




function ajaxCallValidation()
{
    var username= document.getElementById("username"); 
    var password = document.getElementById("password"); 
    //init username and password. 
    
    
    var request; 
    
    if (window.XMLHttpRequest)
        {
            request = new XMLHttpRequest(); 
        }
    
    request.open("POST", "login", true); 
    request.send("username=" + username & "password=" + password);
    //opens connection to server and sends details. 
    
    request.onreadystatechange = funtion()
    {
        if (this.status === 200)
            {
                document.getElementById("validationFeild").innerHTML = this.resoponseText; 
            }
    }
    //when we get a response send the response info back to html. 
    
}


function ajaxCallCalender()
{
    var startDate = document.getElementById("startDate"); 
    var endDate = document.getElementById("endDate"); 
    
    var request; 
    
        if (window.XMLHttpRequest)
        {
            request = new XMLHttpRequest(); 
        }
    
    request.open("GET", "calender", true); 
    
    
    
    request.onreadystatechange = function()
    {
        if (this.status === 200)
            return this.resoponseText; 
    }
    
}


function ajaxDisplayAlert()
{
    var request; 
    
    if (window.XMLHttpRequest)
        {
            request = new XMLHttpRequest(); 
        }
    
    
    
    
}
