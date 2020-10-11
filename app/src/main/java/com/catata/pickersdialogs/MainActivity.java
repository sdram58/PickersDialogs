package com.catata.pickersdialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /*Método manejador del botón para abrir el DatePicker*/
    public void abrirDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "DatePicker");

    }

    /*Método manejador del botón para abrir el TimePicker*/
    public void showTimeDialog(View view) {
        //Creamos un dialogFragment del tipo TimePickerFragment que hemos definido abajo
        DialogFragment newFragment = new TimePickerFragment();

        //Mostramos el DialogFragment.
        newFragment.show(getFragmentManager(), "TimePicker");
    }


    /*
    * Clase para manejar el DatePicker, implementa on DataSetListener para cuando selccionemos la fecha
    * */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstance){

            /*Esta parte no es necesaria pero es para hacer que por defecto tenga seleccionada
            * la fecha actual
            */
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            /*Creamos un DatePickerDialog, con la fecha actual*/
            DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);

            /*Restamos 10 días a la fecha actual, que será la fecha mínima que se puede seleccionar*/
            c.add(Calendar.DATE, -10);
            dpd.getDatePicker().setMinDate(c.getTimeInMillis());

            /*Sumamos 20 días a la fecha que es 10 días menos que la actual,
             es decir, ahora estamos 10 días posterior a la actual que será la fecha máxima que
             se puede seleccionar*/
            c.add(Calendar.DATE, +20);
            dpd.getDatePicker().setMaxDate(c.getTimeInMillis());

            //Devolvemos el objeto
            return  dpd;

        }


        /*Manejador para cuando el usuario selecciona una fecha, nos da, día mes y año
        * OJO!!. El mes va de 0 a 11*/
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Log.i("DATEPICKER", "Elegido " + day+"-"+month+"-" + year );

        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstance){
            final Calendar c = Calendar.getInstance();

            int hour = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, hour, min,
                    DateFormat.is24HourFormat(getActivity()));



            return  tpd;

        }



        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            Log.i("TIMEPICKER", "Elegido " + hourOfDay+":"+minute );

        }
    }
}