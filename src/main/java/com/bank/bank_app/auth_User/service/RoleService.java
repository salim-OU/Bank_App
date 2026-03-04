package com.bank.bank_app.auth_User.service;

import com.bank.bank_app.auth_User.model.Role;
import com.bank.bank_app.shared.dto.Response;

import java.util.List;

public interface RoleService {

    Response<Role> createRole(Role roleRequest);

   Response<Role> updateRole(Role roleRequest);

    Response<List<Role>> getAllRoles();

    Response<?> deleteRole(Long id);
}
