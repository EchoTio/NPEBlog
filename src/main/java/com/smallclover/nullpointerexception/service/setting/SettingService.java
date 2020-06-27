package com.smallclover.nullpointerexception.service.setting;

import com.smallclover.nullpointerexception.model.Setting;

/**
 * @Author: Amadeus
 * @Date: 2020/5/24 13:37
 */
public interface SettingService {

    public Setting getAllSetting();

    public boolean updateSystemSetting(Setting setting);
}
