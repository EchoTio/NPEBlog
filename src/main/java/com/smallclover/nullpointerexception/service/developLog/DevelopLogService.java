package com.smallclover.nullpointerexception.service.developLog;

import com.smallclover.nullpointerexception.model.DevelopLog;

import java.util.List;

/**
 * @author Amadeus
 * @date 2020-01-19
 */
public interface DevelopLogService {

    List<DevelopLog> getAllDevelopLogs();

    boolean insertDevelopLog(DevelopLog developLog);
}
