package com.vaqueiro.app_perros.http

import com.vaqueiro.app_perros.models.MessageResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiDogs {
    @GET("breed/hound/images/random/30")
    fun getDogs(): Call<MessageResponse>

}