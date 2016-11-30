package com.lookout.sample.mvp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PresenterTest {

    @Mock Screen mScreen;
    Presenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPresenter = new Presenter(mScreen);
    }

    @Test
    public void testOnCreate_setsInitialTexts() throws Exception {
        // when:
        mPresenter.onCreate();
        // then:
        Mockito.verify(mScreen).setTextForButton("Click the button");
        Mockito.verify(mScreen).setChangedText("Enter text in the field below");
    }

    @Test
    public void testOnButtonClicked_updatesButtonText() throws Exception {
        // when:
        mPresenter.onButtonClicked();
        // then:
        Mockito.verify(mScreen).setTextForButton("Button clicked: 1");
    }

    @Test
    public void testOnTextChanged_updatesChangedText() throws Exception {
        // setup:
        final String changedText = "TEST";
        // when:
        mPresenter.onTextChanged(changedText);
        // then:
        Mockito.verify(mScreen).setChangedText("You entered: " + changedText);
    }
}