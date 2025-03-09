public class Surname {
  private String kanji;
  private String furigana;
  private int headcount;

  // コンストラクタ
  public Surname(String kanji, String furigana, int headcount) {
    this.kanji = kanji;
    this.furigana = furigana;
    this.headcount = headcount;
  }

  // ゲッターメソッド
  public String getKanji() {
    return kanji;
  }

  public String getFurigana() {
    return furigana;
  }

  public int getHeadcount() {
    return headcount;
  }

  // toStringメソッドのオーバーライド
  @Override
  public String toString() {
    return "Surname[kanji=" + kanji + ", 
                    furigana=" + furigana + ", 
                    headcount=" + headcount + "]";
  }
}