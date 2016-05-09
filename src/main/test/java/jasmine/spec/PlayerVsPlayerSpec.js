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

describe("spaceEmpty", function() {
  it("checks for an empty space", function() {
    expect(true).toBe(spaceEmpty("2"));
    expect(false).toBe(spaceEmpty(X_MARKER));
    expect(false).toBe(spaceEmpty(O_MARKER));
  });
});

describe("oppositeMarker", function() {
  it("return the opposite marker", function() {
    expect(X_MARKER).toEqual(oppositeMarker(O_MARKER));
    expect(O_MARKER).toEqual(oppositeMarker(X_MARKER));
  });
});
