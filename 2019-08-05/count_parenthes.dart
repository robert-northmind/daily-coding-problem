main() {
  String sample1 = "()())()";
  String sample2 = ")(";
  String sample3 = "()((()))()";

  print("sample1: ${nbrParenToRemove(sample1)}");
  print("sample2: ${nbrParenToRemove(sample2)}");
  print("sample3: ${nbrParenToRemove(sample3)}");
}

int nbrParenToRemove(String str) {
  int passedChars = 0;
  int openCount = 0;

  for (int i = 0; i < str.length; i++) {
    String char = str[i];
    if (char == "(") {
      passedChars += 1;
      openCount += 1;
    } else if (char == ")") {
      if (openCount > 0) {
        passedChars += 1;
        openCount -= 1;
      }
    }
  }
  int nbrParent = str.length - passedChars + openCount;
  return nbrParent;
}
