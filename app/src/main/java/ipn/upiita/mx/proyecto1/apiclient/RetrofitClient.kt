package ipn.upiita.mx.proyecto1.apiclient

import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    /*
    /<><><><><><><><><><><>VERSION ORIGINAL COMPARTIDA POR EL PROFESOR<><><><><><><>
    private const val BASE_URL = "http://localhost:3000/api/tasks/"
    val api: TaskApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ipn.upiita.mx.proyecto1.apiclient.RetrofitClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }
    */
    private const val BASE_URL = "http://10.0.2.2:3000/api/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val taskApi : TaskApiService by lazy {
        retrofit.create(TaskApiService::class.java) }

    val userApi: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}