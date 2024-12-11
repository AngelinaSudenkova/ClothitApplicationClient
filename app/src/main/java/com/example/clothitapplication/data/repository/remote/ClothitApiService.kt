package com.example.clothitapplication.data.repository.remote

import com.example.clothitapplication.data.repository.remote.req.UserLoginReq
import com.example.clothitapplication.data.repository.remote.req.UserLogoutReq
import com.example.clothitapplication.data.repository.remote.req.UserRegisterReq
import com.example.clothitapplication.data.repository.remote.response.SignInResponse
import com.example.clothitapplication.data.repository.remote.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface ClothitApiService {
    @POST("service/clothit/api/v1/login")
    suspend fun login(@Body request: UserLoginReq): Response<SignInResponse>

    @POST("service/clothit/api/v1/register")
    suspend fun register(@Body request: UserRegisterReq): Response<SignUpResponse>

    @DELETE("service/clothit/api/v1/logout")
    suspend fun logout(@Header("Authorization") token: String): Response<Unit>

}