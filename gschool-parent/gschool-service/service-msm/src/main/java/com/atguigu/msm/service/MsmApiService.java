package com.atguigu.msm.service;

/**
 * MsmApiService
 *
 * @Author: wd
 * @CreateTime: 2020-04-20
 * @Description:
 */
public interface MsmApiService {
    boolean sendCode(String phone, String code);
}