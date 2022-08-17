package com.flybirds.oauth.service;

import com.flybirds.oauth.model.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flybirds
 * @version 1.0
 **/
@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //将来连接数据库根据账号查询用户信息
        UserDto userDto = new UserDto();

        userDto.setPassword(new BCryptPasswordEncoder().encode("123456"));
        userDto.setUsername("admin");
        if(userDto == null){
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }
        //根据用户的id查询用户的权限
        List<String> permissions = new ArrayList<>();
        permissions.add("sys:user:add");
        permissions.add("sys:user:edit");
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails = User.withUsername(userDto.getUsername())
                                .password(userDto.getPassword())
                                .authorities(permissionArray).build();
        return userDetails;
    }
}
