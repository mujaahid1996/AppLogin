package com.example.sony.myappteam;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.sony.myappteam.mysqlconnection.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Getter {

    Context context;

    //selanjutnya
    String username, password;

    String urlCari;

    public Getter(Context context, String urlCari, Integer type) {
        this.context = context;
        this.urlCari = urlCari;

        switch (type){
            case 0:
                cariLogin(urlCari);
                break;
//            case 1:
//                showUser(urlCari);
//                break;
        }
    }

    public void cariLogin(final  String link){

        class GetUser extends AsyncTask<Void, Void, String>{

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "Logging in...","Harap Tunggu...", false, false);

                //20170523 by Muj
                //atur secara terpisah
//                progressDialog = new ProgressDialog(context);
//                progressDialog.setMessage("Loading...");
//                progressDialog.setIndeterminate(false);
//                progressDialog.setCancelable(true);
//                progressDialog.show();


            }

            @Override
            protected String doInBackground(Void... params) {
                Parser koneksi = new Parser();
                String s = koneksi.kirimRequestGet(link);
                return s;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                showLogin(s);
            }

            private void showLogin(String json) {
                try{

                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray( Link.TAG_JSON_ARRAY );
                    JSONObject o = jsonArray.getJSONObject(0);
                    String name = o.getString( Link.TAG_USER );

                    if( name == "null" ){
                        Toast.makeText(context, "Username atau Password tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }else {
//                        Intent intent = new
                        Toast.makeText(context, "Akun ditemukan ", Toast.LENGTH_SHORT).show();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }


            }

        }

        GetUser ge = new GetUser();
        ge.execute();

    }

}
