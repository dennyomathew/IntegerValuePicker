package com.dennymathew.sampleintvaluepickerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dennymathew.integervaluepicker.IntegerValuePicker;
import com.dennymathew.integervaluepicker.interfaces.ValueChangeListener;

public class MainActivity extends AppCompatActivity {

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
}
