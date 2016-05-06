function getTestimonial(index)
{
 
   
    $testimonialIndex = $(masterData).find( "testimonial[indexval="+index+"]" );
    var TestimonialList ='';
    var appPageCsize=$(masterData).find("appPageCsize").text();   
	
    $testimonialIndex.find("testimonialRecord").each(function()
                                 {
                                 	var name=$(this).find("name").text();
									var comment=$(this).find("comment").text();
     
                                   TestimonialList +='<div class="testimonials" ><div class="testi-comment">'+comment+'<span class="qt"></span></div><span class="user_name">'+name+'</span></div>';
                                 }
                          );
    appendHtml("<div class='page-text'>"+TestimonialList+"</div>",'',1);    
	$(".page-text").addClass(appPageCsize);
}