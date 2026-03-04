package com.bank.bank_app.auth_User.dto;


import com.bank.bank_app.account.dto.AccountDto;
import com.bank.bank_app.auth_User.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @JsonIgnore
    private String password;
    private String profilePicture;
    private boolean active ;
    private List<Role> roles;
    @JsonManagedReference
    private List<AccountDto>accounts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt ;
}
