package com.jun.sail.orderauth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OauthCodeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String code;
    private String authentication;
    private Date cdate;


}
