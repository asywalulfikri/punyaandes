package test.uts.hotel.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedOutputStream;
import java.util.Calendar;

import test.uts.hotel.ListkamarActivity;
import test.uts.hotel.R;
import test.uts.hotel.helper.CostumeEditText;

public class HomeFragment extends Fragment {

    MaterialButton btnSumbit;
    CostumeEditText etCheckin,etCheckout;
    Calendar date;
    // Method onCreateView dipanggil saat Fragment harus menampilkan layoutnya      // dengan membuat layout tersebut secara manual lewat objek View atau dengan     // membaca file XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Layout tampilan untuk fragment ini
        return inflater.inflate(R.layout.fragment_home, parent, false);
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau          // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        btnSumbit = view.findViewById(R.id.btnSubmit);
        etCheckin = view.findViewById(R.id.etCheckin);
        etCheckout = view.findViewById(R.id.etCheckout);

        etCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(true);
            }
        });


        etCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker(false);
            }
        });

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListkamarActivity.class);
                startActivity(intent);
            }
        });
    }


    public void showDateTimePicker(final Boolean status){
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);

                //use this date as per your requirement

                if(status){
                    etCheckin.setText(date.getTime().toString());
                }else {
                    etCheckout.setText(date.getTime().toString());
                }

            }
        };
        DatePickerDialog datePickerDialog = new  DatePickerDialog(getActivity(), dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


  /*  public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int
                            monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);
                        //use this date as per your requirement
                    }
                };
    }*/

}