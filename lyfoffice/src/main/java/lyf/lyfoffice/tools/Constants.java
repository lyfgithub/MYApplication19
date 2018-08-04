package lyf.lyfoffice.tools;

import android.os.Environment;

import java.io.File;

public final class Constants {

    //可扩展的存储目录\BB
    public static final String ROOT_DIR = Environment.getExternalStorageDirectory() + File.separator + "BB";
    //日志的目录
    public static final String LOG_DIR = ROOT_DIR + File.separator + "log";
    //下载的目录
    public static final String DOWNLOAD_DIR = ROOT_DIR + File.separator + "download";
    //缓存目录
    public static final String CACHE_DIR = ROOT_DIR + File.separator + "cache";

    //日志文件名称
    public static final String LOG_NAME = "crash.log";
}