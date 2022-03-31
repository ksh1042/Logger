package com.roman14.log.test;

import com.roman14.log.Logger;
import com.roman14.log.LoggerPools;
import static com.roman14.log.Logger.LoggerInfo.*;

public class LoggerMain
{
  private static final Logger logger;

  static
  {
    try
    {
      Class.forName("com.roman14.log.LoggerPools");
    }
    catch ( ClassNotFoundException e )
    {
      e.printStackTrace();
    }

    logger = LoggerPools.getLogger(LoggerPools.MAIN_LOGGER_NAME);  // 파일에 로그 출력
    //logger = LoggerPools.getLogger("console");  // 콘솔에 로그 출력
  }

  public static void main(String[] args)
  {
    try
    {
      final long startTime = System.currentTimeMillis();

      logger.log(SYSTEM, "system message");
      logger.log(ALERT, "alert message");
      logger.log(INFO, "info message");
      logger.log(DEBUG, "debug message");

      logger.log(SYSTEM, "run time : " + ((double)System.currentTimeMillis() - startTime)/1000 + " sec");

      logger.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
