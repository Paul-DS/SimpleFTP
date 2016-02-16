package com.paulds.simpleftp.presentation.binders;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.paulds.simpleftp.presentation.model.FieldViewModel;

import org.w3c.dom.Text;

/**
 * Class used to defined bindings used in forms views.
 *
 * @author Paul-DS
 */
public class FormBindings {
    /**
     * Binding method for EditText
     * @param view The EditText widget.
     * @param observable The field value.
     */
    @BindingAdapter("binding")
    public static void bindEditText(EditText view, final FieldViewModel<String> observable) {
        if (view.getTag() == null) {
            view.setTag(true);

            view.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    observable.set(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        String newValue = observable.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }

        TextInputLayout parentLayout = view.getParent() instanceof TextInputLayout
                ? (TextInputLayout)view.getParent()
                : null;

        String newError = observable.getError();
        String oldError = parentLayout != null
                ? (String)parentLayout.getError()
                : (String)view.getError();

        if (oldError != newError) {
            if(parentLayout != null) {
                parentLayout.setError(newError);
            }
            else {
                view.setError(newError);
            }

        }
    }

    /**
     * Binding method for Switch
     * @param view The Switch widget.
     * @param observable The field value.
     */
    @BindingAdapter("binding")
    public static void bindSwitch(Switch view, final FieldViewModel<Boolean> observable) {
        if (view.getTag() == null) {
            view.setTag(true);
            view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    observable.set(isChecked);
                }
            });
        }

        boolean newValue = observable.get() == null ? false : observable.get().booleanValue();
        if (view.isChecked() != newValue) {
            view.setChecked(newValue);
        }

        String newError = observable.getError();
        if (view.getError() != newError) {
            view.setError(newError);
        }
    }
}
