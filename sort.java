record Surname(String kanji, String furigana, int headcount) {
}

void main() {
  // Surname型のインスタンスを生成
  Surname[] surnames = {
    new Surname("伊藤", "いとう", 1_045_000),
    new Surname("加藤", "かとう", 867_000),
    new Surname("小林", "こばやし", 1_003_000),
    new Surname("佐藤", "さとう", 1_813_000),
    new Surname("鈴木", "すずき", 1_757_000),
    new Surname("高橋", "たかはし", 1_372_000),
    new Surname("田中", "たなか", 1_302_000),
    new Surname("中村", "なかむら", 1_018_000),
    new Surname("山本", "やまもと", 1_021_000),
    new Surname("渡辺", "わたなべ", 1_035_000)
  };

  // 各インスタンスを表示
  println("--- 並び替え前 ---");
  for (var s : surnames) { println(s); }

  // シェルソート用の間隔hを決定する
  int n = surnames.length;  int h = 13;
  while (h < n) { h = 3 * h - 1; }  h /= 9;


  while (h > 0) {
    // 降順に並び替え
    for (int i = h; i < n; i++) {
      var s = surnames[i];
      int j = i - h;
      while(j >= 0 && surnames[j].headcount < s.headcount) {
        surnames[j + h] = surnames[j];
        j -= h;
      }
      surnames[j + h] = s;
    }
    h /= 3;
  }

  println("\n--- 並び替え後 ---");
  for (var s : surnames) { println(s); }

  // ArrayListの利用
  var list = new ArrayList<>(Arrays.asList(
                new Surname("井上", "いのうえ", 599_000),
                new Surname("木村", "きむら", 560_000),
                new Surname("斎藤", "さいとう", 528_000),
                new Surname("佐々木", "ささき", 650_000),
                new Surname("清水", "しみず", 520_000),
                new Surname("林", "はやし", 532_000),
                new Surname("松本", "まつもと", 611_000),
                new Surname("山口", "やまぐち", 627_000),
                new Surname("山田", "やまだ", 793_000),
                new Surname("吉田", "よしだ", 807_000)
            ));

  // 降順で並び替え
  list.sort(Comparator.comparingInt(Surname::headcount).reversed());

  // 並び替え結果を表示
  println(list);
}