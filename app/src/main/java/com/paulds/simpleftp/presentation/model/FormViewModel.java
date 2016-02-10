package com.paulds.simpleftp.presentation.model;

import android.databinding.BaseObservable;

import java.lang.reflect.Field;

/**
 * Model for form views.
 *
 * @author Paul-DS
 */
public class FormViewModel extends BaseObservable {
    /**
     * Validate all the fields of the model.
     * @return The validation state.
     */
    public boolean validate() {
        boolean result = true;

        for (Field field : this.getClass().getDeclaredFields()) {
            if (FieldViewModel.class.isAssignableFrom(field.getType())) {
                FieldViewModel viewModel = null;
                try {
                    viewModel = (FieldViewModel)field.get(this);

                    viewModel.setValidationEnabled(true);

                    if(!viewModel.isValid()) {
                        result = false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
