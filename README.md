# IntegerValuePicker
An android library which implements a simple number picker.

![alt tag](https://i.imgur.com/MItliW2.png)

### Usage

Add IntegerValuePicker Layout to Activity/Fragment Layout
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.dennymathew.sampleintvaluepickerapp.MainActivity">

    <TextView
        android:id="@+id/select_val_tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="116dp"
        android:layout_marginTop="8dp"
        android:text="@string/select_value"
        app:layout_constraintBottom_toTopOf="@id/value_picker"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.311" />

    <TextView
        android:id="@+id/value_tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="128dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:text="@string/value"
        app:layout_constraintBottom_toTopOf="@id/value_picker"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.464"
        app:layout_constraintStart_toEndOf="@+id/select_val_tv"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.311" />

    <com.dennymathew.integervaluepicker.IntegerValuePicker
        android:id="@+id/value_picker"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="324dp"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:maxValue="99"
        app:minValue="1"
        app:value="1">

    </com.dennymathew.integervaluepicker.IntegerValuePicker>

</android.support.constraint.ConstraintLayout>
```

In activity class:
```java
    IntegerValuePicker mValuePicker;
    TextView mValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mValueTextView = findViewById(R.id.value_tv);

        mValuePicker = findViewById(R.id.value_picker);
        mValuePicker.setValueChangeListener(new ValueChangeListener() {
            @Override
            public void onValueChanged(Integer value, IntegerValuePicker.VALUE_CHANGE_DIRECTION changeDirection) {
                mValueTextView.setText(String.valueOf(value));
            }
        });

    }
```


### License 

```
MIT License

Copyright (c) 2017 Denny Oommen Mathew

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
