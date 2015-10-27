package edu.uco.termproject.mobiletest2;

/**
 * Created by Hanye on 10/26/2015.
 */
public class Hiragana {
    private String myImgName;
    private String myAnswer;

    public Hiragana (String hiragana){
        myImgName = hiragana;
        myAnswer = hiragana;
    }

    public String getMyAnswer() {
        return myAnswer;
    }

    public void setMyAnswer(String myAnswer) {
        this.myAnswer = myAnswer;
    }

    public String getMyImgName() {
        return myImgName;
    }

    public void setMyImgName(String myImgName) {
        this.myImgName = myImgName;
    }
}
