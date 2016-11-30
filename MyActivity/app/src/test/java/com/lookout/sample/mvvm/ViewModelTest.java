package com.lookout.sample.mvvm;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import rx.observers.TestSubscriber;

public class ViewModelTest {

    private ViewModel mViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mViewModel = new ViewModel();
    }

    @Test
    public void testObserveButtonText_initialValue() throws Exception {
        // setup:
        final TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        // when:
        mViewModel.observeButtonText().subscribe(testSubscriber);
        // then:
        testSubscriber.assertValue("Click the button");
    }

    @Test
    public void testObserveButtonText_updatesAfterClick() throws Exception {
        // setup:
        final TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        // when:
        mViewModel.observeButtonText().subscribe(testSubscriber);
        mViewModel.onButtonClicked();
        mViewModel.onButtonClicked();
        // then:
        testSubscriber.getOnNextEvents().contains("Button clicked: 1");
        testSubscriber.getOnNextEvents().contains("Button clicked: 2");
    }

    @Test
    public void testObserveEditedText_providesInitialValueOnSubscribe() throws Exception {
        // setup:
        final TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        // when:
        mViewModel.observeEditedText().subscribe(testSubscriber);
        // then:
        testSubscriber.assertValue("Enter text in the field below");
    }

    @Test
    public void testObserveEditedText_updatesText() throws Exception {
        // setup:
        final TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        // when:
        mViewModel.observeEditedText().subscribe(testSubscriber);
        mViewModel.onTextChanged("TEST");
        // then:
        testSubscriber.getOnNextEvents().contains("You entered: TEST");
    }
}