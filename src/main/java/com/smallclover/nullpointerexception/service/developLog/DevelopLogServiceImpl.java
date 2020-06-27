package com.smallclover.nullpointerexception.service.developLog;

import com.smallclover.nullpointerexception.mapper.DevelopLogMapper;
import com.smallclover.nullpointerexception.model.DevelopLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Amadeus
 * @date 2020-01-19
 */
@Service
@Slf4j
public class DevelopLogServiceImpl implements DevelopLogService {

    private DevelopLogMapper developLogMapper;

    public DevelopLogServiceImpl(DevelopLogMapper developLogMapper) {
        this.developLogMapper = developLogMapper;
    }

    @Override
    public List<DevelopLog> getAllDevelopLogs() {
        return developLogMapper.selectAllDevelopLogs();
    }

    @Override
    public boolean insertDevelopLog(DevelopLog developLog) {
        long count = developLogMapper.insertDevelopLog(developLog);
        if(count > 0){
            return true;
        }
        return false;
    }
}
