package com.gmonetix.gmonetix;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.gmonetix.constant.Const;
import com.gmonetix.gmonetix.helper.JsonData;
import com.gmonetix.gmonetix.helper.PostListAdapter;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecentFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<JsonData> posts = new ArrayList<JsonData>();

    String url = Const.url;
    List<Object> list;
    Gson gson;
    ProgressDialog progressDialog;
    Map<String,Object> mapPost;
    Map<String,Object> mapTitle;

    String postTitle[];
    String albumid;
    RequestQueue requestQueue;

    int postId;
    String postDate, postLink, postSlug, postAuthor, postFeaturedMedia, postCommentStatus, postCategories;

    public RecentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.recent_post_list_view, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        recyclerView = (RecyclerView) view.findViewById(R.id.posts_recycler_view);

        new PostLoader().execute();



    /*    for(int i=0;i<posts.size();i++) {
            JsonData jsonData = posts.get(i);
            albumid = jsonData.getFeatured_media().replace(".0", "").trim();
            String album_url = "http://www.gmonetix.com/wp-json/wp/v2/media/" + albumid;
            final int finalI = i;
            StringRequest mediaRequest = new StringRequest(Request.Method.GET, album_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String jsonLine) {
                    JsonElement jelement = new JsonParser().parse(jsonLine);
                    JsonObject jobject = jelement.getAsJsonObject();
                    jobject = jobject.getAsJsonObject("guid");
                    MediaURL[finalI] = jobject.get("rendered").getAsString();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(mediaRequest);
        }

        requestQueue.add(request);
        for(int i=0;i<posts.size();i++) {
            final int finalI = i;
            ImageRequest imageRequest = new ImageRequest(MediaURL[i], new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap bitmap) {
                    JsonData jsonData = new JsonData(postDate , postTitle[finalI],albumid,bitmap);
                    posts.add(jsonData);
                }
            }, 0, 0, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyError.printStackTrace();
                }
            });
        }   */


   /*     postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mapPost = (Map<String,Object>)list.get(position);
                albumid = mapPost.get("featured_media").toString().replace(".0","").trim();
                String album_url = "http://www.gmonetix.com/wp-json/wp/v2/media/"+albumid;
                StringRequest mediaRequest = new StringRequest(Request.Method.GET, album_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String jsonLine) {
                        JsonElement jelement = new JsonParser().parse(jsonLine);
                        JsonObject jobject = jelement.getAsJsonObject();
                        jobject = jobject.getAsJsonObject("guid");
                        postMedia = jobject.get("rendered").getAsString();
                        Map<String, Object> javaRootMapObject = new Gson().fromJson(jsonLine, Map.class);
                     //   postMedia = (((Map)((Map)(javaRootMapObject.get("guid")))).get("rendered")).toString();
                        Intent intent = new Intent(getActivity(),Post.class);
                        intent.putExtra("id", ""+postID);
                        intent.putExtra("date",postMedia);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_LONG).show();
                    }
                });

                requestQueue.add(mediaRequest);
            }
        });  */

        return  view;
    }

    public class PostLoader extends AsyncTask<String,String,ArrayList<JsonData>>{

        @Override
        protected void onPostExecute(ArrayList<JsonData> jsonDatas) {
            super.onPostExecute(jsonDatas);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<JsonData> doInBackground(String... params) {
            requestQueue = Volley.newRequestQueue(getActivity());
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    gson = new Gson();
                    list = (List) gson.fromJson(s, List.class);
                    postTitle = new String[list.size()];

                    for(int i=0;i<list.size();++i){
                        mapPost = (Map<String,Object>)list.get(i);
                        mapTitle = (Map<String, Object>) mapPost.get("title");

                        postLink = (String) mapPost.get("link");
                        postSlug = (String) mapPost.get("slug");
                        postAuthor = mapPost.get("author").toString().replace(".0","").trim();
                        postCommentStatus = (String) mapPost.get("comment_status");
                        postCategories = mapPost.get("categories").toString().replace(".0","").replace("[","").replace("]","").trim();
                        postTitle[i] = (String) mapTitle.get("rendered");
                        postDate = mapPost.get("date").toString().substring(5,10).trim();
                        postFeaturedMedia = mapPost.get("featured_media").toString().replace(".0","").trim();
                        postId = ((Double)mapPost.get("id")).intValue();
                        JsonData jsonData = new JsonData(postId, postDate , postTitle[i],postFeaturedMedia, postLink, postSlug, postAuthor,postCommentStatus, postCategories);
                        posts.add(jsonData);

                    }

                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    adapter = new PostListAdapter(posts,getActivity());
                    recyclerView.setAdapter(adapter);
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(request);
            return posts;
        }
    }

}
