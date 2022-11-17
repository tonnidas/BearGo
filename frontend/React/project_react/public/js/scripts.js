import $ from 'jquery';
$(document).ready(function (e) {
  $('.sidebar-toggler').on('click', function () {
    $('.sidebar').toggleClass('sidebar-collapse');
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
$('.tab-nav li').click(function () {
  var tab_id = $(this).attr('data-tab');
  $('.tab-nav li').removeClass('active');
  $('.tab-content').hide();
  $(this).addClass('active');
  $('#' + tab_id).show();
});

// Edit

// TAB
function slideItem(thechosenone) {
  $('.slide-content').each(function (index) {
    if ($(this).attr('id') == thechosenone) {
      $(this).slideDown(500);
    } else {
      $(this).slideUp(500);
    }
  });
}
$('.slide-nav ul li a').click(function () {
  $('.slide-nav ul li a').removeClass('active');
  $(this).addClass('active');
});
