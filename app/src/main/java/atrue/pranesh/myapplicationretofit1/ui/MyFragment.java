package atrue.pranesh.myapplicationretofit1.ui;


import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import atrue.pranesh.myapplicationretofit1.R;
import atrue.pranesh.myapplicationretofit1.ui.base.BaseFragment;
import atrue.pranesh.myapplicationretofit1.ui.model.Users;

public class MyFragment extends BaseFragment implements View.OnClickListener {
    EditText edtName;
    EditText edtEmail;
    Button button;
    Users users;
    Communicator communicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showpopup_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtName=view.findViewById(R.id.edtName);
        edtEmail=view.findViewById(R.id.edtEmail);
        button=view.findViewById(R.id.btn);
        button.setOnClickListener(this);

        communicator= (Communicator) getActivity();

        Bundle bundle=getArguments();
        if(bundle!=null && bundle.containsKey("Users")){
            users  = bundle.getParcelable("Users");
            if(bundle!=null){
                edtEmail.setText(users.email);
                edtName.setText(users.name);
            }
        }

    }

    @Override
    public void onClick(View view) {
        users.email=edtEmail.getText().toString();
        users.name=edtName.getText().toString();
        Bundle bundle=new Bundle();
        bundle.putParcelable("Users",users);
        bundle.putInt("pos",getArguments().getInt("pos"));
        communicator.sendData(bundle);
        getFragmentManager().popBackStack();
    }
}
