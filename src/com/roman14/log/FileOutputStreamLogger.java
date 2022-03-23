package com.roman14.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>파일 로깅용 클래스</b>
 * <pre>
 *  - 파일과 파일스트림을 통해 쓰기를 수행한다.
 * </pre>
 * @author MDH
 * @since 2021. 6. 25.
 */
public class FileOutputStreamLogger extends AbstractDefaultLogger
{
  public static final String DEFAULT_LOG_FILE_PATH = getDefaultLogFileWindowPath();

  public static final String DEFAULT_LOG_FILE_NAME = FileOutputStreamLogger.class.getSimpleName();

  private File logFile;

  private FileOutputStream fos;

  private FileOutputStreamLogger(String logFilePath, String logFileName)
  {
    try
    {
      if(logFilePath == null || logFilePath.isEmpty()) new IllegalArgumentException("logFileName not be empty");
      if(logFileName == null || logFileName.isEmpty()) new IllegalArgumentException("logFileName not be empty");

      final StringBuffer filePath = new StringBuffer();

      filePath.append(logFilePath);
      filePath.append(logFileName);
      filePath.append('_');
      filePath.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
      filePath.append(".log");

      this.logFile = new File(filePath.toString());
      this.fos = new FileOutputStream(this.logFile);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  protected void write(String str)
  {
    try
    {
      fos.write(str.getBytes());
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void close() throws Exception
  {
    if(fos != null) fos.close();
  }

  /**
   * 윈도우 환경에 맞는 기본 로그파일 경로를 구현
   * @return
   */
  private static String getDefaultLogFileWindowPath()
  {
    StringBuilder sb = new StringBuilder();
    try
    {
      sb.append("C:\\Users\\");
      sb.append(Inet4Address.getLocalHost().getHostName());
      sb.append("\\Desktop\\");
    }
    catch (UnknownHostException e)
    {
      e.printStackTrace();
    }
    return sb.toString();
  }

  static final class Builder
  {
    private String logFilePath;
    private String logFileName;

    public Builder(){}

    public Builder logFilePath(String logFilePath)
    {
      this.logFilePath = logFilePath;
      return this;
    }
    public Builder logFileName(String logFileName)
    {
      this.logFileName = logFileName;
      return this;
    }

    public FileOutputStreamLogger build()
    {
      return new FileOutputStreamLogger(this.logFilePath, this.logFileName);
    }
  }


}
