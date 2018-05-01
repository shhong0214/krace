
// ** GNB **
jQuery(document).ready(function($) {
    // hide the menu when the page load
    $(".gnb_comm").hide();
    // when .menuBtn is clicked, do this
    $(".btn_mobile_n").click(function() {
        // open the menu with slide effect
        $(".gnb_comm").slideToggle(300);
    });
});

// ** 레이어팝업 **
//Element ID 불러쓰기
function dEI(elementID){
  return document.getElementById(elementID);
}

//레이어 팝업 열기
function openLayer(IdName, tpos, lpos){
  var pop = dEI(IdName);
  pop.style.top = tpos + "px";
  pop.style.left = lpos + "%";
  pop.style.display = "block";

  var wrap = dEI("wrapper");
  var reservation = document.createElement("div");
  reservation.setAttribute("id", "deemed");
  wrap.appendChild(reservation);
}

//레이어 팝엽 닫기
function closeLayer( IdName ){
  var pop = dEI(IdName);
  pop.style.display = "none";
  var clearEl=parent.dEI("deemed");
  var momEl = parent.dEI("wrapper");
  momEl.removeChild(clearEl);
}

