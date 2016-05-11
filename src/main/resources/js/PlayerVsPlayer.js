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
var gameStatusService = new GameStatusService();

  function switchCurrentMarker() {
    currentMarker = oppositeMarker(currentMarker);
  }

  function updateSquareText($this) {
    $this.children().first().text(currentMarker);
  }

  function gameWon() {
    return function(gameWon) {
      return gameWon == "true";
    }
  }

  $(".square").click(function() {
    var squareValue = $(this).children().first().text();
    if(spaceEmpty(squareValue)) {
      var spaceNumber = $(this).data("square");
      var int = parseInt(spaceNumber, 10);
      currentBoard = markBoard(int, currentMarker, currentBoard);
      updateSquareText($(this));
      var board = {board: currentBoard};
      var gameStatus = gameStatusService.gameWon(JSON.stringify(board), gameWon());
      if (gameStatus) {
        alert(currentMarker + " has won the game");
      }
      switchCurrentMarker();
    }
  });

});



