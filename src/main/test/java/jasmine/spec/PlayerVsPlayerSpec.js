describe(".emptyBoard", function() {
  it("Board contains no markers", function() {
    expect(["0", "1", "2", "3", "4", "5", "6", "7", "8"]).toEqual(emptyBoard());
  });
});

describe("markBoard", function() {
  it("marks a board", function() {
    var board = emptyBoard();
    var markedBoard = markBoard(0, "X", board);
    expect(["X", "1", "2", "3", "4", "5", "6", "7", "8"]).toEqual(markedBoard);
  });
});

