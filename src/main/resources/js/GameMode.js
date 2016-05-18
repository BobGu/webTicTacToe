var isComputerPlayer;

$(document).ready(function() {

  $(".board").hide();

  $(".game-mode button").click(function() {
    $(".game-mode").hide();
    $(".board").show();
    isComputerPlayer = $(this).data("computer-player");
  });

});