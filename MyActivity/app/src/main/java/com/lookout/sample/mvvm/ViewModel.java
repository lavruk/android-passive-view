package com.lookout.sample.mvvm;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class ViewModel {

    PublishSubject<String> mChangedTextSubject = PublishSubject.create();
    BehaviorSubject<Integer> mButtonClickedSubject = BehaviorSubject.create(0);

    public Observable<String> observeButtonText() {
        return mButtonClickedSubject.asObservable()
            .map(times -> {
                if (times == 0) {
                    return "Click the button";
                } else {
                    return "Button clicked: " + times;
                }
            });
    }

    public Observable<String> observeEditedText() {
        return mChangedTextSubject.asObservable()
            .map(text -> "You entered: " + text)
            .startWith("Enter text in the field below");
    }

    public void onTextChanged(String changedText) {
        mChangedTextSubject.onNext(changedText);
    }

    public void onButtonClicked() {
        mButtonClickedSubject.onNext(mButtonClickedSubject.getValue() + 1);
    }
}
