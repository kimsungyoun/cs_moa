package com.mju.csmoa.retrofit

import com.google.gson.JsonElement
import com.mju.csmoa.login.domain.model.PostLoginReq
import com.mju.csmoa.login.domain.model.PostOAuthLoginReq
import com.mju.csmoa.login.domain.model.PostSignUpReq
import retrofit2.Call
import retrofit2.http.*

interface IRetrofit {

    @POST("/signup")
    @Headers("accept: application/json", "content-type: application/json")
    fun signUp(@Body postSignUpReq: PostSignUpReq): Call<JsonElement>

    @POST("/login")
    fun login(@Body postLoginReq: PostLoginReq): Call<JsonElement>

    @POST("/login/oauth")
    fun oAuthLogin(@Body postOAuthLoginReq: PostOAuthLoginReq): Call<JsonElement>

    @GET("/token")
    fun refreshJwtToken(@Header("refreshToken") refreshToken: String): Call<JsonElement>

    @GET("/event-items")
    fun getEventItems(): Call<JsonElement>

}
