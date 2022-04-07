# Logger
- java7
- Log4j 등 로그 라이브러리를 사용할 수 없는 폐쇄적 환경에 로그를 사용하기 위한 프로젝트
- main(파일쓰기), console(콘솔출력) 두 방식을 기본 지원


## Stacks
<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>



## example
```java
package com.roman14.log.test;

import com.roman14.log.Logger;
import com.roman14.log.LoggerPools;
import static com.roman14.log.Logger.LoggerInfo.*;

public class LoggerMain
{
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
  }

  public static void main(String[] args)
  {
    // logger = LoggerPools.getLogger("console");  // 콘솔에 로그 출력
    try(Logger logger = LoggerPools.getLogger(LoggerPools.MAIN_LOGGER_NAME))
    {
      final long startTime = System.currentTimeMillis();

      logger.log(SYSTEM, "system message");
      logger.log(ALERT, "alert message");
      logger.log(INFO, "info message");
      logger.log(DEBUG, "debug message");

      logger.log(SYSTEM, "run time : " + ((double)System.currentTimeMillis() - startTime)/1000 + " sec");

    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}

```
## result
```
[2022.03.29 14:30:58] [LoggerMain.java:32] [SYSTEM] system message
[2022.03.29 14:30:58] [LoggerMain.java:33] [ALERT] alert message
[2022.03.29 14:30:58] [LoggerMain.java:34] [INFO] info message
[2022.03.29 14:30:58] [LoggerMain.java:35] [DEBUG] debug message
[2022.03.29 14:30:58] [LoggerMain.java:37] [SYSTEM] run time : 0.0 sec

```

## history
- 22.04.07 안전한 자원반환을 위한 Logger 인터페이스 클래스의 Autocloseable 상속
- 22.03.31 public LoggerInfo 열거형 클래스를 Logger 인터페이스의 이너클래스로 변경
- 22.03.29 프로젝트 Graddle 추가
