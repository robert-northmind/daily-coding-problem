import 'dart:collection';

void main() {
  List<List<int>> testData1 = [
    [1, 0, 0, 0, 0],
    [0, 0, 1, 1, 0],
    [0, 1, 1, 0, 0],
    [0, 0, 0, 0, 0],
    [1, 1, 0, 0, 1],
    [1, 1, 0, 0, 1],
  ];
  List<List<int>> testData2 = [
    [1, 1, 0, 0, 0],
    [0, 1, 0, 0, 1],
    [1, 0, 0, 1, 1],
    [0, 0, 0, 0, 0],
    [1, 0, 1, 0, 1],
  ];

  IslandCounter counter = IslandCounter(testData1);
  print("nbrIslands: ${counter.getNbrIslands()}");

  counter = IslandCounter(testData2);
  print("nbrIslands: ${counter.getNbrIslands()}");
}

class Position {
  final int x, y;
  Position(this.x, this.y);
  String keyVal() {
    return "${x}X${y}";
  }
}

class IslandCounter {
  final List<List<int>> data;
  var _processedIndices = <String>{};
  var _pixelProcessQueue = ListQueue<Position>();
  var _nbrIslands = 0;

  IslandCounter(this.data) {}

  int getNbrIslands() {
    for (var row in data) {
      print("${row}");
    }

    for (int y = 0; y < data.length; y++) {
      for (int x = 0; x < data[y].length; x++) {
        if (_isLand(x, y) && !_isProcessed(x, y)) {
          _nbrIslands += 1;
          _pixelProcessQueue.add(Position(x, y));
          while (_pixelProcessQueue.length > 0) {
            Position pos = _pixelProcessQueue.removeFirst();
            _markAsProcessed(pos.x, pos.y);
            _checkSurroundingPixels(pos);
          }
        }
      }
    }

    return _nbrIslands;
  }

  bool _isLand(int x, int y) {
    if (_subindexIsValid(x, y)) {
      return data[y][x] == 1;
    } else {
      return false;
    }
  }

  bool _isProcessed(int x, int y) {
    return _processedIndices.contains("${x}X${y}");
  }

  _markAsProcessed(int x, int y) {
    _processedIndices.add("${x}X${y}");
  }

  _checkSurroundingPixels(Position pos) {
    final List<Position> surrounding = [
      Position(pos.x - 1, pos.y - 1),
      Position(pos.x, pos.y - 1),
      Position(pos.x + 1, pos.y - 1),
      Position(pos.x - 1, pos.y),
      Position(pos.x + 1, pos.y),
      Position(pos.x - 1, pos.y + 1),
      Position(pos.x, pos.y + 1),
      Position(pos.x + 1, pos.y + 1),
    ];
    surrounding.forEach((aPos) {
      if (_subindexIsValid(aPos.x, aPos.y) &&
          !_isProcessed(aPos.x, aPos.y) &&
          _isLand(aPos.x, aPos.y)) {
        _pixelProcessQueue.add(aPos);
      }
    });
  }

  bool _subindexIsValid(int x, int y) {
    if (y < 0 || y >= data.length) {
      return false;
    }
    if (x < 0 || x >= data[y].length) {
      return false;
    }
    return true;
  }
}
