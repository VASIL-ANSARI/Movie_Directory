package com.example.moviedirectory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MobileRecyclerViewAdapter extends RecyclerView.Adapter<MobileRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public MobileRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context = context;
        movieList = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie=movieList.get(position);
        String posterLink=movie.getPoster();

        holder.title.setText(movie.getTitle());
        holder.type.setText(movie.getMovieType());


        Picasso.get().load(posterLink).placeholder(android.R.drawable.ic_btn_speak_now).
                into(holder.poster);

        holder.year.setText(movie.getYear());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
        //return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView poster;
        TextView year;
        TextView type;
        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);
            context=ctx;

            title = (TextView) itemView.findViewById(R.id.movieTitleID);
            poster = (ImageView) itemView.findViewById(R.id.movieImageID);
            year = (TextView) itemView.findViewById(R.id.movieReleaseID);
            type = (TextView) itemView.findViewById(R.id.movieCatID);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Movie movie = movieList.get(getAdapterPosition());

                    Intent intent = new Intent(context, MovieDetail.class);

                    intent.putExtra("movie", movie);
                    ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {
        }
    }
}
