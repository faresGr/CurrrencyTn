package com.example.fares.tp01;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.json.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public static String apikey="d4c2092604148d2c0cabaee47897afbf";
    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        /*TextView textView=findViewById(R.id.textView2
        textView.setText("hellloo ");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        try {


            String json=IOUtils.toString(new URL("http://www.apilayer.net/api/live?access_key="+apikey+"&source=TND"));
            textView.setText("changedddd");

           Gson gson = new Gson();

            Example example=gson.fromJson(json, Example.class);

            Quotes quotes=example.getQuotes();
            textView.setText(quotes.getUSDEUR().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        final EditText editText=findViewById(R.id.editText3);
        final TextView textView=findViewById(R.id.textView);
        final RadioButton radioButton1=findViewById(R.id.radioButton);
        RadioButton radioButton2=findViewById(R.id.radioButton2);
        Button button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton1.isChecked()) {
                    try {
                        Document doc = Jsoup.connect("http://www.xe.com/fr/currencyconverter/convert/?Amount=1&From=TND&To=EUR").get();
                        Elements element = doc.getElementsByClass("uccResultAmount");

                        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                        Number number = format.parse(element.text());
                        double ratio = number.doubleValue();

                        double input=Float.parseFloat(String.valueOf(editText.getText()));
                        Double result=ratio*input;
                        textView.setText(result.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        Document doc = Jsoup.connect("http://www.xe.com/fr/currencyconverter/convert/?Amount=1&From=TND&To=USD").get();
                        Elements element = doc.getElementsByClass("uccResultAmount");

                        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                        Number number = format.parse(element.text());
                        double ratio = number.doubleValue();

                        double input=Float.parseFloat(String.valueOf(editText.getText()));
                        Double result=ratio*input;
                        textView.setText(result.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
        });





    }
}
