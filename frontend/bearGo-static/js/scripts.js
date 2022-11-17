
$(document).ready(function(e) {
 
  $('.sidebar-toggler').on('click', function(){
     $('.sidebar').toggleClass('sidebar-collapse');
   });
// chat
   $('.chatapp-user-list ul li,.back').on('click', function () {
    $('.chatapp-container').toggleClass('in');
  });
  $('.view-details,.close-details').on('click', function () {
    $('.profile-details').toggleClass('in');
  });
  $('.message ul li').click(function (e) {
    $('.message ul li').removeClass('open');
    $(this).addClass('open');
    e.stopPropagation();
  });
  $('.chatapp-conversation').on('click', function () {
    $('.message ul li').removeClass('open');
  });
  $('.chatapp-user-list ul li').click(function () {
    $('.chatapp-user-list ul li').removeClass('active');
    $(this).addClass('active');
  });
  $('.message ul li:has(img,video,audio)').addClass('message-media');

  // package tooltip
  $(".option-tooltip").on("click", function (e) {
    $(".option-tooltip").removeClass("active");
    $(this).addClass("active");
    e.stopPropagation();
  });

  $("body").on("click", function (e) {
    $('.option-tooltip').removeClass("active");
    e.stopPropagation();
  });

  // msg option
  $(".upload-option").on("click", function (e) {
    $(".upload-option").removeClass("active");
    $(this).addClass("active");
    e.stopPropagation();
  });

  $("body").on("click", function (e) {
    $('.upload-option').removeClass("active");
    e.stopPropagation();
  });

});

// Modal
function showModal(id) {
    $('#' + id).fadeIn(400);
}
function hideModal(id) {
    $('#' + id).fadeOut(400);
}

//Tabs
$('.tab-nav li').click(function(){
  var tab_id = $(this).attr('data-tab');
    $('.tab-nav li').removeClass('active');
    $('.tab-content').hide();
    $(this).addClass('active');
    $("#"+tab_id).show();
  });


// Edit

// TAB
function slideItem(thechosenone) {
$('.slide-content').each(function(index) {
  if ($(this).attr("id") == thechosenone) {
      $(this).slideDown(500);
  } else {
      $(this).slideUp(500);
  }
});
}
$('.slide-nav ul li a').click(function() {
    $('.slide-nav ul li a').removeClass('active');
    $(this).addClass('active');
});


