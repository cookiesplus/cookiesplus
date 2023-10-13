package com.hiklife.log.utils;

import android.content.Context;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.PatternFlattener;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy2;
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;

import java.io.File;

public class XlogUtil {
    public static void initXLog(Integer debugLevel, Context context) {
        Integer logLevel = debugLevel;
        if (debugLevel == 0) {
            logLevel = LogLevel.NONE;
        } else if (debugLevel == 1) {
            logLevel = LogLevel.ALL;
        }
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(logLevel)
                .tag("tag")                                         // 指定 TAG，默认为 "X-LOG"
                .enableThreadInfo()                                    // 允许打印线程信息，默认禁止
                .enableStackTrace(2)                                   // 允许打印深度为 2 的调用栈信息，默认禁止
                .build();
        File externalFilesDir = context.getExternalFilesDir(null);
        AndroidPrinter androidPrinter = new AndroidPrinter(true);         // 通过 android.util.Log 打印日志的打印器
        ConsolePrinter consolePrinter = new ConsolePrinter();             // 通过 System.out 打印日志到控制台的打印器
        PatternFlattener timeFlatter = new PatternFlattener("{d yyyy-MM-dd HH:mm:ss.SSS} {l}/{t}: {m}"); //日志格式化： 2016-11-30 13:00:00.000 D/my_tag: 简单消息
        FilePrinter filePrinter = new FilePrinter                      // 打印日志到文件的打印器
                .Builder(externalFilesDir.getPath() + "/Log")                             // 指定保存日志文件的路径
                .fileNameGenerator(new DateFileNameGenerator())        // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
                .backupStrategy(new FileSizeBackupStrategy2(1048576, 10))             // 指定日志文件备份策略，单个1M，最多10个/每天 足够了，
                .cleanStrategy(new FileLastModifiedCleanStrategy(30 * 24 * 3600 * 1000L)) //30天
                .flattener(timeFlatter)
                .build();

        XLog.init(                                                 // 初始化 XLog
                config,                                                // 指定日志配置，如果不指定，会默认使用 new LogConfiguration.Builder().build()
                androidPrinter,                                        // 添加任意多的打印器。如果没有添加任何打印器，会默认使用 AndroidPrinter(Android)/ConsolePrinter(java)
                consolePrinter,
                filePrinter);

    }
}
