$('.site-mobile-menu-body .has-children').each((index, li) => {
    $(li).click(() => {
        $(li).children('ul').toggle();
		return false;
    });
});


$('.site-mobile-menu-body .has-children a').click((e) => {
	if(!$(e.target).is('a[href="#"]')){
    	e.stopPropagation();
	}
});