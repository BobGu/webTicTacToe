describe("GameStatusService", function() {
  var request;
  var onSuccess;

  beforeEach(function() {
    jasmine.Ajax.install();
    onSuccess = jasmine.createSpy("onSuccess");

    var board = '{"board": "[0, 1, 2, 3, 4, 5, 6, 7, 8]"}';
    gameStatus = new GameStatusService();
    gameStatus.gameWon(board, {
      onSuccess: onSuccess
    });

    request = jasmine.Ajax.requests.mostRecent();
    expect(request.url).toBe("/game-won");
    expect(request.method).toBe("POST");
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
