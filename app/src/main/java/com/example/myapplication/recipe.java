package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

public class recipe extends AppCompatActivity {

    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recipe);
        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_UP)) {
                    // 검색 실행하는 부분

                    CallAPI postReq = new CallAPI(new CallAPI.AsyncResponse() {
                        @Override
                        public void processFinish(String res) {
                            try {

                                JSONObject obj = new JSONObject(res);
                                JSONArray recipes_arr = obj.getJSONArray("result");
                                if (recipes_arr.length() == 0) {
                                    showRecipe(null);
                                }
                                RecipeData[] recipes = new RecipeData[recipes_arr.length()];

                                for (int i = 0; i < recipes_arr.length(); i++) {
                                    JSONObject recipeobj = recipes_arr.getJSONObject(i);
                                    RecipeData recipe = new RecipeData();

                                    recipe.setTitle(recipeobj.getString("name"));
                                    recipe.setURL(recipeobj.getString("img"));

                                    JSONArray desc_arr = recipeobj.getJSONArray("making");
                                    String[] description = new String[desc_arr.length()];
                                    for (int j = 0; j < desc_arr.length(); j++) {
                                        description[j] = desc_arr.getString(j);
                                    }
                                    recipe.setDescription(description);

                                    JSONArray ingred_arr = recipeobj.getJSONArray("ing");
                                    String[] ingreds = new String[ingred_arr.length()];
                                    for (int j = 0; j < ingred_arr.length(); j++) {
                                        ingreds[j] = ingred_arr.getString(j);
                                    }
                                    recipe.setIngreds(ingreds);

                                    int id = recipeobj.getInt("id");
                                    Log.d("id", id+"");
                                    recipe.setID(id);

                                    Log.d("recipe", recipe.toString());
                                    recipes[i] = recipe;

                                }
                                showRecipe(recipes);
                            } catch (Exception e) {

                            }
                        }});
                    Log.d("posting data", searchBar.getText().toString());
                    postReq.execute("http://heavyrisem.kro.kr/search/recipe_search", "list=" + searchBar.getText().toString());
//                    RecipeData tmpdata = new RecipeData();
//                    tmpdata.setTitle((String)searchBar.getText().toString());
//                    tmpdata.setDescription("tmpData Recipe Description");
//                    printRecipe(tmpdata);

                    return true;
                }
                return false;
            }
        });

        CallAPI postReq = new CallAPI(new CallAPI.AsyncResponse() {
            @Override
            public void processFinish(String res) {
                try {

                    JSONObject obj = new JSONObject(res);
                    JSONArray recipes_arr = obj.getJSONArray("result");
                    RecipeData[] recipes = new RecipeData[recipes_arr.length()];

                    for (int i = 0; i < recipes_arr.length(); i++) {
                        JSONObject recipeobj = recipes_arr.getJSONObject(i);
                        RecipeData recipe = new RecipeData();

                        recipe.setTitle(recipeobj.getString("name"));
                        recipe.setURL(recipeobj.getString("img"));

                        JSONArray desc_arr = recipeobj.getJSONArray("making");
                        String[] description = new String[desc_arr.length()];
                        for (int j = 0; j < desc_arr.length(); j++) {
                            description[j] = desc_arr.getString(j);
                        }
                        recipe.setDescription(description);

                        JSONArray ingred_arr = recipeobj.getJSONArray("ing");
                        String[] ingreds = new String[ingred_arr.length()];
                        for (int j = 0; j < ingred_arr.length(); j++) {
                            ingreds[j] = ingred_arr.getString(j);
                        }
                        recipe.setIngreds(ingreds);

                        int id = recipeobj.getInt("id");
                        Log.d("id", id+"");
                        recipe.setID(id);

                        Log.d("recipe", recipe.toString());
                        recipes[i] = recipe;

                    }
                    showRecipe(recipes);
                } catch (Exception e) {

                }
            }});
        postReq.execute("http://iinsu.kro.kr:3002/recipe/getall", "");

    }

//    public RecipeData[] getAllRecipe() {
//
//
//
//    }

    protected void showRecipe(RecipeData[] recipe) {
        LayoutInflater line_inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < recipe.length; i++) {
            Log.d("URL", recipe[i].getImgURL());
            if ((i % 2) == 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.activity_recipe_card, (ViewGroup) findViewById(R.id.recipelist_line1), true);
                ImageView imageView = view.findViewById(R.id.recipe_image);
                Glide.with(this).load(recipe[i].getImgURL()).into((imageView));
                imageView.setId(i);

//                view.findViewById(R.id.recipelist_line1).getVi
                TextView title = view.findViewById(R.id.recipe_title);
                TextView ingreds = view.findViewById(R.id.recipe_ingreds);
                TextView making = view.findViewById(R.id.recipe_making);
                title.setText(recipe[i].getTitle());
                ingreds.setText(recipe[i].getStringIngreds());
                making.setText(recipe[i].getStringDescription());
                title.setId(i);
                ingreds.setId(i);
                making.setId(i);
            } else {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.activity_recipe_card, (ViewGroup) findViewById(R.id.recipelist_line2), true);
                ImageView imageView = view.findViewById(R.id.recipe_image);
                Glide.with(this).load(recipe[i].getImgURL()).into((imageView));
                imageView.setId(i);

                TextView title = view.findViewById(R.id.recipe_title);
                TextView ingreds = view.findViewById(R.id.recipe_ingreds);
                TextView making = view.findViewById(R.id.recipe_making);
                title.setText(recipe[i].getTitle());
                ingreds.setText(recipe[i].getStringIngreds());
                making.setText(recipe[i].getStringDescription());
                title.setId(i);
                ingreds.setId(i);
                making.setId(i);
            }

        }
    }


//    protected RecipeData[] requestAll() {
//
//    }

}