package sun.bob.filechooser.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import sun.bob.filechooser.R;
import sun.bob.filechooser.beans.FileBean;

/**
 * Created by bob.sun on 15/8/16.
 */
public class FileAdapter extends ArrayAdapter {
    ArrayList<FileBean> files;
    public FileAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void setFolder(String folderPath){
        files = new ArrayList();
        Iterator iterator = Arrays.asList(new File(folderPath).listFiles()).iterator();
        File file = null;
        while(iterator.hasNext()){
            file = (File) iterator.next();
            files.add(new FileBean(file.getName(),file.isDirectory()));
        }
    }

    @Override
    public int getCount(){
        return files.size();
    }

    @Override
    public View getView(int posistion, View convertView, ViewGroup parent){
        View ret = convertView;
        ViewHolder viewHolder;
        if (ret == null){
            ret = View.inflate(getContext(), R.layout.layout_file_item,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) ret.findViewById(R.id.id_item_icon);
            viewHolder.name = (TextView) ret.findViewById(R.id.id_file_name);
            ret.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) ret.getTag();
        }
        viewHolder.name.setText(files.get(posistion).getFileName());
        viewHolder.icon.setImageBitmap(files.get(posistion).getIcon());
        return ret;
    }

    class ViewHolder{
        public ImageView icon;
        public TextView name;
    }
}
