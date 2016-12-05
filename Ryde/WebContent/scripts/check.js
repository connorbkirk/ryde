/**
 * This javascript file is responsible for
 * making sure that a user has input text
 * in all fields before enabling the submit button.
 */

$(document).ready(function(){
    $('.text-entry').keyup(doCheck).focusout(doCheck);
    
    if($("input[name=year]").length && $("input[name=price]").length)
    	doExtraCheck();
    else
    	doCheck();
});


//make sure all the fields are not empty before allowing user to continue
function doExtraCheck(){
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

function doCheck(){
    var allFilled = true;
    $('.text-entry').each(function(){
        if($(this).val() == ''){
            allFilled = false;
            return false;
        }
    });
    if(allFilled){
    	$('input[type=submit]').removeClass("disabled");
    	$('input[type=submit]').attr('disabled', false);
    }
    else{
    	$('input[type=submit]').addClass("disabled");
    	$('input[type=submit]').attr('disabled', true);
    }
}