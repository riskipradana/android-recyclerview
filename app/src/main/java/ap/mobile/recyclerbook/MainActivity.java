package ap.mobile.recyclerbook;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Mahasiswa> mahasiswas;
    private BookAdapter bookAdapter;
    private Button btDel;
    private Button btAdd;
    private TextView phoneTexView , emailTextView, nameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mempersiapkan data untuk ditampilkan ke dalam RecyclerView
        Mahasiswa m1 = new Mahasiswa("Si A", "a@email.com", "0811");
        Mahasiswa m2 = new Mahasiswa("Si B", "b@email.com", "0812");
        Mahasiswa m3 = new Mahasiswa("Si C", "c@email.com", "0813");
        Mahasiswa m4 = new Mahasiswa("Si D", "d@email.com", "0814");
        Mahasiswa m5 = new Mahasiswa("Si E", "e@email.com", "0815");

        this.mahasiswas = new ArrayList<>();
        mahasiswas.add(m1);
        mahasiswas.add(m2);
        mahasiswas.add(m3);
        mahasiswas.add(m4);
        mahasiswas.add(m5);

        // ambil elemen RecyclerView dari Layout
        RecyclerView rvBook = (RecyclerView) this.findViewById(R.id.rv_book);

        // buat BookAdapter
        this.bookAdapter = new BookAdapter(this, mahasiswas);

        // set BookAdapter sebagai adapter untuk RecyclerView
        rvBook.setAdapter(bookAdapter);

        // set layout manager yang mengelola tampilan recyclerView
        // dengan: LinearLayoutManager
        rvBook.setLayoutManager(new LinearLayoutManager(this));

        this.btDel = (Button) this.findViewById(R.id.bt_del);
        this.btAdd = (Button) this.findViewById(R.id.bt_add);

        phoneTexView =  (TextView) findViewById(R.id.tv_hp);
        emailTextView =  (TextView) findViewById(R.id.tv_email);
        nameTextView =  (TextView) findViewById(R.id.tv_nama);

        this.btDel.setOnClickListener(this);
        this.btAdd.setOnClickListener(this);

        phoneTexView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:012345"));
                startActivity(callIntent);
            }
        });


        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEmailIntent = new Intent(Intent.ACTION_SENDTO);
                sendEmailIntent.setData(Uri.fromParts("mailto", "riskipradana.rp7@gmail.com", null));
                sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject");
                sendEmailIntent.putExtra(Intent.EXTRA_TEXT, "Email Body");
                startActivity(Intent.createChooser(sendEmailIntent, "Send Email..."));
            }
        });

        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchingNameIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                searchingNameIntent.putExtra(SearchManager.QUERY, "Nama");
                startActivity(searchingNameIntent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:

                // sisipkan data di akhir collection
                this.mahasiswas.add(new Mahasiswa("Si X", "x@email.com", "123"));

                // beritahu Adapter bahwa (collection) datanya berubah
                this.bookAdapter.notifyDataSetChanged();

                break;
        }
    }

}
