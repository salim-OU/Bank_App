package com.bank.bank_app.auth_User.service;

import com.bank.bank_app.auth_User.dto.UpdatePasswordRequest;
import com.bank.bank_app.auth_User.dto.UserDto;
import com.bank.bank_app.auth_User.model.User;
import com.bank.bank_app.shared.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User getCurrentLoggedInUser();

    Response<UserDto> getMyProfile();

    Response<Page<UserDto>> getAllUsers(int page, int size);

    Response<?> updatePassword(UpdatePasswordRequest updatePasswordRequest);
    Response<?> uploadProfilePicture(MultipartFile file);

 //   Response<?> uploadProfilePictureToS3(MultipartFile file);
}
