package com.dennymathew.integervaluepicker.interfaces;

import com.dennymathew.integervaluepicker.IntegerValuePicker;

/**
 * Created by Denny Oommen Mathew on 11/11/2017.
 */

public interface ValueChangeListener {

    void onValueChanged(Integer value, IntegerValuePicker.VALUE_CHANGE_DIRECTION changeDirection);
}
