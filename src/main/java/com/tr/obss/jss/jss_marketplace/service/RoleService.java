package com.tr.obss.jss.jss_marketplace.service;

import com.tr.obss.jss.jss_marketplace.model.Role;
import com.tr.obss.jss.jss_marketplace.model.RoleType;

public interface RoleService {

    Role getRole(RoleType type);
}
