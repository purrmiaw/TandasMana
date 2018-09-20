package xyz.miaw.android.base.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import xyz.miaw.android.base.models.SharedStuff;

public interface MiawSampleApiService {

    @GET("stuffs")
    Call<List<SharedStuff>> StuffsGetAll();

}
