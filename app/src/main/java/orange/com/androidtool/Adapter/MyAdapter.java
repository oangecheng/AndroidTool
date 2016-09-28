package orange.com.androidtool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import orange.com.androidtool.Info.Msg;
import orange.com.androidtool.R;

/**
 * Created by Orange on 2016/9/20.
 */
public class MyAdapter extends BaseAdapter {

    private List<Msg> listMsg;
    private Context context;
    private LayoutInflater inflater;

    public MyAdapter(List<Msg> listMsg, Context context) {
        this.listMsg = listMsg;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listMsg.size();
    }

    @Override
    public Object getItem(int position) {
        return listMsg.get(position);
    }

    @Override
    public long getItemId(int position) {
        Msg msg = listMsg.get(position);
        long s = msg.getId();
        return s;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Msg msg = listMsg.get(position);
        convertView = this.inflater.inflate(R.layout.layout_list_item, null);
        ItemView itemView = new ItemView();

        itemView.tvName = (TextView)convertView.findViewById(R.id.tv_name);
        itemView.tvPass = (TextView)convertView.findViewById(R.id.tv_pass);

        itemView.tvName.setText(msg.getName());
        itemView.tvPass.setText(msg.getPassword());

        return convertView;
    }

    private class ItemView{
        private TextView tvName;
        private TextView tvPass;
    }
}
