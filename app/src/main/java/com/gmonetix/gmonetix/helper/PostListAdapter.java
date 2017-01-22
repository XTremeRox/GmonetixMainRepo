package com.gmonetix.gmonetix.helper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmonetix.gmonetix.Post;
import com.gmonetix.gmonetix.R;
import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    ArrayList<JsonData> posts = new ArrayList<JsonData>();
    Context context;

    public PostListAdapter(ArrayList<JsonData> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_sample_card_view,parent,false);
        PostViewHolder postViewHolder = new PostViewHolder(view,context,posts);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        JsonData jsonData = posts.get(position);
        holder.postTitle.setText(jsonData.getTitle());
        holder.postDate.setText(jsonData.getDate());
      //  holder.featuredImageView.setImageBitmap(jsonData.getBitmap());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ArrayList<JsonData> posts = new ArrayList<JsonData>();
        Context context;
        ImageView featuredImageView;
        TextView postTitle, postDate;

        public PostViewHolder(View itemView,Context context, ArrayList<JsonData> posts) {
            super(itemView);
            this.posts = posts;
            this.context = context;
            itemView.setOnClickListener(this);
            featuredImageView = (ImageView) itemView.findViewById(R.id.post_featured_image);
            postTitle = (TextView) itemView.findViewById(R.id.post_title);
            postDate = (TextView) itemView.findViewById(R.id.post_date);
        }

        @Override
        public void onClick(View v) {

            int position= getAdapterPosition();
            JsonData jsonData = this.posts.get(position);
            Intent intent = new Intent(this.context, Post.class);
            intent.putExtra("id", ""+jsonData.getId());
            intent.putExtra("featured_media", ""+jsonData.getFeatured_media());
            intent.putExtra("date", ""+jsonData.getDate());
            intent.putExtra("link", ""+jsonData.getLink());
            intent.putExtra("slug", ""+jsonData.getSlug());
            intent.putExtra("author", ""+jsonData.getAuthor());
            intent.putExtra("comment_status", ""+jsonData.getComment_status());
            intent.putExtra("categories", ""+jsonData.getCategories());
            this.context.startActivity(intent);

        }
    }

}
