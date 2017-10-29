package ap.mobile.recyclerbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aryo on 9/10/17.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.RowHolder>
{

    private ArrayList<Mahasiswa> mahasiswas;
    private LayoutInflater inflater;

    public BookAdapter(Context context, ArrayList<Mahasiswa> mahasiswas) {
        this.inflater = LayoutInflater.from(context);
        this.mahasiswas = mahasiswas;
    }


    @Override
    public BookAdapter.RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // meng-inflate View sebagai holder dari XML
        View holder = this.inflater.inflate(R.layout.book_item,
                parent, false);

        return new RowHolder(holder);
    }

    @Override
    public void onBindViewHolder(BookAdapter.RowHolder holder, int position) {

        Mahasiswa m = this.mahasiswas.get(position);

        // mapping
        holder.tvNama.setText(m.nama);
        holder.tvEmail.setText(m.email);
        holder.tvHp.setText(m.hp);

        // ketika melakukan mapping view holder dengan elemen data tunggal
        // simpan index-nya untuk digunakan di lain waktu
        holder.position = position;

    }

    @Override
    public int getItemCount() {
        return this.mahasiswas.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvHp;
        private TextView tvEmail;
        private TextView tvNama;
        public int position;

        public RowHolder(View itemView) {

            super(itemView);

            this.tvNama = (TextView) itemView.findViewById(R.id.tv_nama);
            this.tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
            this.tvHp = (TextView) itemView.findViewById(R.id.tv_hp);

            // jika textView nama di-klik
            this.tvNama.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.tv_nama:
                    // jika textView nama di-klik
                    // hapus mahasiswa yang diklik
                    mahasiswas.remove(this.position);

                    // beritahu adapter (diri sendiri)
                    // bahwa datanya sudah berubah
                    notifyDataSetChanged();

                    break;
            }

        }
    }
}
