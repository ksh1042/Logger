package com.roman14.log;

import com.roman14.log.info.LoggerInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>기본 로거 추상클래스</b>
 * <pre>
 *  - 기본 로그 출력방식을 정의한다. 별도의 출력형식에 대한 정의가 필요한 경우 해당 클래스를 견본으로 사용한다.
 *  - 해당 추상 클래스를 상속받아 출력할 대상을 선택하여 구현한다.
 *  - 해당 추상 클래스를 상속받은 클래스의 인스턴스 사용 후 close() 메서드 호출을 통해 자원을 반환을 하여야 한다.
 * </pre>
 * @author MDH
 * @since 2021. 8. 2.
 */
public abstract class AbstractDefaultLogger implements Logger
{
  /**
   * !!DEBUG ONLY!!
   * IDE 콘솔 출력 여부
   */
  protected boolean isDebug;

  private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

  public void log(LoggerInfo info, String str)
  {
    final StringBuilder sb = new StringBuilder();
    sb.append('[');
    sb.append(SDF.format(new Date()));
    sb.append("] [");
    sb.append(this.getCalledClassInfo());
    sb.append("] [");
    sb.append(info.name());
    sb.append("] ");
    sb.append(str);
    sb.append('\n');

    this.write(sb.toString());
  }

  /**
   * Logger에 쓰기를 직접적으로 수행할 쓰기 추상 메서드로, 로그 기록 시 직접적으로 쓰기에 대한 내용을 정의한다.
   * 상속받은 클래스의 로그 쓰기 객체를 통해 쓰기를 구현한다.
   * @param str - 기록될 로그의 내용을 입력
   */
  protected abstract void write(String str);

  /**
   * !!DEBUG ONLY!!
   * 현재 호출되는 클래스(파일명) 및 라인 번호를 반환한다.
   * @return - 스택트레이스를 확인하여 현재 호출된 클래스명과 라인번호를 반환
   */
  private synchronized String getCalledClassInfo()
  {
    final StackTraceElement [] elements = Thread.currentThread().getStackTrace();
    // 0 : Thread
    // 1 : current Class, current Method
    // 2 : current Class, log Method
    // 3 : runtimeClass, The method that actually calls the log method.
    return elements[3].getFileName() + ':' + elements[3].getLineNumber();
  }

  public boolean isDebug()
  {
    return this.isDebug;
  }

  public void setIsDebug(boolean flag)
  {
    this.isDebug = flag;
  }

}
