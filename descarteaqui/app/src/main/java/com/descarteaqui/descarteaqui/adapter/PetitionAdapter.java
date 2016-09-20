package com.descarteaqui.descarteaqui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.descarteaqui.descarteaqui.R;
import com.descarteaqui.descarteaqui.controllers.PetitionController;
import com.descarteaqui.descarteaqui.controllers.UserController;
import com.descarteaqui.descarteaqui.model.Petition;

import java.util.List;

/**
 * Created by Gabriel on 10/09/2016.
 */
public class PetitionAdapter extends RecyclerView.Adapter<PetitionAdapter.MyViewHolder> {

    private List<Petition> mList;
    private LayoutInflater mLayoutInflater;
    private int currentPosition;
    private MyViewHolder viewHolder;
    private final float alreadyClickNG = (float) 1.3;
    private final float alreadyClickOK = (float) 1.3;
    private final float notAlreadyClickNG = 1;
    private final float notAlreadyClickOK = 1;
    private Context ctx;



    public PetitionAdapter(Context c, List<Petition> petitionList) {
        mList = petitionList;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.rate_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        ctx = view.getContext();

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.streetName.setText(String.valueOf(mList.get(position).getStreetName()));
        holder.discrictName.setText(String.valueOf(mList.get(position).getDistrictName()));
        holder.creationDate.setText(String.valueOf("Criado em " + mList.get(position).getCreationDate() + ", por " + mList.get(position).getCreator()));
        holder.rateOK.setText("+ " + String.valueOf(mList.get(position).getRatesOK()));
        holder.rateNG.setText("+ " + String.valueOf(mList.get(position).getRatesNG()));


        holder.rateNGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mList.get(position).getCreator().equals(UserController.getCurrentUser(ctx))) {
                    if (userCanRate(mList.get(position))) {
                        if (holder.rateNGButton.getScaleX() == alreadyClickNG) {
                            mList.get(position).unrateNG();
                            holder.rateNGButton.animate().scaleX(notAlreadyClickNG).scaleY(notAlreadyClickNG).setDuration(200).start();
                            UserController.removeRatedPetition(ctx, UserController.getCurrentUser(ctx), mList.get(position).getID());
                        } else {
                            mList.get(position).rateNG();
                            holder.rateNGButton.animate().scaleX(alreadyClickNG).scaleY(alreadyClickNG).setDuration(200).start();
                            UserController.addRatedPetition(ctx, UserController.getCurrentUser(ctx), mList.get(position).getID(), UserController.getCurrentUser(ctx));
                            if (holder.rateOKButton.getScaleX() == alreadyClickOK) {
                                holder.rateOKButton.animate().scaleX(notAlreadyClickOK).scaleY(notAlreadyClickOK).setDuration(200).start();
                                holder.rateOK.setText("+ " + String.valueOf(mList.get(position).getRatesOK()));
                            }
                        }

                        holder.rateNG.setText("+ " + String.valueOf(mList.get(position).getRatesNG()));
                        PetitionController.updatePetition(ctx, mList.get(position));
                    }
                } else {
                    Toast.makeText(ctx, "Você não pode votar nas suas petições. :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.rateOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mList.get(position).getCreator().equals(UserController.getCurrentUser(ctx))) {

                    if (userCanRate(mList.get(position))) {
                        if (holder.rateOKButton.getScaleX() == alreadyClickOK) {
                            mList.get(position).unrateOK();
                            holder.rateOKButton.animate().scaleX(notAlreadyClickOK).scaleY(notAlreadyClickOK).setDuration(200).start();
                            UserController.removeRatedPetition(ctx, UserController.getCurrentUser(ctx), mList.get(position).getID());
                        } else {
                            mList.get(position).rateOK();
                            holder.rateOKButton.animate().scaleX(alreadyClickOK).scaleY(alreadyClickOK).setDuration(200).start();
                            UserController.addRatedPetition(ctx, UserController.getCurrentUser(ctx), mList.get(position).getID(), UserController.getCurrentUser(ctx));
                            if (holder.rateNGButton.getScaleX() == alreadyClickNG) {
                                holder.rateNGButton.animate().scaleX(notAlreadyClickNG).scaleY(notAlreadyClickNG).setDuration(200).start();
                                holder.rateNG.setText("+ " + String.valueOf(mList.get(position).getRatesNG()));
                            }
                        }

                        holder.rateOK.setText("+ " + String.valueOf(mList.get(position).getRatesOK()));
                        PetitionController.updatePetition(ctx, mList.get(position));
                    }
                } else {
                    Toast.makeText(ctx, "Você não pode votar nas suas petições. :(", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView streetName;
        public TextView discrictName;
        public TextView creationDate;
        public TextView rateOK;
        public TextView rateNG;
        public ImageView rateOKButton;
        public ImageView rateNGButton;

        public MyViewHolder (View itemView){
            super(itemView);

            streetName = (TextView) itemView.findViewById(R.id.nome_rua);
            discrictName = (TextView) itemView.findViewById(R.id.nome_bairro);
            creationDate = (TextView) itemView.findViewById(R.id.data_criacao);
            rateNG = (TextView) itemView.findViewById(R.id.ng_rate_number);
            rateOK = (TextView) itemView.findViewById(R.id.ok_rate_number);
            rateOKButton = (ImageView) itemView.findViewById(R.id.ok_rate);
            rateNGButton = (ImageView) itemView.findViewById(R.id.ng_rate);

            streetName.setSelected(true);
            discrictName.setSelected(true);
            creationDate.setSelected(true);

        }
    }

    private boolean userCanRate(Petition petition){
        List<Petition> ratedPetitions = UserController.getRatedPetitions(ctx, UserController.getCurrentUser(ctx));

        int districtRateLimit = 0;
        boolean alreadyRated = false;

        for (int i = 0; i < ratedPetitions.size(); i++) {
            if (ratedPetitions.get(i).getDistrictName().equals(petition.getDistrictName())){
                districtRateLimit++;
            }

            if (ratedPetitions.get(i).getID() == petition.getID()){
                alreadyRated = true;
            }
        }

        if (alreadyRated){
            Toast.makeText(ctx, "Você já votou nessa aqui, fion. :(", Toast.LENGTH_SHORT).show();
            return false;
        } else if (districtRateLimit >= 2){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
            alertDialogBuilder.setTitle("Não foi possível votar na Petição.");
            alertDialogBuilder.setMessage("Você já estrapolou o limite de votos por bairro.");

            alertDialogBuilder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return false;
        } else {
            return true;
        }
    }

}