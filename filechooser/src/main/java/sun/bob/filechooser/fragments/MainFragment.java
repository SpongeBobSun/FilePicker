package sun.bob.filechooser.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import sun.bob.filechooser.R;
import sun.bob.filechooser.adapters.FileAdapter;
import sun.bob.filechooser.beans.FileBean;

/**
 * Created by bob.sun on 15/8/16.
 */
public class MainFragment extends Fragment {
    private FileAdapter fileAdapter;
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup parent, Bundle savedInstanceState){
        View ret = null;
        ret = layoutInflater.inflate(R.layout.fragment_main,parent, false);
        initListView((ListView) ret.findViewById(R.id.id_listview));
        return ret;
    }

    private void initListView(ListView listView){
        fileAdapter = new FileAdapter(getActivity(), R.layout.layout_file_item);
        fileAdapter.setFolder(Environment.getExternalStorageDirectory().getPath());
        listView.setAdapter(fileAdapter);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileAdapter adapter = (FileAdapter) parent.getAdapter();
                if (!adapter.getItem(position).isFolder()) {
                    return;
                }
                if (!adapter.getItem(position).getFileName().equalsIgnoreCase("..")) {
                    adapter.push();
                }
                adapter.setFolder(((FileBean) parent.getAdapter().getItem(position)).getFullName());
                adapter.notifyDataSetChanged();
            }
        });
    }

    public boolean pop(){
        return fileAdapter == null ? false : fileAdapter.pop();
    }

}
