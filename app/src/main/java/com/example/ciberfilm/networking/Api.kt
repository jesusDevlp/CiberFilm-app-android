package com.example.ciberfilm.networking

import com.example.ciberfilm.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object Api {

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
        .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val builder : Retrofit.Builder = Retrofit.Builder().
    baseUrl("http://i202116627-001-site1.atempurl.com/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())

    interface RemoteService{

        // Customer
        @GET("api/Customers")
        suspend fun getAllCustomers() : Response<List<Customer>>

        @GET("api/Customer/{id}")
        suspend fun getFindByIdCustomer(@Path("id") id: Int) : Response<Customer>

        @POST("api/Customer")
        suspend fun saveCustomer(@Body request: RegisterCustomerRequest) : Response<ResultApi>

        @GET("api/Customer")
        suspend fun login(@Query("email") email:String, @Query("password") password: String) : Response<ResultApiLogin>

        // Sale
        @GET("api/Sales")
        suspend fun getAllSales() : Response<List<Sales>>

        @GET("api/Sales/{id}")
        suspend fun getAllSalesById(@Path("id") id: Int) : Response<List<Sales>>

        @POST("api/Sale")
        suspend fun saveSale(@Body request: SaleResquet) : Response<ResultApiv2>

        @GET("api/Sale/{id}")
        suspend fun getFindByIdSale(@Path("id") id:Int) : Response<Sales>

        // Movie
        @GET("api/Movie")
        suspend fun getAllMovies() : Response<List<Movie>>

        @GET("api/Movie/{id}")
        suspend fun getFindByIdMovie(@Path("id") id: Int) : Response<Movie>






    }


    fun build() : RemoteService{
        return builder.build().create(RemoteService::class.java)
    }
}