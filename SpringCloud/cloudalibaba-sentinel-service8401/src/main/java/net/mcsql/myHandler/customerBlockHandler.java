package net.mcsql.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import net.mcsql.entitles.CommonResult;

public class customerBlockHandler {

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(2020, "自定义限流处理信息....CustomerBlockHandler");

    }

}
