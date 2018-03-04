package com.dennymathew.integervaluepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dennymathew.integervaluepicker.interfaces.ValueChangeListener;


/**
 * Created by Denny Oommen Mathew on 11/8/2017.
 */

public class IntegerValuePicker extends ConstraintLayout {

    EditText mInputValue;
    ImageButton mAddButton;
    ImageButton mSubButton;

    private Integer value;
    private boolean isWholeNumberPicker = true;

    private boolean readOnly = false;
    private boolean enabled = true;
    private Integer minValue;
    private Integer maxValue;

    private String controlColor;

    private static final Integer DEFAULT_PICKER_VALUE = 0;
    private static final Integer DEFAULT_PICKER_MIN_VALUE = Integer.MIN_VALUE;
    private static final Integer DEFAULT_PICKER_MAX_VALUE = Integer.MAX_VALUE;


    private ValueChangeListener mValueChangeListener;

    private static final String KEY_SUPER_STATE = "KEY_SUPER_STATE";
    private static final String KEY_PICKER_VALUE = "KEY_PICKER_VALUE";

    public enum VALUE_CHANGE_DIRECTION {
        NEGATIVE,
        POSITIVE
    }

    public IntegerValuePicker(Context context) {
        this(context, null);
    }

    public IntegerValuePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    /**
     * Control Initialization function
     */
    private void init() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        layoutInflater.inflate(R.layout.value_picker, this);

        mSubButton = findViewById(R.id.sub_btn);
        mInputValue = findViewById(R.id.value_etv);
        mAddButton = findViewById(R.id.add_btn);

        setupView();
    }


    private void setupView() {
        setReadOnly(readOnly);

        setEnabled(enabled);

        if(controlColor != null) {
            setControlColor(controlColor);
        }

        mInputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    value = Integer.parseInt(s.toString());

                    //Notify the client.
                    if(mValueChangeListener != null) {
                        mValueChangeListener.onValueChanged(value, VALUE_CHANGE_DIRECTION.NEGATIVE);
                    }
                } catch (NumberFormatException e) {

                }
            }
        });

        mSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyInput();
                if(value > minValue || !isWholeNumberPicker) {
                    --value;
                }
                updateUI();

                //Notify the client.
                if(mValueChangeListener != null) {
                    mValueChangeListener.onValueChanged(value, VALUE_CHANGE_DIRECTION.NEGATIVE);
                }
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyInput();
                if(value < maxValue) {
                    ++value;
                }
                updateUI();

                //Notify the client.
                if(mValueChangeListener != null) {
                    mValueChangeListener.onValueChanged(value, VALUE_CHANGE_DIRECTION.POSITIVE);
                }
            }
        });
    }

    /**
     * Control Initialization through XML
     * @param attrs
     */
    private void init(AttributeSet attrs) {

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.IntegerValuePicker);

        isWholeNumberPicker = array.getBoolean(R.styleable.IntegerValuePicker_wholeNumbersOnly, true);

        if(isWholeNumberPicker) {
            minValue = array.getInteger(R.styleable.IntegerValuePicker_minValue, DEFAULT_PICKER_VALUE);
        } else {
            minValue = array.getInteger(R.styleable.IntegerValuePicker_minValue, DEFAULT_PICKER_MIN_VALUE);
        }
        value = array.getInteger(R.styleable.IntegerValuePicker_value, minValue);

        maxValue = array.getInteger(R.styleable.IntegerValuePicker_maxValue, DEFAULT_PICKER_MAX_VALUE);

        readOnly = array.getBoolean(R.styleable.IntegerValuePicker_readOnly, false);

        enabled = array.getBoolean(R.styleable.IntegerValuePicker_enabled, true);

        controlColor = array.getString(R.styleable.IntegerValuePicker_controlColor);

        array.recycle();

        init();

        updateUI();

    }


    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState());
        bundle.putInt(KEY_PICKER_VALUE, value);
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            value = bundle.getInt(KEY_PICKER_VALUE);
            super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER_STATE));
        } else {
            super.onRestoreInstanceState(state);
        }

        updateUI();
    }

    private void verifyInput() {
        if(value > maxValue) {
            value = maxValue;
        }
        if(value < minValue) {
            value = minValue;
        }
    }

    /**
     * UI Update Helper function
     */
    private void updateUI() {
        mInputValue.setText(String.valueOf(value));
    }

    /**
     * Value Change Listener Interface.
     *
     * Client needs to implement this interface.
     *
     * @param valueChangeListener
     */
    public void setValueChangeListener(ValueChangeListener valueChangeListener) {
        mValueChangeListener = valueChangeListener;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer val) {
        value = val;
        updateUI();
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        mInputValue.setEnabled(!readOnly);
        this.readOnly = readOnly;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if(enabled) {
            mAddButton.setEnabled(true);
            mSubButton.setEnabled(true);
            mInputValue.setEnabled(true);
        } else {
            mAddButton.setEnabled(false);
            mSubButton.setEnabled(false);
            mInputValue.setEnabled(false);
        }
        this.enabled = enabled;
    }

    public String getControlColor() {
        return controlColor;
    }

    public void setControlColor(String color) {
        mSubButton.setColorFilter(Color.parseColor(color));
        mAddButton.setColorFilter(Color.parseColor(color));
        this.controlColor = color;
    }

}