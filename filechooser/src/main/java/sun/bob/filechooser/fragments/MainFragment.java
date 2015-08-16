package sun.bob.filechooser.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import sun.bob.filechooser.R;
import sun.bob.filechooser.adapters.FileAdapter;

/**
 * Created by bob.sun on 15/8/16.
 */
public class MainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup parent, Bundle savedInstanceState){
        View ret = null;
        ret = layoutInflater.inflate(R.layout.fragment_main,parent, false);
        initListView((ListView) ret.findViewById(R.id.id_listview));
        return ret;
    }

    private void initListView(ListView listView){
        FileAdapter fileAdapter = new FileAdapter(getActivity(), R.layout.layout_file_item);
        fileAdapter.setFolder(Environment.getExternalStorageDirectory().getPath());
        listView.setAdapter(fileAdapter);
    }
}
