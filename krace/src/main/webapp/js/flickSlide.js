$(document).ready(function() {
  
  $("#pagNav01").touchSlider({
    flexible : true,
    autoplay : {
        enable : true,
        pauseHover : true,
        addHoverTarget : "",
        interval : 3000
      },
    btn_prev : $("#pagNav01").next().find(".btn_prev"),
    btn_next : $("#pagNav01").next().find(".btn_next"),
    initComplete : function (e) {
      $("#pagNav01_paging").html("");
      $("#pagNav01 ul li").each(function (i, el) {
        $("#pagNav01_paging").append('<button type="button" class="btn_page">page' + (i+1) + '</button>');
      });
      $("#pagNav01_paging .btn_page").bind("click", function (e) {
        var i = $(this).index();
        $("#pagNav01").get(0).go_page(i);
      });
    },
    counter : function (e) {
      $("#pagNav01_paging .btn_page").removeClass("on").eq(e.current-1).addClass("on");
    }
  });

  $("#pagNav02").touchSlider({
    flexible : true,
    btn_prev : $("#pagNav02").next().find(".btn_prev"),
    btn_next : $("#pagNav02").next().find(".btn_next"),
    initComplete : function (e) {
      $("#pagNav02_paging").html("");
      $("#pagNav02 ul li").each(function (i, el) {
        $("#pagNav02_paging").append('<button type="button" class="btn_page">page' + (i+1) + '</button>');
      });
      $("#pagNav02_paging .btn_page").bind("click", function (e) {
        var i = $(this).index();
        $("#pagNav02").get(0).go_page(i);
      });
    },
    counter : function (e) {
      $("#pagNav02_paging .btn_page").removeClass("on").eq(e.current-1).addClass("on");
    }
  });
  
});