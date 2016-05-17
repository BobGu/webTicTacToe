var ComputerMoveService = function() {};

ComputerMoveService.prototype.computerMove = function(board, callbackObject) {
  $.ajax({
    url: "/computer-move",
    method: "POST",
    data: board,
    dataType: "json"
  }).done(function(data) {
    callbackObject.onSuccess(data);
  });
}
