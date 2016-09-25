package com.descarteaqui.descarteaqui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.descarteaqui.descarteaqui.R;
import com.descarteaqui.descarteaqui.controllers.TipsController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Gabriel on 24/09/2016.
 */
public class TipAdapter extends BaseExpandableListAdapter {

    private List<String> listGroup;
    private HashMap<String, List<String>> listData;
    private LayoutInflater inflater;
    private String type;

    public TipAdapter(Context c, List<String> listGroup, HashMap<String, List<String>> listData, String type) {
        this.listData = listData;
        this.listGroup = listGroup;
        this.type = type;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listData.get(listGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listData.get(listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup holder;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.header_expandable_list_view, null);

            holder = new ViewHolderGroup();
            convertView.setTag(holder);

            holder.tvGroup = (TextView) convertView.findViewById(R.id.tvGroup);
            holder.imgGroup = (ImageView) convertView.findViewById(R.id.imageGroup);
        } else {
            holder = (ViewHolderGroup) convertView.getTag();
        }

        holder.empIcon = (ImageView) convertView.findViewById(R.id.empIcon);

        if (type.equals("Empresas")){
            holder.empIcon.setImageResource(TipsController.getIconEmp(groupPosition));
        } else if (type.equals("EcoDicas")){
            holder.empIcon.setImageResource(TipsController.getIconTips(groupPosition));
        }

        holder.empIcon.setColorFilter(Color.parseColor("#448d25"));

        if (isExpanded) {
            holder.tvGroup.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            holder.imgGroup.animate().rotation(-180);
        } else {
            holder.tvGroup.setTypeface(Typeface.DEFAULT);
            holder.imgGroup.animate().rotation(0);
        }

        holder.tvGroup.setText(listGroup.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem holder;
        String val = (String) getChild(groupPosition, childPosition);

        if (convertView == null){
            if (type.equals("EcoDicas")) {
                convertView = inflater.inflate(R.layout.item_expandable_list_view, null);
            } else if (type.equals("Empresas")){
                convertView = inflater.inflate(R.layout.item_expandable_list_view_emp, null);
            }

            holder = new ViewHolderItem();
            convertView.setTag(holder);

            holder.tvItemText = (TextView) convertView.findViewById(R.id.tvItemText);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }

        if (type.equals("Empresas")) {
            holder.tvItemIcon = (ImageView) convertView.findViewById(R.id.tvItemIcon);
            holder.tvItemIcon.setImageResource(TipsController.getIconChild(childPosition));
            holder.tvItemIcon.setColorFilter(Color.GRAY);
        }

        holder.tvItemText.setText(val);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolderGroup {
        TextView tvGroup;
        ImageView imgGroup;
        ImageView empIcon;

    }

    class ViewHolderItem{
        TextView tvItemText;
        ImageView tvItemIcon;
    }
}
