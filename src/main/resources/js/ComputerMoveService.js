var ComputerMoveService = function() {};

ComputerMoveService.prototype.computerMove = function(data, callbackObject) {
  $.ajax({
    url: "/computer-move",
    method: "POST",
    data: data,
    dataType: "json"
  }).done(function(data) {
    callbackObject.onSuccess(data.computerMove);
  });
}
