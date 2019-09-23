/**
 * 
 */
//$obj : $("input[name]")
function checkInputEmpty( $objs ){ 
	var count = 0;
	
	$objs.each(function(i, obj){ //obj : input객체
		if( $(obj).val() == "" ){
			var $next = $(obj).next();
			$next.css("display","inline");
			count++;
		}
	})
	
	if(count > 0)
		return false; //빈 input태그가 존재함
	
	return true;// 빈 input태그가 존재하지 않음
}



