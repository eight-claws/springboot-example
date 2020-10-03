package com.jun.cloud.common.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 代码生成器演示例子
 * </p>
 *
 * @author jun
 * @since 2018-09-12
 */
public class MpGenerator {


    private static final String[] TABLE_NAMES = {"device", "region"};

    static String author = "余生君";

    /**
     * 父工程下还分模块，在这里配置
     */
    private static final String MODULE_NAME = "/cloud-modules/cloud-service";

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator generator = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + MODULE_NAME + "/src/main/java");
        gc.setAuthor(author);
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        generator.setGlobalConfig(gc);


        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:postgresql://localhost:5432/mp_db?useUnicode=true&characterEncoding=utf8");
        dsc.setSchemaName("public");
        dsc.setDriverName("org.postgresql.Driver");
        dsc.setUsername("postgres");
        dsc.setPassword("postgres");
        generator.setDataSource(dsc);


        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.jun.cloud.service");
        pc.setModuleName("test");//看是否在parent下再分模块
        pc.setMapper("mapper");
        generator.setPackageInfo(pc);


        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义xml文件位置
                return projectPath + "/cloud-start/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);
        generator.setTemplate(new TemplateConfig().setXml(null));


        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntitySerialVersionUID(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntityBuilderModel(true);
        strategy.setInclude(TABLE_NAMES);
        generator.setStrategy(strategy);


        //选择了非velocity，如freemarker引擎需要在这里指定，注意pom依赖必须有！
        //generator.setTemplateEngine(new FreemarkerTemplateEngine());

        generator.execute();
    }

}
