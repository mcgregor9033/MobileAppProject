package edu.uco.termproject.mobiletest2;

/**
 * Created by Will on 11/13/2015.
 */
public class Word {
    private static String englishWordString;
    private static String romajiString;
    private static String kanaString;
    private static String kanjiString;

    Word(){
        this.englishWordString = "";
        this.romajiString = "";
        this.kanaString = "";
        this.kanjiString = "";
    }
    Word(String englishWord, String romaji, String kana, String kanji)
    {
        this.englishWordString = englishWord;
        this.romajiString = romaji;
        this.kanaString = kana;
        this.kanjiString = kanji;
    }
    Word(String englishWord, String romaji, String kana)
    {
        this.englishWordString = englishWord;
        this.romajiString = romaji;
        this.kanaString = kana;
    }

    Word(String englishWord, String romaji)
    {
        this.englishWordString = englishWord;
        this.romajiString = romaji;
    }

    public void setUpWord(String englishWord, String romaji, String kana, String kanji)
    {
        this.englishWordString = englishWord;
        this.romajiString = romaji;
        this.kanaString = kana;
        this.kanjiString = kanji;
    }
    public void setUpWord(String englishWord, String romaji)
    {
        this.englishWordString = englishWord;
        this.romajiString = romaji;
        this.kanaString = "";
        this.kanjiString = "";

    }
    public void setUpEnglishWord(String englishWord)
    {
        this.englishWordString = englishWord;
    }

    public void setUpRomaji(String romaji)
    {
        this.romajiString = romaji;
    }

    public void setUpKana(String kana)
    {
        this.kanaString = kana;
    }
    public void setUpKanji(String kanji)
    {
        this.kanjiString = kanji;
    }

    public String getEnglishWord()
    {
        return this.englishWordString;
    }

    public String getRomaji()
    {
        return this.romajiString;
    }

    public String getKana()
    {
        return this.kanaString;
    }

    public String getKanji()
    {
       return this.kanjiString;
    }

}
