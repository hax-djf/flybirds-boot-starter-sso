package com.flybirds.oauthCustom.model;

import lombok.Data;

/**
 * @author flybirds
 * @version 1.0
 **/
@Data
public class PermissionDto {

    private String id;
    private String code;
    private String description;
    private String url;
}
