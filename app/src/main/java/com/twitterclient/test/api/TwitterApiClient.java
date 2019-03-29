package com.twitterclient.test.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwitterApiClient {

    public static <S> S createServiceWithAuth(Class<S> serviceClass) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request().newBuilder().addHeader("Authorization", APIConfig.AUTH_APP_TOKEN).build();

                return chain.proceed(request);
            }
        };
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        return retrofit.create(serviceClass);
    }
}
