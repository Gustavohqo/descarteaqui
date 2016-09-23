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
    private List<Petition> ratedPetitions;
    private LayoutInflater mLayoutInflater;
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

        ratedPetitions = UserController.getRatedPetitions(ctx, UserController.getCurrentUser(ctx));

        holder.streetName.setText(String.valueOf(mList.get(position).getStreetName()));
        holder.discrictName.setText(String.valueOf(mList.get(position).getDistrictName()));
        holder.creationDate.setText(String.valueOf("Criado em " + mList.get(position).getCreationDate() + ", por " + mList.get(position).getCreator()));
        holder.rateOK.setText("+ " + String.valueOf(mList.get(position).getRatesOK()));
        holder.rateNG.setText("+ " + String.valueOf(mList.get(position).getRatesNG()));


        for (int i = 0; i < ratedPetitions.size(); i++) {
            if (ratedPetitions.get(i).getID() == mList.get(position).getID()){
                if (UserController.getTypeRate(ctx, mList.get(position).getID(), UserController.getCurrentUser(ctx)).equals("ng")){
                    holder.rateNGButton.animate().scaleX(alreadyClickNG).scaleY(alreadyClickNG).setDuration(200).start();
                } else if (UserController.getTypeRate(ctx, mList.get(position).getID(), UserController.getCurrentUser(ctx)).equals("ok")){
                    holder.rateOKButton.animate().scaleX(alreadyClickOK).scaleY(alreadyClickOK).setDuration(200).start();
                }
            }
        }

        holder.rateNGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mList.get(position).getCreator().equals(UserController.getCurrentUser(ctx))) {
                    if (userCanRate(mList.get(position), holder)) {
                        mList.get(position).rateNG();
                        holder.rateNGButton.animate().scaleX(alreadyClickNG).scaleY(alreadyClickNG).setDuration(200).start();
                        holder.rateNG.setText("+ " + String.valueOf(mList.get(position).getRatesNG()));
                        UserController.addRatedPetition(ctx, UserController.getCurrentUser(ctx), mList.get(position).getID(), UserController.getCurrentUser(ctx), "ng");
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

                    if (userCanRate(mList.get(position), holder)) {
                        mList.get(position).rateOK();
                        holder.rateOKButton.animate().scaleX(alreadyClickOK).scaleY(alreadyClickOK).setDuration(200).start();
                        holder.rateOK.setText("+ " + String.valueOf(mList.get(position).getRatesOK()));
                        UserController.addRatedPetition(ctx, UserController.getCurrentUser(ctx), mList.get(position).getID(), UserController.getCurrentUser(ctx), "ok");
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

    private boolean userCanRate(Petition petition, final MyViewHolder holder){
        List<Petition> ratedPetitions = UserController.getRatedPetitions(ctx, UserController.getCurrentUser(ctx));

        int districtRateLimit = 0;
        boolean alreadyRated = false;

        for (int i = 0; i < ratedPetitions.size(); i++) {
            if (ratedPetitions.get(i).getDistrictName().equals(petition.getDistrictName())){
                districtRateLimit++;
            }

            if (petition.getID() == ratedPetitions.get(i).getID()){
                alreadyRated = true;
            }
        }

        if (alreadyRated){

            final String ratedType = UserController.getTypeRate(ctx, petition.getID(), UserController.getCurrentUser(ctx));
            final Petition thisPetition = petition;
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
            alertDialogBuilder.setTitle("Ops! Você já votou nessa petição.");
            alertDialogBuilder.setMessage("Deseja alterar o seu voto?");

            alertDialogBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    if (ratedType.equals("ng")){
                        thisPetition.unrateNG();
                        holder.rateNGButton.animate().scaleX(notAlreadyClickNG).scaleY(notAlreadyClickNG).setDuration(200).start();
                        holder.rateNG.setText("+ " + String.valueOf(thisPetition.getRatesNG()));

                    } else if (ratedType.equals("ok")){
                        thisPetition.unrateOK();
                        holder.rateOKButton.animate().scaleX(notAlreadyClickOK).scaleY(notAlreadyClickOK).setDuration(200).start();
                        holder.rateOK.setText("+ " + String.valueOf(thisPetition.getRatesOK()));
                    }
                    UserController.removeRatedPetition(ctx, UserController.getCurrentUser(ctx), thisPetition.getID());
                    PetitionController.updatePetition(ctx, thisPetition);
                }
            });
            alertDialogBuilder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return false;
        }

        if (districtRateLimit >= 2){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
            alertDialogBuilder.setTitle("Não foi possível avaliar a Petição.");
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