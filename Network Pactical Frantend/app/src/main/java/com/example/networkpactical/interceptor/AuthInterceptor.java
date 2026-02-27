package com.example.networkpactical;

import android.content.Context;
import androidx.annotation.NonNull;
import com.example.networkpactical.api.AuthApi;
import com.example.networkpactical.client.RetrofitClient;
import com.example.networkpactical.dto.TokenDTO;
import com.example.networkpactical.manager.TockenManager;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthInterceptor implements Interceptor {

    private Context context;
    public static final String AUTHORIZATION = "Authorization";
    public static final int UNAUTHORIZATION = 401;
    private AuthApi authApi;

    public AuthInterceptor(Context context) {
        this.context = context.getApplicationContext();
        // Refresh කිරීම සඳහා පමණක් භාවිතා කරන හුදකලා Retrofit instance එකක්
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.authApi = retrofit.create(AuthApi.class);
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String uri = originalRequest.url().encodedPath();

        // Login හෝ Refresh සඳහා Token අවශ්‍ය නැත
        if (uri.contains("/auth/login") || uri.contains("/auth/refresh")) {
            return chain.proceed(originalRequest);
        }

        String accessToken = TockenManager.retrieveAccessToken(context);
        Request request = buildRequestWithToken(originalRequest, accessToken);
        Response response = chain.proceed(request);

        // 401 Error එකක් ආවොත් පමණක් Token එක Refresh කරන්න
        if (response.code() == UNAUTHORIZATION) {
            synchronized (this) {
                String newAccessToken = fetchRefreshToken();
                if (newAccessToken != null) {
                    response.close(); // පරණ response එක අනිවාර්යයෙන්ම වසන්න
                    Request newRequest = buildRequestWithToken(originalRequest, newAccessToken);
                    return chain.proceed(newRequest);
                }
            }
        }
        return response;
    }

    private Request buildRequestWithToken(Request request, String token) {
        if (token == null) return request;
        return request.newBuilder()
                .header(AUTHORIZATION, "Bearer " + token) // "Bearer " පසු හිස්තැන වැදගත්
                .build();
    }

    private String fetchRefreshToken() {
        try {
            String refreshToken = TockenManager.retrieveRefreshToken(context);
            if (refreshToken == null) return null;

            TokenDTO dto = new TokenDTO();
            dto.setRefeshToken(refreshToken);

            Call<TokenDTO> tokenDTOCall = authApi.refeshAcessTocken(dto);
            retrofit2.Response<TokenDTO> response = tokenDTOCall.execute();

            if (response.isSuccessful() && response.body() != null) {
                TokenDTO tokenDTO = response.body();
                TockenManager.saveTokens(context, tokenDTO);
                return tokenDTO.getAccessToken();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TockenManager.clearTokens(context);
        return null;
    }
}