package com.example.taskapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.adapters.AnswersAdapter;
import com.example.taskapp.adapters.QuestionsAdapter;
import com.example.taskapp.classes.AnswerModel;
import com.example.taskapp.classes.HttpHandler;
import com.example.taskapp.classes.QuestionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AnswersActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ArrayList<AnswerModel> answers;
    public AnswersAdapter answersAdapter;
    public static Context context;
    public TextView txt;
    String id= "", answre;
    ArrayList<HashMap<String, String>> contactList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_detail);

        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_answers);
        txt = findViewById(R.id.question);
        answers = new ArrayList<AnswerModel>();

        if (getIntent().getStringExtra("question")!=null){
            txt.setText(getIntent().getStringExtra("question"));
        }
        if (getIntent().getStringExtra("que_id")!=null){
            id = getIntent().getStringExtra("que_id");
        }

        getAnswers();

        answersAdapter =new AnswersAdapter(answers);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        //    recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(answersAdapter);

    }

    private void getAnswers()
    {

        new GetAnswersTask().execute("https://api.stackexchange.com/2.0/answers?order=desc&sort=activity&tagged=android&site=stackoverflow");

    }


    private class GetAnswersTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {

            try { HttpHandler sh2 = new HttpHandler();
                // Making a request to url and getting response
                String url2 =params[0];
                String jsonStr2 = sh2.makeServiceCall(url2);

                //  Log.e(TAG, "Response from url: " + jsonStr);
                if (jsonStr2 != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr2);

                        JSONArray contacts = jsonObj.getJSONArray("items");

                        //Log.e("ARRAY", contacts.toString());
                        JSONObject obj = new JSONObject(), objj = new JSONObject();
                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            obj = contacts.getJSONObject(i);

                            for(int j = 0; j < obj.length(); j++) {
                                Log.e("Json obj", obj.getString("owner"));
                                objj = obj.getJSONObject("owner");

                                if (obj.getString("question_id").equals(id)){
                                    answre = objj.getString("link");
                                }




                            }
                            HashMap<String, String> contact = new HashMap<>();

                            contact.put("answer", answre);


                            contactList2.add(contact);

                        }


                    } catch (final JSONException e) {
                        Log.e("Err 1", "Json parsing error: " + e.getMessage().replaceAll("\\s+$", ""));

                    }

                } else {
                    Log.e("Err 2", "Couldn't get json from server.");


                }

            } catch (Exception e) {
                Log.e("Err 3", "jsonTask: "+e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



            //  questions.add(new QuestionModel(1,username,title,img));
            // ArrayList<QuestionModel> singleItem = new ArrayList<QuestionModel>();
            for ( HashMap<String, String> map2 : new ArrayList<>(contactList2)) {
                answers.add(new AnswerModel(map2.get("answer")));
                answersAdapter.notifyDataSetChanged();
            }

        }

    }

}