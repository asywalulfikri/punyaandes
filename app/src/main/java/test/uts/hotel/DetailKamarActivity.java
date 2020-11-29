package test.uts.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import test.uts.hotel.adapter.MyRecyclerViewAdapter;

public class DetailKamarActivity extends AppCompatActivity {


    TextView titleText,subtitleText,descriptionText;
    ImageView imageView;
    MaterialButton btnSubmit;

    String title,subtitle, description,price;
    int icon ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kamar);

        title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        description = getIntent().getStringExtra("description");
        price = getIntent().getStringExtra("price");
        icon = getIntent().getIntExtra("icon",0);

        titleText = findViewById(R.id.title);
        subtitleText = findViewById(R.id.subtitle);
        imageView = findViewById(R.id.icon);
        descriptionText = findViewById(R.id.description);
        btnSubmit = findViewById(R.id.btnSubmit);

        titleText.setText(title);
        subtitleText.setText(subtitle);
        imageView.setImageResource(icon);
        descriptionText.setText(description);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailKamarActivity.this, DetailOrderActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("subtitle",subtitle);
                intent.putExtra("description",description);
                intent.putExtra("icon",icon);
                intent.putExtra("price",price);
                startActivity(intent);
            }
        });
    }
}
