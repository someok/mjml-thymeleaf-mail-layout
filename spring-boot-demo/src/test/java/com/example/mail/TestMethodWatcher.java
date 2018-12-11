package com.example.mail;

import org.apache.commons.lang3.StringUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.boot.ansi.AnsiColor;

/**
 * 监控测试方法开始和结束位置，并输出相应提示信息，以方便定位。
 * <p>
 * 需要配合 {@link org.junit.Rule} 使用：
 * <p>
 * {@code &#064;Rule public TestWatcher testWatcher = new TestMethodWatcher();}
 *
 * @author wjx
 * @version 1.0.0
 */
public class TestMethodWatcher extends TestWatcher {

    /**
     * 输出内容带颜色。
     *
     * @param msg
     */
    private void print(String msg) {
        ColorOutput.println(msg);
    }

    private void print(String prefix, String msg) {
        ColorOutput.println(AnsiColor.CYAN, prefix, AnsiColor.BRIGHT_BLUE, msg);
    }

    /**
     * 单元测试方法开始时候输出的内容。
     *
     * @param description {@link Description}
     */
    @Override
    protected void starting(Description description) {
        String methodName = description.getMethodName();
        String className = description.getClassName();
        String msg = String.format("Test starting @ %s#%s", className, methodName);
        System.out.println();
        print(StringUtils.repeat("=", msg.length() + 3));
        print(">> ", msg);
        print(StringUtils.repeat("-", msg.length() + 3));
        System.out.println();
    }

    /**
     * 单元测试方法结束时候输出的内容。
     *
     * @param description {@link Description}
     */
    @Override
    protected void finished(Description description) {
        String methodName = description.getMethodName();
        String className = description.getClassName();
        String msg = String.format("Test finished @ %s#%s", className, methodName);
        System.out.println();
        print(StringUtils.repeat("-", msg.length() + 3));
        print(">> ", msg);
        print(StringUtils.repeat("=", msg.length() + 3));
        System.out.println();
    }
}
