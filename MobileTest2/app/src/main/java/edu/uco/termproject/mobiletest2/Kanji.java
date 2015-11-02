package edu.uco.termproject.mobiletest2;

public class Kanji {
    private String myImgName;
    private String myAnswer;

    public Kanji (String imgName, String Answer){
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
