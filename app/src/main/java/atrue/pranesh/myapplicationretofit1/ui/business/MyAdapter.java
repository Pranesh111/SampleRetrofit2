package atrue.pranesh.myapplicationretofit1.ui.business;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import atrue.pranesh.myapplicationretofit1.R;
import atrue.pranesh.myapplicationretofit1.ui.MainActivity;
import atrue.pranesh.myapplicationretofit1.ui.model.Users;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<Users> usersList;
    MyViewHolder myViewHolder;
    View.OnClickListener listener;
    public MyAdapter(List<Users> usrList, View.OnClickListener listener){
        this.usersList=usrList;
        this.listener=listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter,parent,false);
        myViewHolder= new MyViewHolder(view,listener);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        myViewHolder.onBind(usersList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
