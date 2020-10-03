package com.jun.cloud.restful.service.impl;

import com.jun.cloud.common.common.annotation.SystemServiceLog;
import com.jun.cloud.restful.dto.CameraDto;
import com.jun.cloud.restful.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jun
 * 创建时间： 2019/8/24
 */
@Service
public class TestServiceImpl implements ITestService {


    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);


    /**
     * 演示使用restTemplate调用接口
     *
     * @param cameraDto 假的
     * @return CameraDto 假的
     */
    @Override
    @SystemServiceLog
    public CameraDto testUrl(CameraDto cameraDto) {
        //String url = "http://111.45.12.5:8080/iss/api/test/get";
        //
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //headers.set("userId", "admin");
        //HttpEntity<?> entity = new HttpEntity<>(cameraDto, headers);
        //
        //ResponseEntity<BaseResponse<CameraDto>> resp = restTemplate.exchange(url, HttpMethod.POST, entity,
        //        new ParameterizedTypeReference<BaseResponse<CameraDto>>(){});
        //
        //if(ObjectUtils.isNotEmpty(resp)){
        //    logger.info("query acs access info is :{}", resp);
        //    BaseResponse<CameraDto> body = resp.getBody();
        //    if(ObjectUtils.isNotEmpty(body)){
        //        if (DefaultErrorCode.SUCCESS.equals(body.getCode())) {
        //            CameraDto data = body.getData();
        //        }
        //    }
        //}
        return null;
    }


}
