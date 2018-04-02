package io.github.lfasmpao.lyrica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends BaseAdapter {

    public class ViewHolder {
        TextView title, artist;
    }

    private List<Mutator> mutatorList;

    private Context context;
    private ArrayList<Mutator> arrayList;

    ListAdapter(List<Mutator> x, Context y){
        this.mutatorList = x;
        this.context = y;
        arrayList = new ArrayList<>();
        arrayList.addAll(mutatorList);
    }

    @Override
    public int getCount() {
        return mutatorList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return mutatorList.get(i).getID();
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        ViewHolder viewHolder;
        if (rowView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            rowView = inflater.inflate(R.layout.content_list, null);
            viewHolder = new ViewHolder();
            viewHolder.title = rowView.findViewById(R.id.line_a);
            viewHolder.artist = rowView.findViewById(R.id.line_b);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(mutatorList.get(i).getTitle());
        viewHolder.artist.setText(mutatorList.get(i).getArtist());
        return rowView;
    }

    void filter(String text){
        text = text.toLowerCase(Locale.getDefault());
        mutatorList.clear();
        if (text.length() == 0) {
            mutatorList.addAll(arrayList);
        }
        else {
            for (Mutator values: arrayList) {
                if (text.length() != 0 && values.getTitle().toLowerCase(Locale.getDefault()).contains(text)) {
                    mutatorList.add(values);
                } else if (text.length() != 0 && values.getArtist().toLowerCase(Locale.getDefault()).contains(text)) {
                    mutatorList.add(values);
                }
            }
        }
        notifyDataSetChanged();
    }
}
