(function ($) {

    $(".menu-btn a").click(function () {
        event.preventDefault();
        $(".gnb_comm").fadeToggle(200);
        $(this).toggleClass('btn-open').toggleClass('btn-close');
    });

    $('.gnb_comm').on('click', function () {
        $(".gnb_comm").fadeToggle(200);
        $(".menu-btn a").toggleClass('btn-open').toggleClass('btn-close');
    });

    $('.menu a').on('click', function () {
        event.preventDefault();
        $(".gnb_comm").fadeToggle(200);
        $(".menu-btn a").toggleClass('btn-open').toggleClass('btn-close');
    });

}(jQuery));
