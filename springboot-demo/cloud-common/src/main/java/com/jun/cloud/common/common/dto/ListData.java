package com.jun.cloud.common.common.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Jun
 * 创建时间： 2019/12/14
 */
@Api(value = "列表数据")
public class ListData<T> {

    @ApiModelProperty(value = "列表数据")
    private List<T> list;

    @ApiModelProperty(value = "列表数据总数")
    private Long total;

    /**
     * <select id="queryConsumeRecord" resultType="com.hikvision.ctm05vcs.web.dto.ConsumeRecordDto">
     *         SELECT
     *         v.name as visitorName,
     *         v.receptionist_name as receptionistName,
     *         v.receptionist_org_name as receptionistOrgName,
     *         c.consume_time as consumeTime,
     *         c.money as money,
     *         c.serial_number as serialNumber,
     *         c.card_no as cardNo,
     *         c.send_time as sendTime,
     *         c.charge_type_name as chargeType,
     *         c.times as times,
     *         c. device_name as deviceName,
     *         c.merchant_name as merchantName,
     *         c.remark as remark
     *         FROM
     *         visitor v
     *         inner join visitor_consume c on c.visitor_id = v.visitor_id
     *         WHERE
     *         c.consume_time between #{queryDto.startTime} and #{queryDto.endTime}
     *         <if test="queryDto.visitorName != null and queryDto.visitorName != ''">
     *             and v.name like CONCAT('%',#{queryDto.visitorName},'%')  ESCAPE '/'
     *         </if>
     *         <if test="queryDto.receptionistName != null and queryDto.receptionistName != ''">
     *             and v.receptionist_name like CONCAT('%',#{queryDto.receptionistName},'%')  ESCAPE '/'
     *         </if>
     *         <if test="queryDto.cardNo != null and queryDto.cardNo != ''">
     *             AND c.card_no = #{queryDto.cardNo}
     *         </if>
     *         <if test="queryDto.chargeType != null and queryDto.chargeType != ''">
     *             AND c.charge_type = #{queryDto.chargeType}
     *         </if>
     *         order by v.name desc, c.consume_time desc
     *         limit #{queryDto.pageSize} OFFSET #{queryDto.pageNo}
     *     </select>
     */
    /**
     *  @Override
     *     public PageData<ConsumeRecordDto> queryConsume(ConsumeQueryDto reqDto){
     *         Integer pageNo = reqDto.getPageNo();
     *         Integer pageSize = reqDto.getPageSize();
     *         PageData<ConsumeRecordDto> pageData = new PageData<>();
     *         pageData.setPageNo(pageNo);
     *         pageData.setPageSize(pageSize);
     *
     *         if(ObjectUtils.isNotEmpty(reqDto.getVisitorName())){
     *             reqDto.setVisitorName(ObjectUtils.escapeLikeParam(reqDto.getVisitorName()));
     *         }
     *         if(ObjectUtils.isNotEmpty(reqDto.getReceptionistName())){
     *             reqDto.setReceptionistName(ObjectUtils.escapeLikeParam(reqDto.getReceptionistName()));
     *         }
     *         reqDto.setPageNo((pageNo - 1) * reqDto.getPageSize());
     *
     *         Long total = baseMapper.countConsumeRecord(reqDto);
     *         pageData.setTotal(total);
     *         List<ConsumeRecordDto> consumeRecord = new ArrayList<>();
     *         pageData.setList(consumeRecord);
     *         if(total != null && total> 0) {
     *             consumeRecord = baseMapper.queryConsumeRecord(reqDto);
     *             pageData.setList(consumeRecord);
     *         }
     *         return pageData;
     *     }
     */
}
