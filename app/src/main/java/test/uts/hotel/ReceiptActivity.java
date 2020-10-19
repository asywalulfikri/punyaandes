package test.uts.hotel;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiptActivity extends AppCompatActivity {


    TextView titleText,subtitleText,descriptionText;
    ImageView imageView;

    String title,subtitle, description;
    int icon ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

       /* title = getIntent().getStringExtra("title");
        subtitle = getIntent().getStringExtra("subtitle");
        description = getIntent().getStringExtra("description");
        icon = getIntent().getIntExtra("icon",0);

        titleText = findViewById(R.id.title);
        subtitleText = findViewById(R.id.subtitle);
        imageView = findViewById(R.id.icon);
        descriptionText = findViewById(R.id.description);

        titleText.setText(title);
        subtitleText.setText(subtitle);
        imageView.setImageResource(icon);
        descriptionText.setText(description);*/
    }
}
