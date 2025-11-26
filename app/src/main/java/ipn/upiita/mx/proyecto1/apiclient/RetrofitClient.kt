package ipn.upiita.mx.proyecto1.apiclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://localhost:3000/api/tasks/"

    val api: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ipn.upiita.mx.proyecto1.apiclient.RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }
}