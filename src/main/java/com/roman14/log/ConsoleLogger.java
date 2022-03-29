package com.roman14.log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * <b>기본 로거 추상클래스</b>
 * <pre>
 *  - System.out을 통해 콘솔로 출력한다.
 * </pre>
 * @author MDH
 * @since 2021. 8. 17.
 */
public final class ConsoleLogger extends AbstractDefaultLogger
{
  private final BufferedWriter bw;

  public ConsoleLogger()
  {
    this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
  }

  @Override
  protected void write(String str)
  {
    try
    {
      bw.write(str);
      bw.flush();
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void close() throws Exception
  {
    if(bw != null) bw.close();
  }
}
