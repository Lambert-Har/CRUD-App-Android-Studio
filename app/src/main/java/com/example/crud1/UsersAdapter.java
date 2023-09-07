package com.example.crud1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter {

    ArrayList<String> al_id = new ArrayList<String>();
    ArrayList<String> al_name = new ArrayList<String>();
    ArrayList<String> al_username = new ArrayList<String>();
    Context ctx;

    public UsersAdapter(
            ArrayList<String> al_id,
            ArrayList<String> al_name,
            ArrayList<String> al_username,
            Context ctx
    ) {
      this.al_id = al_id;
      this.al_name = al_name;
      this.al_username = al_username;
      this.ctx = ctx;
    }

    @Override
    public int getCount() {

        return al_id.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater;
        ViewHolder vh;
        if (view == null){
            vh = new ViewHolder();
            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.users_list,null);
            vh.t_id = view.findViewById(R.id.ul_id);
            vh.t_name = view.findViewById(R.id.ul_name);
            vh.t_email = view.findViewById(R.id.ul_email);
            view.setTag(vh);

        } else {
            vh =(ViewHolder) view.getTag();
        }
        vh.t_id.setText(al_id.get(position));
        vh.t_name.setText(al_name.get(position));
        vh.t_email.setText(al_username.get(position));
        return view;
    }
}

class ViewHolder {
    TextView t_id,t_name,t_email;
}