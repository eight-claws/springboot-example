package com.jun.cloud.restful.dto;

import com.jun.cloud.common.common.errorcode.CountGroupErrorCode;
import com.jun.cloud.common.common.errorcode.DefaultErrorCode;
import com.jun.cloud.restful.common.constant.RestfulConstants;
import com.jun.cloud.restful.common.enums.SexEnum;
import com.jun.sail.sailvalidate.annotation.EnumCheck;
import com.jun.sail.sailvalidate.constant.ValidatorConstant;
import com.jun.sail.sailvalidate.group.Save;
import com.jun.sail.sailvalidate.group.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * 注解@GroupSequence指定分组校验的顺序，即先校验Save分组的，如果不通过就不会去做后面分组的校验了
 */
@Data
@ApiModel("用户添加修改对象")
@GroupSequence({Save.class, Update.class, UserDto.class})
public class UserDto {

    @NotEmpty(message = DefaultErrorCode.ARGUMENTS_MISSING, groups = Update.class)
    @ApiModelProperty(notes = "用户id", example = "2441634686")
    private String id;

    @NotEmpty(message = DefaultErrorCode.ARGUMENTS_MISSING, groups = Save.class)
    @Length(min = 3, max = RestfulConstants.NAME_MAX_LENGTH, message = CountGroupErrorCode.USER_NAME_SIZE_IS_ILLEGAL)
    @Pattern(regexp = ValidatorConstant.LEGAL_CHARACTER, message = CountGroupErrorCode.USER_NAME_IS_ILLEGAL)
    @ApiModelProperty(notes = "用户姓名", example = "张飞")
    private String name;

    @NotNull(message = CountGroupErrorCode.AGE_IS_ILLEGAL)
    @Min(value = 5L, message = CountGroupErrorCode.AGE_IS_ILLEGAL, groups = Save.class)
    @ApiModelProperty(notes = "年龄", example = "12")
    private Integer age;

    @ApiModelProperty(notes = "手机号", example = "18108195635")
    @Pattern(regexp = ValidatorConstant.MOBILE)
    private String phone;

    @ApiModelProperty(notes = "出生日期，格式如2018-08-08", example = "2018-08-08")
    private LocalDate birthday;

    @ApiModelProperty(notes = "性别,1-男，2-女，3-未知", example = "2")
    @EnumCheck(required = false, enumClass = SexEnum.class)
    private Integer sex;

    /**
     * 级联校验只需要添加@Valid
     * 注解@ConvertGroup用于分组的转换，只能和@Valid一起使用。（一般用不到）
     */
    @Size(max = RestfulConstants.DIRECTION_MAX_NUMBER, message = CountGroupErrorCode.DIRECTION_NUMBER_IS_ILLEGAL)
    @ApiModelProperty(notes = "包含的方向列表")
    @Valid
    //@ConvertGroup(from = Search.class, to = Update.class)
    private List<DirectionDto> list;

}

