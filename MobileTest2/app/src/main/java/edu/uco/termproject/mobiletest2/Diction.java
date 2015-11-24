package edu.uco.termproject.mobiletest2;

public class Diction {
    private String myImgName;
    private String myAnswer;
    private String myCharacter;

    public Diction(String imgName, String character, String answer){
        myImgName = imgName;
        myAnswer = answer;
        myCharacter = character;
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

    public String getMyCharacter() { return myCharacter; }

    public void setMyCharacter(String myCharacter) { this.myCharacter = myCharacter; }
}
