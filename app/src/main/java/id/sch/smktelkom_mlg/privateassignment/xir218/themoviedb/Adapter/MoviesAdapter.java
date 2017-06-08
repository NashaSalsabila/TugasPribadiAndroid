package id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.Model.Movie;
import id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.R;



public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>{

    private List<Movie> moviesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, synopsis;
        public ImageView picture;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            //synopsis = (TextView) view.findViewById(R.id.synopsis);
            year = (TextView) view.findViewById(R.id.year);
            picture = (ImageView) view.findViewById(R.id.picture);
        }
    }

    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_online_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        //holder.synopsis.setText(movie.getSynopsis());
        holder.year.setText(movie.getYear());

        if(movie.getImage() != null){
            Glide.with(holder.picture.getContext()).load(movie.getImage()).into(holder.picture);
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}
