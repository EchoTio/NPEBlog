package com.smallclover.nullpointerexception.service.setting.impl;

import com.smallclover.nullpointerexception.mapper.SettingMapper;
import com.smallclover.nullpointerexception.model.Setting;
import com.smallclover.nullpointerexception.service.setting.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Amadeus
 * @Date: 2020/5/24 13:46
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingMapper settingMapper;

    @Override
    public Setting getAllSetting() {
        return settingMapper.getSystemSetting();
    }

    @Override
    public boolean updateSystemSetting(Setting setting) {
        return settingMapper.updateSystemSetting(setting) > 0;
    }
}
