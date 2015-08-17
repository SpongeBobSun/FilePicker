package sun.bob.filechooser.activities;

import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import sun.bob.filechooser.R;
import sun.bob.filechooser.fragments.MainFragment;
import sun.bob.filechooser.utils.Constants;

public class FileChooserActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filechooser);
        initFragments();
        initStaticBmps();
    }

    private void initFragments(){
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.id_fragment_container,new MainFragment(),"mainFragment").commit();
    }

    private void initStaticBmps(){
        Constants.folderIcon = BitmapFactory.decodeResource(getResources(), R.drawable.folder);
        Constants.documentIcon = BitmapFactory.decodeResource(getResources(), R.drawable.document);
        Constants.fileIcon = BitmapFactory.decodeResource(getResources(), R.drawable.file);
        Constants.musicIcon = BitmapFactory.decodeResource(getResources(), R.drawable.music);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
