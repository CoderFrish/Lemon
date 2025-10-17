package me.coderfrish.traium.profiler;

import me.coderfrish.traium.profiler.networkprofiler.NetworkData;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static me.coderfrish.traium.utils.Constants.PROFILER_FOLDER;

public class NetworkProfiler {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
    private static final List<NetworkData> data = new CopyOnWriteArrayList<>();
    private static volatile boolean profiling = false;

    public static void start() {
        if (profiling) return;

        profiling = true;
    }

    public static void stop() {
        if (!profiling) return;

        try {
            String time = LocalDateTime.now().format(formatter);
            if (!PROFILER_FOLDER.exists()) {
                PROFILER_FOLDER.mkdirs();
            }

            File file = new File(PROFILER_FOLDER, "network-profiler-" + time + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileUtils.writeLines(file, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            profiling = false;
        }
    }

    public static void listen(NetworkData data) {
        if (!profiling) return;
        NetworkProfiler.data.add(data);
    }

    public static boolean isProfiling() {
        return profiling;
    }
}
