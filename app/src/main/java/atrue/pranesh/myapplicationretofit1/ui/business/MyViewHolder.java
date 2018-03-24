package atrue.pranesh.myapplicationretofit1.ui.business;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import atrue.pranesh.myapplicationretofit1.R;
import atrue.pranesh.myapplicationretofit1.ui.model.Users;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView txtName, txtCompany, txtEmail, txtAdd;
    CheckBox checkBox;
    CardView cardView;
    View.OnClickListener listener;
    public MyViewHolder(View itemView,View.OnClickListener listener) {
        super(itemView);
        checkBox=itemView.findViewById(R.id.chkBox);
        txtName=itemView.findViewById(R.id.txtName);
        txtCompany=itemView.findViewById(R.id.txtComp);
        txtEmail=itemView.findViewById(R.id.txtEmail);
        txtAdd=itemView.findViewById(R.id.txtAddress);
        cardView=itemView.findViewById(R.id.cardView);
        this.listener=listener;
    }

    public void onBind(Users users, int position) {
        txtName.setText(users.name);
        txtCompany.setText(users.company.name);
        txtEmail.setText(users.email);
        txtAdd.setText(users.address.street.concat(",".concat(users.address.city)));

        cardView.setOnClickListener(listener);
        cardView.setTag(users);
        cardView.setTag(R.id.cardView,position);
    }
}
