package atrue.pranesh.myapplicationretofit1.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import atrue.pranesh.myapplicationretofit1.R;
import atrue.pranesh.myapplicationretofit1.ui.base.BaseFragment;
import atrue.pranesh.myapplicationretofit1.ui.business.MyAdapter;
import atrue.pranesh.myapplicationretofit1.ui.model.Users;
import atrue.pranesh.myapplicationretofit1.ui.network.ApiClients;
import atrue.pranesh.myapplicationretofit1.ui.network.ApiStories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment implements View.OnClickListener{
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    LinearLayout lnrRec;
    private List<Users> usersList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView);
        lnrRec=view.findViewById(R.id.lnrRec);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        if(usersList.size()>0){
            setAdapter(usersList);
        }
        else{
            fetchData();
        }

    }

    private void fetchData() {
        ApiStories apiStories =
                ApiClients.getClient().create(ApiStories.class);
        Call<List<Users>> call = apiStories.doGetListUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                setAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }

    private void setAdapter(List<Users> usersList) {
        this.usersList=usersList;
        myAdapter=new MyAdapter(usersList,this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.cardView:
                Users users= (Users) view.getTag();
                int pos= (Integer) view.getTag(R.id.cardView);
                showPopUpDialog(users,pos);
                break;
        }
    }

    private void showPopUpDialog(Users users, int pos) {
        lnrRec.setVisibility(View.GONE);
        Fragment dialogFragment=new MyFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("Users",users);
        bundle.putInt(("pos"),pos);
        dialogFragment.setArguments(bundle);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.base_container,dialogFragment,"PopUpFragment");
        fragmentTransaction.addToBackStack("PopUpFragment");
        fragmentTransaction.commit();

    }

    void refershAdapter(Bundle bundle)
    {
        int position;
        Users users;
        if(bundle!=null && bundle.containsKey("pos") && bundle.containsKey("Users")){
            users=bundle.getParcelable("Users");
            position=bundle.getInt("pos");
            myAdapter.notifyItemChanged(position,users);
        }

    }
}
