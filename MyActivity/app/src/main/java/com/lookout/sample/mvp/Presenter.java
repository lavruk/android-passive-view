package com.lookout.sample.mvp;

public class Presenter {

    private final Screen mScreen;
    private int mTimes = 0;

    public Presenter(Screen screen) {
        mScreen = screen;
    }

    public void onCreate() {
        mScreen.setTextForButton("Click the button");
        mScreen.setChangedText("Enter text in the field below");
    }

    public void onButtonClicked() {
        mScreen.setTextForButton("Button clicked: " + (++mTimes));
    }

    public void onTextChanged(String changedText) {
        mScreen.setChangedText("You entered: " + changedText);
    }
}
