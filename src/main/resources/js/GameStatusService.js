var GameStatusService = function() {};

GameStatusService.prototype.gameWon = function(board, {onSuccess: successMethod}) {
  $.ajax({
    url: "/game-won",
    method: "POST",
    data: board,
    dataType: "json"
  }).done(function(data) {
    successMethod(data.response.gameStatus.gameWon);
  });
}

