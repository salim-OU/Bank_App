package com.bank.bank_app.auth_User.service;


import com.bank.bank_app.auth_User.dto.LoginRequest;
import com.bank.bank_app.auth_User.dto.LoginResponse;
import com.bank.bank_app.auth_User.dto.RegistrationRequest;
import com.bank.bank_app.auth_User.dto.ResetPasswordRequest;
import com.bank.bank_app.shared.dto.Response;

public interface AuthService {
    Response<String > register(RegistrationRequest request);
    Response<LoginResponse> login(LoginRequest loginRequest);
    Response<? > forgetPassword(String email);
    Response<? > updatePasswordViaResetCode(ResetPasswordRequest resetPasswordRequest);
}