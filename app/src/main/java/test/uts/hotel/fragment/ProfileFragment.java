package test.uts.hotel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import test.uts.hotel.LoginActivity;
import test.uts.hotel.Model.User;
import test.uts.hotel.R;
import test.uts.hotel.helper.SharedPreference;

public class ProfileFragment extends Fragment {
    LinearLayout llLogout;
    private SharedPreference sharedPreference;
    private TextView etEmail;
    private User user;
    // Method onCreateView dipanggil saat Fragment harus menampilkan layoutnya      // dengan membuat layout tersebut secara manual lewat objek View atau dengan     // membaca file XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Layout tampilan untuk fragment ini
        return inflater.inflate(R.layout.fragment_profile, parent, false);
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau          // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        etEmail = view.findViewById(R.id.et_email);

        sharedPreference = new SharedPreference();
        user = sharedPreference.getUser(getActivity());
        etEmail.setText(user.getEmail());
        llLogout = view.findViewById(R.id.ll_logout);
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreference.saveLogin(getActivity(),false);
                sharedPreference.clearSharedPreference(getActivity());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}