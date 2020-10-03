package com.jun.cloud.service.service.impl;

import com.jun.cloud.service.common.enums.SubTableEnum;
import com.jun.cloud.service.mapper.SubTableMapper;
import com.jun.sail.utils.check.ObjectUtils;
import com.jun.sail.utils.datetime.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Jun
 * 创建时间： 2019/7/7
 */
@Service
public class SubTableServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(SubTableServiceImpl.class);

    @Autowired
    private SubTableMapper subTableMapper;


    /**
     * 一周创建一张表
     */
    private static final Integer SUB_TABLE_NUMBER_FOR_WEEKLY = 1;

    /**
     * 一次需要创建4张表
     */
    private static final Integer SUB_TABLE_NUMBER = 4;

    /**
     * 创建分表的sql模板
     * CREATE TABLE #table-name#_#table-suffix# (LIKE #table-name# INCLUDING ALL) INHERITS (#table-name#);" +
     * "ALTER TABLE #table-name#_#table-suffix# ADD CONSTRAINT #table-name#_#table-suffix#_ck1 CHECK (#query-param# >=
     * DATE '#start-time#' AND #query-param# < DATE '#end-time#');" +
     * "CREATE RULE insert_#table-name#_#table-suffix# AS ON INSERT TO #table-name# WHERE (#query-param# >= DATE '#start-time#'" +
     * "AND #query-param# < DATE '#end-time#') DO INSTEAD INSERT INTO #table-name#_#table-suffix# VALUES (NEW .*);
     */
    private static final String CREATE_TEMPLATE = "CREATE TABLE {0}_{1} (LIKE {0} INCLUDING ALL) INHERITS ({0});" +
            "ALTER TABLE {0}_{1} ADD CONSTRAINT {0}_{1}_ck1 CHECK ({2} >= TIMESTAMPTZ ''{3}'' AND {2} < TIMESTAMPTZ ''{4}'');" +
            "CREATE RULE insert_{0}_{1} AS ON INSERT TO {0} WHERE ({2} >= TIMESTAMPTZ ''{3}''" +
            "AND {2} < TIMESTAMPTZ ''{4}'') DO INSTEAD INSERT INTO {0}_{1} VALUES (NEW .*);\n";


    /***
     * 获取上次分表日期的sql模板
     */
    private static final String SELECT_TEMPLATE = "SELECT substring(r.rulename,8,100) as partitionName FROM ((pg_rewrite r JOIN pg_class c " +
            "ON ((c.oid = r.ev_class))) LEFT JOIN pg_namespace n ON ((n.oid = c.relnamespace))) " +
            "WHERE (r.rulename <> '_RETURN'::name) AND (n.nspname='public') AND (c.relname=" +
            "'#table-name#') order by substring(r.rulename,8,100) desc limit 1";

    private final static String PLACEHOLDER_TABLE_NAME = "#table-name#";


    public void subTableForMonth(SubTableEnum subTableEnum) {

    }

    /**
     * 执行分表的入口
     * 为方便维护，每张表的后缀都是每周一
     */
    public void subTableForWeek(SubTableEnum subTableEnum) {
        LocalDate currentMonday = LocalDate.now().with(DayOfWeek.MONDAY);

        logger.info("[sub table] start check {} subTable ", subTableEnum.getTableName());
        LocalDate lastSubTableDate = getLastSubTableDate(subTableEnum.getTableName());

        if (lastSubTableDate == null) {
            //不存在上次分表，开始执行分表
            logger.info("[sub table]{} is not exists,current time is {}", subTableEnum.getTableName(), LocalDateTime.now());
            executeSubTable(subTableEnum, currentMonday, SUB_TABLE_NUMBER);
        } else {
            //检查分表是否充足
            logger.info("[sub table] last subTable date is {}", lastSubTableDate);
            if (lastSubTableDate.isBefore(currentMonday.plusWeeks(SUB_TABLE_NUMBER - 1))) {
                if (currentMonday.isBefore(lastSubTableDate)) {
                    int partitionNum = (int) lastSubTableDate.until(currentMonday.plusWeeks(SUB_TABLE_NUMBER - 1), ChronoUnit.WEEKS);
                    executeSubTable(subTableEnum, lastSubTableDate.plusWeeks(1), partitionNum);
                } else {
                    //此处考虑平台长时间未运行
                    executeSubTable(subTableEnum, currentMonday, SUB_TABLE_NUMBER);
                }
            } else {
                logger.info("[sub table] enough");
            }
        }
        logger.info("[sub table] execute end! subTable name is {}", subTableEnum.getTableName());
    }

    /**
     * 执行分表
     */
    private void executeSubTable(SubTableEnum subTableEnum, LocalDate startDate, Integer partitionNum) {
        if (subTableEnum == null || startDate == null) {
            logger.error("[sub table] table name or start date must not be empty");
            return;
        }
        StringBuilder sql = new StringBuilder();
        LocalDate end = startDate.plusWeeks(SUB_TABLE_NUMBER_FOR_WEEKLY);
        String tableSuffix;
        for (int i = 1; i <= partitionNum; i++) {
            //表的日期后缀
            tableSuffix = DateTimeUtil.formatLocalDate(startDate, DateTimeUtil.DATE_FORMAT_yyyy_MM_dd);
            logger.info("[sub table] start execute,subTable name is:{}_{} ", subTableEnum.getTableName(), tableSuffix);

            //{0}：表名   {1}：时间后缀  {2}：分表字段  {3}：开始时间   {4}：结束时间
            sql.append(MessageFormat.format(CREATE_TEMPLATE, subTableEnum.getTableName(), tableSuffix, subTableEnum.getSubCol(),
                    DateTimeUtil.formatLocalDate(startDate, DateTimeUtil.DATE_FORMAT_yyyy_MM_dd),
                    DateTimeUtil.formatLocalDate(end, DateTimeUtil.DATE_FORMAT_yyyy_MM_dd)));

            // 为下个分表设置时间
            startDate = startDate.plusWeeks(SUB_TABLE_NUMBER_FOR_WEEKLY);
            end = end.plusWeeks(SUB_TABLE_NUMBER_FOR_WEEKLY);
        }
        subTableMapper.createSubTable(sql.toString());
    }


    /***
     * 获取最新的分表日期
     */
    private LocalDate getLastSubTableDate(String tableName) {
        String sql = SELECT_TEMPLATE.replace(PLACEHOLDER_TABLE_NAME, tableName);

        String lastSubTableName = subTableMapper.findBySql(sql);
        int index = tableName.length();
        if (ObjectUtils.isNotEmpty(lastSubTableName) && lastSubTableName.length() > index) {
            //去掉表名部分
            String date = lastSubTableName.substring(index + 1);
            return DateTimeUtil.parseLocalDate(date, DateTimeUtil.DATE_FORMAT_yyyy_MM_dd);
        } else {
            logger.error("[sub table] fetch {} last subTable date is error", tableName);
        }
        return null;
    }


}
