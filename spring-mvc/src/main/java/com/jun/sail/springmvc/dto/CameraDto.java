package com.jun.sail.springmvc.dto;

import lombok.Data;

/**
 * @author Jun
 * 创建时间： 2019/5/12
 */
@Data
public class CameraDto {

    private String code;

    private Integer direc;

    public CameraDto(String code, Integer direc) {
        this.code = code;
        this.direc = direc;
    }


}
