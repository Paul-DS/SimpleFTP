package com.paulds.simpleftp.presentation.model;

import android.databinding.BaseObservable;

/**
 * Model for form fields.
 *
 * @author Paul-DS
 */
public class FieldViewModel<T> extends BaseObservable {
    /**
     * Value of the field.
     */
    private T mValue;

    /**
     * Validation error to display on the field
     */
    private String error;

    /**
     * A value indicating whether the validation must be performed
     * when the field is modified.
     */
    private boolean isValidationEnabled = false;

    /**
     * Constructor with initialization.
     *
     * @param value The initial value.
     */
    public FieldViewModel(T value) {
        mValue = value;
    }

    /**
     * Default constructor
     */
    public FieldViewModel() {
    }

    /**
     * @return The stored value.
     */
    public T get() {
        return mValue;
    }

    /**
     * Set the stored value.
     */
    public void set(T value) {
        if (value != mValue) {
            mValue = value;
            notifyChange();

            if(this.isValidationEnabled) {
                this.onValidate();
            }
        }
    }

    /**
     * Indicates whether the validation must be performed
     * automatically when the field is modified.
     */
    public boolean isValidationEnabled() {
        return this.isValidationEnabled;
    }

    /**
     * Specify whether the validation must be performed
     * automatically when the field is modified.
     */
    public void setValidationEnabled(boolean isValidationEnabled) {
        this.isValidationEnabled = isValidationEnabled;
    }

    /**
     * Gets the validation errors.
     * @return The validation errors.
     */
    public String getError() {
        return this.error;
    }

    /**
     * Sets the validation errors.
     * @param error The validation errors.
     */
    protected void setError(String error) {
        this.error = error;
    }

    /**
     * Validation method (to override)
     */
    protected void onValidate() {

    }

    /**
     * Execute the validation and indicates whether the field is valid.
     * @return The value indicating whether the field is valid.
     */
    public boolean isValid() {
        String oldError = this.error;
        this.onValidate();

        if(this.error != oldError) {
            notifyChange();
        }

        return this.error == null || this.error.isEmpty();
    }
}
