package dev.bober.auth.data.remote

import dev.bober.auth.data.remote.dto.LoginUserDto
import dev.bober.auth.data.remote.dto.RegisterUserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/sign-up")
    suspend fun signUp(@Body user : RegisterUserDto) : Result<Unit>

    @POST("auth/sign-in")
    suspend fun signIn(@Body user : LoginUserDto) : Result<Unit>

    @POST("auth/verify-email")
    suspend fun verifyEmail(@Body verifyCode : String) : Result<Unit>

}