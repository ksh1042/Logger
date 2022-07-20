package com.roman14.log;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.roman14.log.Logger.LoggerInfo.*;

public class LoggerTest
{
  private Logger logger = null;

  @BeforeEach
  void init()
  {
    Assertions.assertDoesNotThrow(
      () -> {
        Class.forName("com.roman14.log.LoggerPools"); // ClassNotFoundException
      }
    );

    // logger = LoggerPools.getLogger("console");  // 콘솔에 로그 출력
    logger = LoggerPools.getLogger(LoggerPools.MAIN_LOGGER_NAME); // 메인에 로그 출력(기본: 파일)
  }

  @Test
  void test()
  {
    Assertions.assertDoesNotThrow(
      () -> {
        final long startTime = System.currentTimeMillis();

        logger.log(SYSTEM, "system message");
        logger.log(ALERT, "alert message");
        logger.log(INFO, "info message");
        logger.log(DEBUG, "debug message");

        logger.log(SYSTEM, "run time : " + ((double)System.currentTimeMillis() - startTime)/1000 + " sec");
      }
    );
  }

  @AfterEach
  void tearDown()
  {
    Assertions.assertDoesNotThrow(
      () -> {
        if(logger != null) logger.close();
      }
    );
  }
}
