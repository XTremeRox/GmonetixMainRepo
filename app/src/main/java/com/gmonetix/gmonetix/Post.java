package com.gmonetix.gmonetix;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gmonetix.gmonetix.constant.Const;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

public class Post extends AppCompatActivity {
    TextView title;
    WebView content;
    ProgressDialog progressDialog;
    Gson gson;
    Map<String, Object> mapPost;
    Map<String, Object> mapTitle;
    Map<String, Object> mapContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        final String id = getIntent().getExtras().getString("id");

        title = (TextView) findViewById(R.id.title);
        content = (WebView)findViewById(R.id.content);
        TextView ID = (TextView) findViewById(R.id.id);
        TextView DATE = (TextView) findViewById(R.id.date);
        TextView LINK = (TextView) findViewById(R.id.link);
        TextView SLUG = (TextView) findViewById(R.id.slug);
        TextView AUTHOR = (TextView) findViewById(R.id.author);
        TextView FEATURED_MEDIA = (TextView) findViewById(R.id.featured_media);
        TextView COMMENT_STATUS = (TextView) findViewById(R.id.comment_status);
        TextView CATEGORIES = (TextView) findViewById(R.id.categories);
        ID.setText(id);
        DATE.setText(getIntent().getExtras().getString("date"));
        LINK.setText(getIntent().getExtras().getString("link"));
        SLUG.setText(getIntent().getExtras().getString("slug"));
        AUTHOR.setText(getIntent().getExtras().getString("author"));
        FEATURED_MEDIA.setText(getIntent().getExtras().getString("featured_media"));
        COMMENT_STATUS.setText(getIntent().getExtras().getString("comment_status"));
        CATEGORIES.setText(getIntent().getExtras().getString("categories"));

        progressDialog = new ProgressDialog(Post.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        String url = "http://www.gmonetix.com/wp-json/wp/v2/posts/"+id+"?fields=title,content";

        String mediaUrl = Const.featured_media_url.replace("FEATURED_MEDIA_ID",getIntent().getExtras().getString("featured_media"));
       // String media_url = "http://gmonetix.com/blog/wp-content/uploads/2017/01/Anonymous-Hacker-Charged-with-CyberStalking.jpg";

        RequestQueue rQueue = Volley.newRequestQueue(Post.this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                gson = new Gson();
                mapPost = (Map<String, Object>) gson.fromJson(s, Map.class);
                mapTitle = (Map<String, Object>) mapPost.get("title");
                mapContent = (Map<String, Object>) mapPost.get("content");

                title.setText(mapTitle.get("rendered").toString());
                content.loadData(mapContent.get("rendered").toString(),"text/html","UTF-8");

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();
                Toast.makeText(Post.this, id, Toast.LENGTH_LONG).show();
            }
        });
        rQueue.add(request);

        /*

       ImageRequest imageRequest = new ImageRequest(albumId,new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        },0,0,null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        });


        rQueue.add(imageRequest);   */

    }
}