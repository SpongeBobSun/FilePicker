package sun.bob.filechooser.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import sun.bob.filechooser.R;
import sun.bob.filechooser.beans.FileBean;
import sun.bob.filechooser.utils.Results;

/**
 * Created by bob.sun on 15/8/16.
 */
public class FileAdapter extends ArrayAdapter {
    private ArrayList<FileBean> files;
    private Stack<String> histories;
    private String currentPath;
    public FileAdapter(Context context, int resource) {
        super(context, resource);
        histories = new Stack<>();
    }

    public void setFolder(String folderPath){
        currentPath = folderPath;
        if (files != null){
            files.clear();
        }
        files = new ArrayList();
        File file = new File(folderPath);
        Iterator iterator = Arrays.asList(file.listFiles()).iterator();
        if (file.getParent() != null){
            files.add(new FileBean("..", true, file.getParent()));
        }
        File filetoAdd = null;
        while(iterator.hasNext()){
            filetoAdd = (File) iterator.next();
            files.add(new FileBean(filetoAdd.getName(), filetoAdd.isDirectory(), filetoAdd.getAbsolutePath()));
        }
    }

    @Override
    public int getCount(){
        return files.size();
    }

    @Override
    public FileBean getItem(int position){
        return files.get(position);
    }

    @Override
    public View getView(final int posistion, View convertView, ViewGroup parent){
        View ret = convertView;
        final ViewHolder viewHolder;
        if (ret == null){
            ret = View.inflate(getContext(), R.layout.layout_file_item,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) ret.findViewById(R.id.id_item_icon);
            viewHolder.name = (TextView) ret.findViewById(R.id.id_file_name);
            viewHolder.checkBox = (CheckBox) ret.findViewById(R.id.id_item_check);
            ret.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) ret.getTag();
        }
        final FileBean bean = files.get(posistion);
        viewHolder.name.setText(bean.getFileName());
        viewHolder.icon.setImageBitmap(files.get(posistion).getIcon());
        if (bean.getFileName().equalsIgnoreCase("..")){
            viewHolder.checkBox.setVisibility(View.INVISIBLE);
            return ret;
        } else {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        }
        viewHolder.checkBox.setChecked(Results.getInstance().contains(bean.getFullName()));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Results.getInstance().contains(bean.getFullName())){
                    ((CheckBox) v).setChecked(false);
                    Results.getInstance().removeFile(bean.getFullName());
                } else {
                    ((CheckBox) v).setChecked(true);
                    Results.getInstance().putFile(bean.getFullName());
                }
            }
        });
        return ret;
    }

    public boolean pop(){
        String folder = null;
        boolean ret = true;
        try {
            folder = histories.pop();
        } catch (EmptyStackException e) {
            ret =  false;
        }
        if (ret) {
            this.setFolder(folder);
            this.notifyDataSetChanged();
        }
        return ret;
    }

    public void push(){
        histories.push(currentPath);
    }

    class ViewHolder{
        public ImageView icon;
        public TextView name;
        public CheckBox checkBox;
    }
}
