package test.uts.hotel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import test.uts.hotel.adapter.MyRecyclerViewAdapter;

public class ListkamarActivity extends AppCompatActivity {

    RecyclerView list;

    String[] maintitle ={
            "Kamar Suite 1","Kamar  Ekslusiv 2",
            "Kamar Suite 5","Kamar Luxury",
            "Kamar Suite 2",
    };

    String[] subtitle ={
            "Rp.500.000 / Malam ","Rp.250.000 / Malam",
            "RP.300.000 / Malam","Rp.120.000 / Malam",
            "Rp.600.000 / Malam",
    };


    Integer[] imgid={
            R.drawable.kamar_1,R.drawable.kamar_2,
            R.drawable.kamar_3,R.drawable.kamar_4,
            R.drawable.kamar_5,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kamar);

        String[] description ={getString(R.string.description_kamar_1),getString(R.string.description_kamar_2),
                getString(R.string.description_kamar_3),getString(R.string.description_kamar_4),
                getString(R.string.description_kamar_5),
        };

        list= findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(linearLayoutManager);
        MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter(this, maintitle, subtitle,imgid,description);
        list.setAdapter(adapter);



       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // TODO Auto-generated method stub
                if(position == 0) {
                    //code specific to first list item
                    Toast.makeText(getApplicationContext(),"Place Your First Option Code",Toast.LENGTH_SHORT).show();
                }

                else if(position == 1) {
                    //code specific to 2nd list item
                    Toast.makeText(getApplicationContext(),"Place Your Second Option Code",Toast.LENGTH_SHORT).show();
                }

                else if(position == 2) {

                    Toast.makeText(getApplicationContext(),"Place Your Third Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 3) {

                    Toast.makeText(getApplicationContext(),"Place Your Forth Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 4) {

                    Toast.makeText(getApplicationContext(),"Place Your Fifth Option Code",Toast.LENGTH_SHORT).show();
                }

            }
        });*/
    }
}
