package atrue.pranesh.myapplicationretofit1.ui.network;


import java.util.List;

import atrue.pranesh.myapplicationretofit1.ui.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiStories {
    @GET("/users")
    Call<List<Users>> doGetListUsers();
}
