package dev.bober.auth.data.remote

import dev.bober.auth.data.remote.dto.LoginUserDto
import dev.bober.auth.data.remote.dto.RegisterUserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/sign-up")
    suspend fun signUp(@Body user : RegisterUserDto)

    @POST("/sign-in")
    suspend fun signIn(@Body user : LoginUserDto)

    @POST("/verify-email")
    suspend fun verifyEmail(@Body verifyCode : String)

}