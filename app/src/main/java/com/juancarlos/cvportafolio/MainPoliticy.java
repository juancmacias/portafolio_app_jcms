package com.juancarlos.cvportafolio;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.juancarlos.cvportafolio.app.AppConfig;
import com.juancarlos.cvportafolio.app.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainPoliticy extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView = findViewById(R.id.htmlTextView);


        try {
            InputStream is = getAssets().open(AppConfig.POLICITA);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String html = builder.toString();

            Html.ImageGetter imageGetter = new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    try {
                        InputStream ims = getAssets().open(source);
                        Drawable d = Drawable.createFromStream(ims, null);
                        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                        return d;
                    } catch (Exception e) {
                        return null;
                    }
                }
            };

            Spanned spanned;
            spanned = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
            Log.d("CARGA", "Datos de politica");
            textView.setText(spanned);
            textView.setMovementMethod(LinkMovementMethod.getInstance()); // activa clic en enlaces

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}