$(document).ready(function(){
    $('.text-entry').keyup(doCheck).focusout(doCheck);
});


//make sure all the fields are not empty before allowing user to continue
function doCheck(){
    var allFilled = true;
    $('.text-entry').each(function(){
        if($(this).val() == ''){
            allFilled = false;
            return false;
        }
    });
    if(allFilled && $.isNumeric($("input[name=year]").val()) && $.isNumeric($("input[name=price]").val())){
    	$('input[type=submit]').removeClass("disabled");
    	$('input[type=submit]').attr('disabled', false);
    }
    else{
    	$('input[type=submit]').addClass("disabled");
    	$('input[type=submit]').attr('disabled', true);
    }
}