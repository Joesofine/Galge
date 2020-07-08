package com.example.galge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// Customized adapter til highscore listen
public class adapter_user  extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<userObj> highArr;
    private user_obj user;

    public adapter_user(Context context, ArrayList<userObj> arr) {
        super(context, R.layout.listelement_high);
        this.highArr = arr;
        this.context = context;
        user = user_obj.getInstance();
    }

    @Override
    public int getCount() {
        return highArr.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listelement_high, parent, false);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.score = convertView.findViewById(R.id.score);
            viewHolder.rankings = convertView.findViewById(R.id.rankings);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(highArr.get(position).getName());
        viewHolder.score.setText(String.valueOf(highArr.get(position).getScore()));
        viewHolder.rankings.setText(String.valueOf(position + 1 ));


        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView score;
        TextView rankings;
    }
}
