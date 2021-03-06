package com.roman14.log;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>로거 인스턴스 풀</b>
 * <pre>
 *  - 로거 인스턴스 관리를 위한 클래스
 *  - Class.forName() 호출을 통해 정적 블록 수행 필요
 * </pre>
 * @author MDH
 * @since 2021. 7. 1.
 */
public class LoggerPools
{
  public static final String MAIN_LOGGER_NAME = "main";

  private static final Map<String, Logger> loggerPools =  new HashMap<>();

  static
  {
    loggerPools.put(MAIN_LOGGER_NAME, new FileOutputStreamLogger
                                            .Builder()
                                            .logFilePath(FileOutputStreamLogger.DEFAULT_LOG_FILE_PATH)
                                            .logFileName("log")
                                            .build());

    loggerPools.put("console", new ConsoleLogger());

    // -> 이 곳에 원하는 새로운 로거 추가. 혹은 putLogger 메서드를 통해 외부에서 등록.
  }

  public static Logger getLogger(String key)
  {
    return loggerPools.get(key);
  }

  public static void putLogger(String key, Logger logger)
  {
    synchronized (LoggerPools.class)
    {
      loggerPools.put(key, logger);
    }
  }

}
