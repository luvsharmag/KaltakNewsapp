package com.example.kaltak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRvadapter.categoryclickinterface {
//853e0efea20447e898c0a1230bf458fc
    private RecyclerView newsRV,categoryyRV;
    private ProgressBar loadingpb;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRvModal> categoryRvModalArrayList;
    private CategoryRvadapter categoryRvadapter;
    private Newsrvadapter newsrvadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRvnews);
        categoryyRV = findViewById(R.id.idRvcategories);
        loadingpb = findViewById(R.id.idpbloading);
        articlesArrayList = new ArrayList<>();
        categoryRvModalArrayList = new ArrayList<>();
        newsrvadapter = new Newsrvadapter(articlesArrayList,this);
        categoryRvadapter = new CategoryRvadapter(categoryRvModalArrayList,this,this::oncategoryclick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsrvadapter);
        categoryyRV.setAdapter(categoryRvadapter);
        getcategories();
        getNews("All");
        newsrvadapter.notifyDataSetChanged();
    }
    private void getcategories(){
        categoryRvModalArrayList.add(new CategoryRvModal("All","https://images.unsplash.com/photo-1529243856184-fd5465488984?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=892&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Science","https://images.unsplash.com/photo-1507413245164-6160d8298b31?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Sports","https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("General","https://images.unsplash.com/photo-1499244571948-7ccddb3583f1?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1032&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Business","https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Entertainment","https://images.unsplash.com/photo-1486572788966-cfd3df1f5b42?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=872&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Health","https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80"));
        categoryRvadapter.notifyDataSetChanged();
    }
    private void getNews(String category){
        loadingpb.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryurl = "https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=853e0efea20447e898c0a1230bf458fc";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=853e0efea20447e898c0a1230bf458fc";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All")){
            call = retrofitAPI.getAllnews(url);
        }else{
            call = retrofitAPI.getNewsByCategory(categoryurl);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                loadingpb.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles();
                for (int i=0;i<articles.size();i++) {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(),
                            articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                }
                newsrvadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail To Get News!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void oncategoryclick(int position) {
        String category = categoryRvModalArrayList.get(position).getCategory();
        getNews(category);
    }
}