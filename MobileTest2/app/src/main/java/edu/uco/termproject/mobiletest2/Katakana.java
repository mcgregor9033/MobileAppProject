package edu.uco.termproject.mobiletest2;

public class Katakana {
    private String myImgName;
    private String myAnswer;

    public Katakana (String imgName, String Answer){
        myImgName = imgName;
        myAnswer = Answer;
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
