package com.zentask.identity.mapper;

import com.zentask.config.UserPrincipal;
import com.zentask.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    UserPrincipal toUserPrincipal(User user);
}
