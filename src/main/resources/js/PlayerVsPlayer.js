var X_MARKER = "X"
var O_MARKER = "O"

function emptyBoard() {
  return ["0", "1", "2", "3", "4", "5", "6", "7", "8"];
}

function markBoard(space, marker, board) {
  board[space] = marker;
  return board;
}

function spaceEmpty(squareValue) {
  return squareValue != X_MARKER && squareValue != O_MARKER;
}

function oppositeMarker(currentMarker) {
  if (currentMarker == X_MARKER) {
    return O_MARKER;
  } else {
    return X_MARKER;
  }
}


$(document).ready(function() {

var currentMarker = "X";
var currentBoard = emptyBoard();
var gameStatusService = new gameStatusService();

  function switchCurrentMarker() {
    currentMarker = oppositeMarker(currentMarker);
  }

  function updateSquareText($this) {
    $this.children().first().text(currentMarker);
  }

  function gameWon(gameWon) {
    return gameWon == "true";
  }

  $(".square").click(function() {
    var squareValue = $(this).children().first().text();
    if(spaceEmpty(squareValue)) {
      var int = parseInt(squareValue, 10);
      markBoard(int, currentMarker, currentBoard);
      updateSquareText($(this));
      var gameWon = gameStatusService.gameWon({board:board}, gameWon);
      if (gameWon) {
        alert(currentMarker + " has won the game");
      }
      switchCurrentMarker();
    }
  });

});



