var GameStatusService = function() {};

GameStatusService.prototype.gameWon = function(board, callbackObject) {
  $.ajax({
    url: "/game-won",
    method: "POST",
    data: board,
    dataType: "json"
  }).done(function(data) {
    callbackObject.onSuccess(data.response.gameStatus.gameWon);
  });
}

