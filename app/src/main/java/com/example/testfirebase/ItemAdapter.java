package com.example.testfirebase;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

public class ItemAdapter extends FirebaseRecyclerAdapter<
        Woord, ItemAdapter.ItemViewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context x;
    public ItemAdapter(@NonNull FirebaseRecyclerOptions<Woord> options, Context context) {
        super(options);

        x = context;

    }

    private void speelAudio(String url, Context c) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }}



    @Override
    protected void onBindViewHolder(@NonNull ItemAdapter.ItemViewholder holder, int position, @NonNull Woord model) {
        holder.tvWoordned.setText(model.getWoordned());
        holder.tvwoordamz.setText(model.getWoordamz());
        Glide.with(x).load(model.getImagepath()).into(holder.ivWoord);

        holder.okbutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Context c = holder.itemView.getContext();
                speelAudio(model.getAudiopath(),c);
            }
        });

    }
    @NonNull
    @Override
    public ItemAdapter.ItemViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.woord_layout, parent, false);
        return new ItemAdapter.ItemViewholder(view);
    }
    public class ItemViewholder extends RecyclerView.ViewHolder {

        TextView tvWoordned, tvwoordamz;
        ImageView ivWoord;
        Button okbutton;

        public ItemViewholder(@NonNull View itemView) {
            super(itemView);
            tvWoordned = itemView.findViewById(R.id.tvWoordned2);
            tvwoordamz = itemView.findViewById(R.id.tvWoordamz2);
            ivWoord = itemView.findViewById(R.id.ivWoord2);
            okbutton = itemView.findViewById(R.id.button);


        }
    }
}