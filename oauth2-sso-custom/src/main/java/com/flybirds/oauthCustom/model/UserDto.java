package com.flybirds.oauthCustom.model;

import lombok.Data;

/**
 * @author flybirds
 * @version 1.0
 **/
@Data
public class UserDto {

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}
