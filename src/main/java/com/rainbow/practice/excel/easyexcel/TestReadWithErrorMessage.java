package com.rainbow.practice.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.rainbow.practice.excel.easyexcel.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: yzh
 * @Date: 2023/12/19
 * @Description:
 */
@RequestMapping("/easyexcel/TestReadWithErrorMessage")
@RestController
@Slf4j
public class TestReadWithErrorMessage {

    @PostMapping("/upload")
    public List<String> test(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), User.class, new UserListener()).sheet().doRead();

        return null;
    }


    public class UserListener implements ReadListener<User> {

        /**
         * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
         */
        private static final int BATCH_COUNT = 100;
        /**
         * 缓存的数据
         */
        private List<User> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

        @Override
        public void invoke(User data, AnalysisContext context) {
            log.info("解析到一条数据:{}", data);
            cachedDataList.add(data);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            log.info("doAfterAllAnalysed cachedDataList: [{}]", cachedDataList.toString());
        }
    }
}
