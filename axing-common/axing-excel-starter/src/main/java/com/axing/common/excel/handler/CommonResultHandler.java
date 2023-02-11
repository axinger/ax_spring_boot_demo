package com.axing.common.excel.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.axing.common.excel.ExcelUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date: 2021/7/28 10:22
 */
@Getter
public abstract class CommonResultHandler<T> implements ResultHandler<T> {

    protected final HttpServletResponse response;

    protected final ExcelWriter writer;

    protected WriteSheet sheet;

    protected final List<T> rowDataList;

    public CommonResultHandler(HttpServletResponse response, Class<? extends T> clazz) throws IOException {
        this.response = response;
        this.writer = EasyExcel.write(response.getOutputStream(), clazz).build();
        rowDataList = new ArrayList<>(1);
        this.initSheet();
    }


    public void initSheet(){
        this.sheet = EasyExcel.writerSheet().build();
    }

    @Override
    @SneakyThrows
    public void handleResult(ResultContext<? extends T> resultContext){
        T obj = resultContext.getResultObject();
        rowDataList.add(processing(obj));
        writer.write(rowDataList,sheet);
        rowDataList.clear();
    }

    public abstract T processing(T t);
}
