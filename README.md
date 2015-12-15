# Passive View in Android (variation on MVP)
## Motivation
http://martinfowler.com/eaaDev/PassiveScreen.html
http://codebetter.com/jeremymiller/2007/05/31/build-your-own-cab-part-4-the-passive-view/

## Basic Structure
The main elements of our Passive View implementation in Android are:
* Activity (the view) is the only element that references Android's display objects (Android views, resource IDs, adapters, etc).
* Screen, implemented by the Activity, is the View interface.
* Presenter implements the business logic controlling the view.
* Model is the rest of the application logic.

## Activity
The goal of the Passive View pattern is to minimize the amount of logic implemented in the view (and thus not covered by unit tests), therefore the logic implemented here should be as minimal as possible.  

Guidelines for implementing the activity:
* All events generated by the display should be delegated to the presenter to handle.  These include: create, destroy, stop,  resume, button listener events, text listener events, any widget listener events.  
* Before delegating the create() event to the presenter, finish initializing the activity if needed: inflate the layout, initialize member variables, etc.  The call to the presenter should be the last call in the 
* Any logic which requires branches (if statements, loops, etc) should be delegated to the presenter.
* Encapsulate any display object types with simple java types (interfaces, enums, wrapper classes).
* Keep animation logic in the activity.  
  * Animations have a very wide, builder-style interface that is difficult to wrap with a screen interface.
  * Correctness of animations needs to be verified by visual inspection, which does not lend itself well to unit testing.

## Presenter
* The presenter should only be invoked by the Activity, therefore its only public methods should be event handlers for the view.
* Name event handler methods to describe the event being handled.  E.g.: onScreenCreated(), onButtonAPressed(), onEmailTextEdited(), etc.
* Keep one-to-one mapping between events in the activity and the event handler methods in the presenter. 
* To close the activity, declare finish() method on the screen interface.  It is already implemented by the Activity base class.
* To start another activity.  Declare a navigateTo...() method on the screen and call it.

## Antipatterns
* DO NOT write logic where the activity retrieves data from the presenter:  
```java
public class MyActivity extends Activity implements MyScreen {
  public void onCreate() {
    presenter.onScreenCreated();
    findView(R.id.title_text).setText(presenter.getTitle());
  }
}

public class MyPresenter {
  public String getTitle() {
    if (spring) return "daffodils"
    if (fall) return "leaves"
  }
}
```
Instead DO:
```java
public class MyActivity extends Activity implements MyScreen { 
  public void onCreate() {
    presenter.onScreenCreated();
  }  
    
  @Override 
  public void setText(String title)
    findView(R.id.title_text).setText(title);
  }
}

public class MyPresenter {
  public void onScreenCreated() {
     screen.setTitle(getTitle());
  }
  private String getTitle() {
    if (spring) return "daffodils"
    if (fall) return "leaves"
  }
}
```
In general, public presenter method should not have return values unless handling a display event requries a result to be returned synchronously.

* DO NOT call the same presenter method from multiple places in the activity:
```
public class MyActivity extends Activity implements MyScreen {
  public void onCreated() {
    findById(R.id.stopButton).addListener(new OnButtonPressed() { 
      public void onPressed() {
        presenter.onStop();
      }
    })
    presenter.onScreenCreated();
  }
  public void onDestroyed() {
    presnter.onStop();
  }
}

public class MyPresenter {
  public void onStop() {
     model.stopDoingStuff();
  }
}
```
Instead DO:
```
public class MyActivity extends Activity implements MyScreen {
  public void onCreated() {
    findById(R.id.stopButton).addListener(new OnButtonPressed() { 
      public void onPressed() {
        presenter.onStopButtonPressed();
      }
    })
    presenter.onScreenCreated();
  }
  public void onDestroyed() {
    presnter.onScreenDestroyed();
  }
}

public class MyPresenter {
  public void onStopButtonPressed() {
    stop();
  }
  public void onScreenDestroyed() {
    stop();
  }
  
  private void stop() {
    model.stopDoingStuff();
  }
}
```