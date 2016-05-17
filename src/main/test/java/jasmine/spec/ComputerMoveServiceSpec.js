describe("ComputerMoveService", function() {
  var request;
  var onSuccess;

  beforeEach(function(){
    jasmine.Ajax.install();
    onSuccess = jasmine.createSpy("onSuccess");

    var jsonToSend = '{"board": ["0", "1", "2", "3", "4", "5", "6", "7", "8"], "marker": "X"}';
    var computerMoveService = new ComputerMoveService();
    computerMoveService.computerMove(jsonToSend, {
      onSuccess: onSuccess
    });

    request = jasmine.Ajax.requests.mostRecent();
    expect(request.url).toBe("/computer-move");
    expect(request.method).toBe("POST");
  });


  describe("on success", function() {
    beforeEach(function() {
      request.respondWith(computerMoveResponse.success);
    });

    it("the computers move is 2", function() {
      var response = onSuccess.calls.mostRecent().args[0];
      expect("2").toEqual(response.computerMove);
    });
  });

 afterEach(function() {
  jasmine.Ajax.uninstall();
  });
});

