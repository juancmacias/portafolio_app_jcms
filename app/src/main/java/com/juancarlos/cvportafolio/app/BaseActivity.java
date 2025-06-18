package com.juancarlos.cvportafolio.app;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.juancarlos.cvportafolio.EnviarContacto;
import com.juancarlos.cvportafolio.ListActivity;
import com.juancarlos.cvportafolio.MainActivity;
import com.juancarlos.cvportafolio.MainPoliticy;
import com.juancarlos.cvportafolio.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuinicio) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        } else if (itemId == R.id.menupolitica) {
            startActivity(new Intent(this, MainPoliticy.class));
            return true;
        } else if (itemId == R.id.list) {
            startActivity(new Intent(this, ListActivity.class));
            return true;
        } else if (itemId == R.id.contactar) {
            startActivity(new Intent(this, EnviarContacto.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
