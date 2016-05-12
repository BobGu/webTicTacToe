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
  var currentMarker;
  var currentBoard;
  var gameStatusService = new GameStatusService();

  function originalGameSettings() {
    currentMarker = X_MARKER;
    currentBoard = emptyBoard();
  }

  function clearSpaces() {
    $(".marker").text("");
  }

  originalGameSettings();

  function switchCurrentMarker() {
    currentMarker = oppositeMarker(currentMarker);
  }

  function updateSquareText($this) {
    $this.children().first().text(currentMarker);
  }

  function gameWon() {
    return function(gameWon) {
      if (gameWon == "true") {
        alert(oppositeMarker(currentMarker) + " has won the game");
        originalGameSettings();
        clearSpaces();
      }
    }
  }

  function getSquareNumber($this) {
    return $this.data("square");
  }

  function move($this) {
    updateSquareText($this);
    var squareNumber = getSquareNumber($this);
    var int = parseInt(squareNumber, 10);
    currentBoard = markBoard(int, currentMarker, currentBoard);
  }

  function askGameWon() {
    var board = {board: currentBoard};
    gameStatusService.gameWon(JSON.stringify(board), {onSuccess: gameWon()});
  }

  $(".square").click(function() {
    var squareValue = $(this).children().first().text();
    if(spaceEmpty(squareValue)) {
      move($(this));
      askGameWon();
      switchCurrentMarker();
    }
  });

});



