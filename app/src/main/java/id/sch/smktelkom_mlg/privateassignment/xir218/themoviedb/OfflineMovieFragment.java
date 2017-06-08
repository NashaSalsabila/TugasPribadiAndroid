package id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.Adapter.MoviesAdapter;
import id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.DB.Movies;
import id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.Model.Movie;



public class OfflineMovieFragment extends Fragment {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    public OfflineMovieFragment() {
        // Required empty public constructor
    }


    public static OfflineMovieFragment newInstance() {
        OfflineMovieFragment fragment = new OfflineMovieFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_offline_movie, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_offline);

        mAdapter = new MoviesAdapter(movieList);

        movieList.clear();
        prepareMovieData();
        
        return v;
    }

    private void prepareMovieData() {
        List<Movies> movies = Movies.listAll(Movies.class);
        if(movies.size() != 0){
            for(int i = 0; i < movies.size(); i++){
                Movies current = movies.get(i);

                movieList.add(new Movie(current.getMovieId(),current.getTitle(),current.getOverview().substring(0,64)+"...",Vars.TMDB_BASEPATH_IMG + current.getImage()));
            }
            setTv();
        }



    }

    private void setTv() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),recyclerView, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie selectedMG = movieList.get(position);
                Intent i = new Intent(getContext(),ItemDetailActivityMovie.class);
                i.putExtra("title",selectedMG.getTitle());
                i.putExtra("id",selectedMG.getId());
                i.putExtra("archive",true);
                startActivity(i);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Movie SelectedMG = movieList.get(position);
                String dataID = SelectedMG.getId();

                List<Movies> selectedMovies = Movies.find(Movies.class,"movie_id = ?",dataID);
                Movies m = selectedMovies.get(0);
                Boolean isDeleted = m.delete();
                if(isDeleted){
                    movieList.remove(position);
                    mAdapter.notifyDataSetChanged();
                    Snackbar.make(view,"Item Deleted",Snackbar.LENGTH_SHORT).show();
                }
            }
        }));
    }


}
