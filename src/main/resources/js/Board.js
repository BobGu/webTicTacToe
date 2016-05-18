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


function boardFull(board) {
  var boardFull = true;
  board.forEach(function(space, index, spaces) {
    if(spaceEmpty(space)) {
      boardFull = false;
    }
  });
  return boardFull;
}
