
package com.abhi.moviemania.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.abhi.moviemania.views.MainActivity;
import com.abhi.moviemania.R;
import com.abhi.moviemania.models.MoviesResult;
import com.abhi.moviemania.picasso.RoundedTransformation;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerMovie extends RecyclerView.Adapter<RecyclerMovie.MyViewHolder> {


    private List<MoviesResult> mMovieList;


    public interface ListItemClickListener {

    }


    public RecyclerMovie(List<MoviesResult> movieList) {
        mMovieList = movieList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MoviesResult movie = mMovieList.get(position);
        //holder.itemView.setTag(movie.getId());
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).transform(new RoundedTransformation(14, 0)).into(holder.img_movie);
        holder.title.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView img_movie;
        @BindView(R.id.title)
        TextView title;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //itemView.setOnClickListener(this);

        }



    }
}
