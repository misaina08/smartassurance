package com.aro.misaina.smartassurance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import utilitaire.Util;

/**
 * Created by Misaina on 18/03/2017.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private TextView textViewToFill;
    private EditText editTextToFill;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Util util = new Util();
        String mois = util.addPrefix(2, month + 1 + "", "0");
        String jour = util.addPrefix(2, day + "", "0");
//        textViewToFill.setText(year + "-" + mois + "-" + jour);
        editTextToFill.setText(jour+"/"+mois+"/"+year);
    }

    public TextView getTextViewToFill() {
        return textViewToFill;
    }

    public void setTextViewToFill(TextView textViewToFill) {
        this.textViewToFill = textViewToFill;
    }

    public EditText getEditTextToFill() {
        return editTextToFill;
    }

    public void setEditTextToFill(EditText editTextToFill) {
        this.editTextToFill = editTextToFill;
    }
}