package com.example.mail;

import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;

/**
 * 使用 {@link AnsiOutput} 输出带颜色定义的字符串到 {@link System#out#println(String)}
 *
 * @author wjx
 * @version 1.0.0
 */
public class ColorOutput {
    static {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
    }

    /**
     * 默认输出字符串颜色为 {@link AnsiColor#CYAN}.
     *
     * @param out 字符串内容
     */
    public static void println(String out) {
        System.out.println(AnsiOutput.toString(AnsiColor.CYAN, out));
    }

    /**
     * 支持定义颜色的字符串输出。
     *
     * @param color {@link AnsiColor} 颜色定义
     * @param out   字符串内容
     */
    public static void println(AnsiColor color, String out) {
        System.out.println(AnsiOutput.toString(color, out));
    }

    /**
     * 可灵活定义不同部分颜色的输出方式
     *
     * @param elements 可以是 {@link org.springframework.boot.ansi.AnsiElement} 格式的内容，例如颜色、样式、字符串等
     */
    public static void println(Object... elements) {
        System.out.println(AnsiOutput.toString(elements));
    }
}
