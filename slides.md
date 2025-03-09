---
# You can also start simply with 'default'
theme: seriph
# random image from a curated Unsplash collection by Anthony
# like them? see https://unsplash.com/collections/94734566/slidev
background: https://cover.sli.dev
# some information about your slides (markdown enabled)
title: Welcome to Slidev
info: |
  ## Slidev Starter Template
  Presentation slides for developers.

  Learn more at [Sli.dev](https://sli.dev)
# apply unocss classes to the current slide
class: text-center
# https://sli.dev/features/drawing
drawings:
  persist: false
# slide transition: https://sli.dev/guide/animations.html#slide-transitions
transition: slide-left
# enable MDC Syntax: https://sli.dev/features/mdc
mdc: true
---



## Java で 整列させてみよう

<p text-xl>シェルソート & コレクションの活用例</p>

<div @click="$slidev.nav.next" class="mt-12 py-1" hover:bg="white op-10" text-2xl>
  アトリヱ未來 廣瀬　誠
   <!-- <carbon:arrow-right /> -->
</div>

<!-- <div class="abs-br m-6 text-xl">
  <button @click="$slidev.nav.openInEditor" title="Open in Editor" class="slidev-icon-btn">
    <carbon:edit />
  </button>
  <a href="https://github.com/slidevjs/slidev" target="_blank" class="slidev-icon-btn">
    <carbon:logo-github />
  </a>
</div> -->

<style>
  h2 {
    font-size: 48px;
  }
</style>


<!--
The last comment block of each slide will be treated as slide notes. It will be visible and editable in Presenter Mode along with the slide. [Read more in the docs](https://sli.dev/guide/syntax.html#notes)
-->

---
# transition: fade-out
---

# 整列とは?

### データの集合を一定の規則に従って並べること。<br>並べ替え、ソート(sort)とも言う。
<br>

# 代表的なアルゴリズム

- 📝 **挿入ソート** : 整列済部分に新要素を挿入。 $O(n^2)$
  - 🦪**シェルソート** : 間隔を空け整列するよう挿入ソートを改良。$O(n^{1.25})$
- 🎨 **選択ソート** : 未整列部分から最小要素を選択、整列済部分の末尾に追加。$O(n^2)$
- 🧑‍💻 **交換ソート** : 隣接する要素を比較して、順序が逆なら交換。$O(n^2)$
- 🤹 **マージソート** : データを半分に分割、再帰的に整列済要素を併合。$O(n \log n)
$
- 🎥 **クイックソート** : ピボット要素を基準にデータを分割、再帰的に整列。$O(n \log n)
$
- 📤 **ヒープソート** : ヒープ（二分木）を用いて整列。$O(n \log n)
$
- 🛠 **基数ソート** : 各桁を基にして整列を行うアルゴリズム。$O(nk)$
<br>
<br>

<!-- Read more about [Why Slidev?](https://sli.dev/guide/why) -->

<!--
You can have `style` tag in markdown to override the style for the current page.
Learn more: https://sli.dev/features/slide-scope-style
-->

<style>
h1 {
  background-color: #2B90B6;
  background-image: linear-gradient(45deg, #4EC5D4 10%, #146b8c 20%);
  background-size: 100%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
</style>

---
layout: two-cols
layoutClass: gap-16
---

<style>
h1 {
  background-color: #2B90B6;
  background-image: linear-gradient(45deg, #4EC5D4 10%, #146b8c 20%);
  background-size: 100%;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}
</style>

# 日本人の姓 トップ10

春は新しい出会いの季節です。

| 姓   | ふりがな | 人口         |
|------|----------|--------------|
| 伊藤 | いとう   | 1,045,000    |
| 加藤 | かとう   | &nbsp;&nbsp;&nbsp;867,000      |
| 小林 | こばやし | 1,003,000    |
| 佐藤 | さとう   | 1,813,000    |
| 鈴木 | すずき   | 1,757,000    |
| 高橋 | たかはし | 1,372,000    |
| 田中 | たなか   | 1,302,000    |
| 中村 | なかむら | 1,018,000    |
| 山本 | やまもと | 1,021,000    |
| 渡辺 | わたなべ | 1,035,000    |

<style>
  th, td {
    padding-block: 1px;
    text-align: center;
  }
</style>

::right::

![お花見](./ohanami_sakura_zensen.png)



---
layout: two-cols
layoutClass: gap-16
---

# レコード型

````md magic-move {lines: true}
```java
public record Surname(String kanji, 
                      String furigana, 
                      int headcount) {
}
```
````
<br>

### 利点
 
* 簡潔な構文
* 不変性（イミュータブル）
* 可読性、生産性の向上


::right::

````md magic-move  {lines: true}
```java
public class Surname {
  private String kanji;
  private String furigana;
  private int headcount;

  // コンストラクタ
  public Surname(String kanji, 
                 String furigana, 
                 int headcount) {
    this.kanji = kanji;
    this.furigana = furigana;
    this.headcount = headcount;
  }

  // ゲッターメソッド
  public String getKanji()    { return kanji; }
  public String getFurigana() { return furigana; }
  public int getHeadcount()   { return headcount; }

  // toStringメソッドのオーバーライド
  @Override
  public String toString() {
    return "Surname[kanji=" + kanji + ", 
                    furigana=" + furigana + ", 
                    headcount=" + headcount + "]";
  }
}
```
````

<!-- <<< @/klass.java -->

<!-- <<< @/snippets/external.ts#snippet -->


---
layout: two-cols
layoutClass: gap-16
---

# レコードの表示

````md magic-move {lines: true}
```java
record Surname(String kanji, String furigana, 
               int headcount) {}

// 簡略化された main
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

  // 拡張for文により、各インスタンスを表示
  println("--- 並び替え前 ---");
  for (var s : surnames) { println(s); }
}
```
````

::right::

実行結果

```
--- 並び替え前 ---
Surname[kanji=伊藤, furigana=いとう, headcount=1045000]
Surname[kanji=加藤, furigana=かとう, headcount=867000]
Surname[kanji=小林, furigana=こばやし, headcount=1003000]
Surname[kanji=佐藤, furigana=さとう, headcount=1813000]
Surname[kanji=鈴木, furigana=すずき, headcount=1757000]
Surname[kanji=高橋, furigana=たかはし, headcount=1372000]
Surname[kanji=田中, furigana=たなか, headcount=1302000]
Surname[kanji=中村, furigana=なかむら, headcount=1018000]
Surname[kanji=山本, furigana=やまもと, headcount=1021000]
Surname[kanji=渡辺, furigana=わたなべ, headcount=1035000]
```

---
layout: two-cols
layoutClass: gap-16
---

# シェルソート

> シェルソートは、ドナルド・シェルが開発した整列アルゴリズム。挿入ソートの「ほとんど整列されたデータに対しては高速」という長所を活かし、配列の中である程度間隔が離れた要素の組ごとに挿入ソートを行い、間隔を小さくしながら整列を繰り返すことで高速化を図るものである。(引用元: Wikipedia)

<div style="margin-inline: 100px;">

![shellsort](./Sorting_shellsort_anim.gif)

<p>シェルソートのイメージ図</p>
</div>

::right::

### 実装例

````md magic-move {lines: true}
```java
  // シェルソート用の間隔hを決定する
  int n = surnames.length;  int h = 13;
  while (h < n) { h = 3 * h - 1; }  h /= 9;


  while (h > 0) {
    // 降順に並び替え
    for (int i = h; i < n; i++) {
      var s = surnames[i];
      int j = i - h;
      while(j >= 0 && 
            surnames[j].headcount < s.headcount) {
        surnames[j + h] = surnames[j];
        j -= h;
      }
      surnames[j + h] = s;
    }
    h /= 3;
  }
```
````

`h = 1` に固定すれば、挿入ソートと同一になる。

---
layout: two-cols
layoutClass: gap-16
---


# シェルソート

### 実行結果

```
--- 並び替え後 ---
Surname[kanji=佐藤, furigana=さとう, headcount=1813000]
Surname[kanji=鈴木, furigana=すずき, headcount=1757000]
Surname[kanji=高橋, furigana=たかはし, headcount=1372000]
Surname[kanji=田中, furigana=たなか, headcount=1302000]
Surname[kanji=伊藤, furigana=いとう, headcount=1045000]
Surname[kanji=渡辺, furigana=わたなべ, headcount=1035000]
Surname[kanji=山本, furigana=やまもと, headcount=1021000]
Surname[kanji=中村, furigana=なかむら, headcount=1018000]
Surname[kanji=小林, furigana=こばやし, headcount=1003000]
Surname[kanji=加藤, furigana=かとう, headcount=867000]
```

---
layout: two-cols
layoutClass: gap-16
---

# コレクションの利用

> オブジェクトの集まりを扱うための仕組み。<br>データの格納、検索、操作を効率的に行えるよう `List / Map / Set`などが提供されている。

<br>

### ArrayList

* 要素の追加や削除で自動的にサイズが変更される。
* 特定の位置にある要素を簡単に取得できる。
* 同じ要素を複数回追加することができる。
* 要素の追加や削除、検索、整列メソッドがある。


::right::

````md magic-move {lines: true}
```java
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
          new Surname("吉田", "よしだ", 807_000)));
// 降順で並び替え
list.sort(
  Comparator.comparingInt(Surname::headcount).reversed());

// 並び替え結果を表示
println(list);
```
````

---
layout: two-cols
layoutClass: gap-16
---

# コレクションの利用

### 実行結果

```
[Surname[kanji=吉田, furigana=よしだ, headcount=807000], 
 Surname[kanji=山田, furigana=やまだ, headcount=793000], 
 Surname[kanji=佐々木, furigana=ささき, headcount=650000], 
 Surname[kanji=山口, furigana=やまぐち, headcount=627000], 
 Surname[kanji=松本, furigana=まつもと, headcount=611000], 
 Surname[kanji=井上, furigana=いのうえ, headcount=599000], 
 Surname[kanji=木村, furigana=きむら, headcount=560000], 
 Surname[kanji=林, furigana=はやし, headcount=532000], 
 Surname[kanji=斎藤, furigana=さいとう, headcount=528000], 
 Surname[kanji=清水, furigana=しみず, headcount=520000]]
```



---
layout: center
class: text-center
---

# ご清聴、ありがとうございました。

<!-- [Documentation](https://sli.dev) · [GitHub](https://github.com/slidevjs/slidev) · [Showcases](https://sli.dev/resources/showcases) -->

<PoweredBySlidev mt-10 />
