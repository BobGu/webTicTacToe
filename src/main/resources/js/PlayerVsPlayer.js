$(document).ready(function() {
  var currentMarker;
  var currentBoard;
  var gameStatusService = new GameStatusService();
  var computerMoveService = new ComputerMoveService();
  var isComputerPlayer = true;

  function game() {
    var squareValue = $(this).children().first().text();
    if(spaceEmpty(squareValue)) {
      move($(this), currentMarker);
      switchCurrentMarker();

        if(isComputerPlayer && !boardFull(currentBoard)) {
          var data = {board: currentBoard, marker: currentMarker};
          preventUserFromClick();
          computerMoveService.computerMove(JSON.stringify(data), {onSuccess: computerMove(currentMarker)});
        }
    }
  }

  function move($this, marker) {
    updateSquareText($this, marker);
    var squareNumber = getSquareNumber($this);
    updateBoardAndCheckIfGameIsWon(squareNumber, marker);
  }

  function computerMove(marker) {
    return function(computersMove) {
      $squareToUpdate = $(".square[data-square='" + computersMove + "']");
      updateSquareText($squareToUpdate, marker);
      updateBoardAndCheckIfGameIsWon(computersMove, marker);
      switchCurrentMarker();
      allowUserToClick();
    }
  }

  function updateBoardAndCheckIfGameIsWon(move, marker) {
    var int = parseInt(move);
    board = markBoard(int, marker, currentBoard);
    updateCurrentBoard(board);
    askGameWon();
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

  function askGameWon() {
    var board = {board: currentBoard};
    gameStatusService.gameWon(JSON.stringify(board), {onSuccess: gameWon()});
  }

  function oppositeMarker(currentMarker) {
    if (currentMarker == X_MARKER) {
      return O_MARKER;
    } else {
      return X_MARKER;
    }
  }

  function originalGameSettings() {
    currentMarker = X_MARKER;
    currentBoard = emptyBoard();
    clearSpaces();
  }

  originalGameSettings();
  $(".square").bind("click", game);

  function updateCurrentBoard(board) {
    currentBoard = board;
  }

  function switchCurrentMarker() {
    currentMarker = oppositeMarker(currentMarker);
  }

  function updateSquareText($this, marker) {
    $this.children().first().text(marker);
  }
  function preventUserFromClick() {
    $(".square").unbind("click");
  }

  function allowUserToClick() {
    $(".square").bind("click", game);
  }

  function clearSpaces() {
    $(".marker").text("");
  }

  function getSquareNumber($this) {
    return $this.data("square");
  }


});



