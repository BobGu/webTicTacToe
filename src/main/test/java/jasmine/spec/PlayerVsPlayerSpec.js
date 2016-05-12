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
  it("returns the opposite marker", function() {
    expect(X_MARKER).toEqual(oppositeMarker(O_MARKER));
    expect(O_MARKER).toEqual(oppositeMarker(X_MARKER));
  });
});

describe("GameStatusService", function() {
  var request;
  var onSuccess;

  beforeEach(function() {
    jasmine.Ajax.install();
    onSuccess = jasmine.createSpy('onSuccess');

    var board = '{"board": "[0, 1, 2, 3, 4, 5, 6, 7, 8]"}';
    gameStatus = new GameStatusService();
    gameStatus.gameWon(board, {
      onSuccess: onSuccess
    });

    request = jasmine.Ajax.requests.mostRecent();
    expect(request.url).toBe('/game-won');
    expect(request.method).toBe('POST');
  });

  describe("on success", function() {
    beforeEach(function() {
      request.respondWith(gameWonResponses.success);
    });

    it("returns the game won as being false", function() {
      var gameWon = onSuccess.calls.mostRecent().args[0];
      expect("false").toEqual(gameWon);
    });
  });

  afterEach(function() {
    jasmine.Ajax.uninstall();
  });
});

describe("boardFull", function() {
  it("returns true if board is full", function() {
    var board = ["X", "O", "X",
                   "O", "X", "O",
                   "O", "X", "O"];
    expect(true).toEqual(boardFull(board));
  });

  it("returns false if board is not full", function() {
    var board = ["0", "1", "X", "X", "X", "X", "X", "X", "X", "X"];
    expect(false).toEqual(boardFull(board));
  });
});

