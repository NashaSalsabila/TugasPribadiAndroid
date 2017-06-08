package id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.Adapter.MoviesAdapter;
import id.sch.smktelkom_mlg.privateassignment.xir218.themoviedb.Model.Movie;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnlineMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnlineMovieFragment extends Fragment {
    // TODO: Rename and change types of parameters
    private String TYPE; //NOW / UPCOMING.

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    public OnlineMovieFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static OnlineMovieFragment newInstance(String type) {
        OnlineMovieFragment fragment = new OnlineMovieFragment();
        Bundle args = new Bundle();
        args.putString("TYPE", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            TYPE = getArguments().getString("TYPE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_online_movie, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);

        prepareMovieData();

        return v;
    }

    private void prepareMovieData() {
        final String kind = "movie";
        String type = "";
        if(TYPE.equals("NOW")){
            type = "now_playing";
        }else if(TYPE.equals("UPCOMING")){
            type = "upcoming";
        }
        RequestQueue rq = Volley.newRequestQueue(getContext());
        String url = String.format("https://api.themoviedb.org/3/%s/%s?api_key=%s&page=%s&language=en-US",kind,type,Vars.APIKEY,String.valueOf(1));
        //Log.d("??",url);
        JsonObjectRequest jObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jA = response.getJSONArray("results");

                    for(int i = 0; i < jA.length(); i++){
                        JSONObject jO = (JSONObject) jA.get(i);

                        String mvTitle = "", mvPic = "", mvId = "", mvDate = "";
                        if(kind == "movie") {
                            mvTitle = jO.getString("title");
                            mvPic = jO.getString("poster_path");
                            mvId = jO.getString("id");
                            mvDate = jO.getString("release_date");
                        }

                        mvPic = Vars.TMDB_BASEPATH_IMG + mvPic;

                        Log.d("PIC",mvPic);
                        movieList.add(new Movie(mvId,mvTitle,mvDate,mvPic));

                        if(i == jA.length() - 1){
                            // At Least it works btw.
                            setTv();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(jObj);

        mAdapter.notifyDataSetChanged();
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
                startActivity(i);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }


}
