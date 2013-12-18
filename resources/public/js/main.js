
$('.markdown-view').bind('input propertychange', function() {
  $.ajax({
    url: "/convert?md=" + encodeURIComponent(this.value),
    success: function( data ) {
        $('.html-view').html(data);
    }
  });
});


