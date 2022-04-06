package com.roman14.log;

import com.roman14.log.info.LoggerInfo;

/**
 * <b>로그 수행 인터페이스</b>
 * <pre>
 * </pre>
 * @author MDH
 * @since 2021. 8. 2.
 */
public interface Logger extends AutoCloseable
{
  void log(LoggerInfo info, String str);
  void close() throws Exception;

  enum LoggerInfo{
    SYSTEM, ERROR, FATAL, ALERT, DEBUG, INFO
  }
}
