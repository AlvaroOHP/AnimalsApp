package com.iteso.altomobileproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private final Context context;
    private final  ArrayList<Animal> Animals;

    public  Adapter(Context context, ArrayList<Animal> animals) {
        this.context = context;
        this.Animals = animals;

    }



    @Override
    public int getCount() {
        return Animals.size();
    }

    @Override
    public Object getItem(int position) {
        return Animals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(
                    R.layout.list, null);
        }

        TextView name =  convertView.findViewById(R.id.list_entry_name);


        ImageView ivBasicImage = convertView.findViewById(R.id.image);

        String imageUri = Animals.get(position).getPictureURL();

        if(imageUri.isEmpty()) {
            imageUri =  context.getString(R.string.default_image);
            }

        Picasso.with(context).load(imageUri).resize(300, 300).
                centerCrop().into(ivBasicImage);
        name.setText(Animals.get(position).getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context, "Life: " + Animals.get(position).getLife() + "", Toast.LENGTH_SHORT);
                toast.show();
            }
        });



        return convertView;
    }
    }

