package com.tr.obss.jss.jss_marketplace.service.impl;

import com.tr.obss.jss.jss_marketplace.model.Role;
import com.tr.obss.jss.jss_marketplace.model.RoleType;
import com.tr.obss.jss.jss_marketplace.repository.RoleRepository;
import com.tr.obss.jss.jss_marketplace.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRole(RoleType type) {
        Objects.requireNonNull(type, "role cannot be null");
        return roleRepository.findByName(type);
    }
}
