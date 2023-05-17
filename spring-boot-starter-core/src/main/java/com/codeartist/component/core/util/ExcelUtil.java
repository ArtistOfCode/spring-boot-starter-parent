package com.codeartist.component.core.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <b>EasyExcel操作工具类</b>
 * <br/><br/>
 * <p>使用read方法来进行读Excel，使用write方法支持写Excel。</p>
 * <p>支持从文件、输入流读取，支持写入文件、输出流。</p>
 * <pre>
 *
 *     ExcelUtil.read("file.xlsx", ExcelDemo.class, data -> {
 *         // 处理读取数据data
 *     });
 *
 *     ExcelUtil.write("file.xlsx", ExcelDemo.class, consumer -> {
 *         // 写入数据data，可以重复写入避免内存占用
 *         consumer.accept(data);
 *         consumer.accept(data);
 *                ...
 *         consumer.accept(data);
 *     });
 * </pre>
 *
 * @author 艾江南
 * @date 2021/6/22
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExcelUtil {

    // 读Excel

    public static <T> void read(File file, Class<T> clazz, Consumer<List<T>> consumer) {
        read(EasyExcel.read(file), clazz, consumer);
    }

    public static <T> void read(String path, Class<T> clazz, Consumer<List<T>> consumer) {
        read(EasyExcel.read(path), clazz, consumer);
    }

    public static <T> void read(InputStream inputStream, Class<T> clazz, Consumer<List<T>> consumer) {
        read(EasyExcel.read(inputStream), clazz, consumer);
    }

    public static <T> void read(ExcelReaderBuilder builder, Class<T> clazz, Consumer<List<T>> consumer) {
        ExcelReader excelReader = null;
        try {
            ExcelReadListener<T> listener = new ExcelReadListener<>(consumer);
            if (clazz == Map.class) {
                excelReader = builder.registerReadListener(listener).build();
            } else {
                excelReader = builder.head(clazz).registerReadListener(listener).build();
            }

            excelReader.readAll();
        } finally {
            if (excelReader != null) {
                excelReader.close();
            }
        }
    }

    // 写Excel

    public static <T> void write(File file, Class<T> clazz, Consumer<Consumer<Collection<?>>> writer) {
        write(EasyExcel.write(file), clazz, writer);
    }

    public static <T> void write(String path, Class<T> clazz, Consumer<Consumer<Collection<?>>> writer) {
        write(EasyExcel.write(path), clazz, writer);
    }

    public static <T> void write(OutputStream outputStream, Class<T> clazz, Consumer<Consumer<Collection<?>>> writer) {
        write(EasyExcel.write(outputStream), clazz, writer);
    }

    public static void write(File file, List<String> head, Consumer<Consumer<Collection<?>>> writer) {
        write(EasyExcel.write(file), head, writer);
    }

    public static void write(String path, List<String> head, Consumer<Consumer<Collection<?>>> writer) {
        write(EasyExcel.write(path), head, writer);
    }

    public static void write(OutputStream outputStream, List<String> head, Consumer<Consumer<Collection<?>>> writer) {
        write(EasyExcel.write(outputStream), head, writer);
    }

    public static <T> void write(ExcelWriterBuilder builder, Class<T> clazz, Consumer<Consumer<Collection<?>>> writer) {
        ExcelWriter excelWriter = null;
        try {
            excelWriter = builder.head(clazz).build();
            WriteSheet sheet = EasyExcel.writerSheet().build();

            final ExcelWriter finalExcelWriter = excelWriter;
            writer.accept(data -> finalExcelWriter.write(data, sheet));
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    public static void write(ExcelWriterBuilder builder, List<String> head, Consumer<Consumer<Collection<?>>> writer) {
        ExcelWriter excelWriter = null;
        try {
            List<List<String>> headList = head.stream().map(Collections::singletonList).collect(Collectors.toList());
            excelWriter = builder.head(headList).build();
            WriteSheet sheet = EasyExcel.writerSheet().build();

            final ExcelWriter finalExcelWriter = excelWriter;
            writer.accept(data -> finalExcelWriter.write(data, sheet));
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    public static class ExcelReadListener<T> extends AnalysisEventListener<T> {

        private final List<T> dataList;
        private final int batchCount;
        private final Consumer<List<T>> consumer;

        public ExcelReadListener(Consumer<List<T>> consumer) {
            this(100, consumer);
        }

        public ExcelReadListener(int batchCount, Consumer<List<T>> consumer) {
            this.batchCount = batchCount;
            this.dataList = new ArrayList<>();
            this.consumer = consumer;
        }

        @Override
        public void invoke(T data, AnalysisContext context) {
            dataList.add(data);
            if (dataList.size() >= batchCount) {
                consumer.accept(dataList);
                dataList.clear();
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            consumer.accept(dataList);
        }
    }
}
