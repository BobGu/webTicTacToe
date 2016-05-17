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

function boardFull(board) {
  var boardFull = true;
  board.forEach(function(space, index, spaces) {
    if(spaceEmpty(space)) {
      boardFull = false;
    }
  });
  return boardFull;
}


$(document).ready(function() {
  var currentMarker;
  var currentBoard;
  var gameStatusService = new GameStatusService();
  var computerMoveService = new ComputerMoveService();
  var isComputerPlayer = true;

  function originalGameSettings() {
    currentMarker = X_MARKER;
    currentBoard = emptyBoard();
    clearSpaces();
  }

  function clearSpaces() {
    $(".marker").text("");
  }

  originalGameSettings();

  function switchCurrentMarker() {
    currentMarker = oppositeMarker(currentMarker);
  }

  function updateSquareText($this, marker) {
    $this.children().first().text(marker);
  }

  function gameWon() {
    return function(gameWon) {
      if (gameWon == "true") {
        alert(oppositeMarker(currentMarker) + " has won the game");
        originalGameSettings();
      } else if (boardFull(currentBoard)) {
        alert("The game is a tie");
        originalGameSettings();
      }
    }
  }

  function computerMove(currentMarker) {
    return function(computersMove) {
      $squareToUpdate = $(".square[data-square='" + computersMove + "']");
      updateSquareText($squareToUpdate, currentMarker);
      var int = parseInt(computersMove);
      currentBoard = markBoard(int, currentMarker, currentBoard);
      askGameWon();
      switchCurrentMarker();
    }
  }

  function getSquareNumber($this) {
    return $this.data("square");
  }

  function move($this) {
    updateSquareText($this, currentMarker);
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

        if(isComputerPlayer) {
          var data = {board: currentBoard, marker: currentMarker};
          computerMoveService.computerMove(JSON.stringify(data), {onSuccess: computerMove(currentMarker)});
        }
    }

  });

});



