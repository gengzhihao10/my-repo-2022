package my.repo.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import my.repo.common.exception.CreateIdException;

/**
 * @author gengzhihao
 * @date 2022/11/7 10:50
 * @description 通过hutool调用雪花算法
 **/
@Slf4j
public class IDUtil {

    private static Snowflake snowflake;

    /**
     * @author gengzhihao
     * @date 2022/11/7 10:53
     * @description 获取雪花算法id
     **/
    //todo synchronized可以优化，这个关键字影响性能
    public static synchronized long getId(Long machineId, Long dataCenterId) {
        Snowflake snowflake = getSnowFlake(machineId, dataCenterId);
        return snowflake.nextId();
    }


    /**
     * @author gengzhihao
     * @date 2022/11/7 11:46
     * @description 获取当前datacenter和machine下的SnowFlake实例
    **/
    private static Snowflake getSnowFlake(Long machineId, Long dataCenterId){
        //当前datacenter和machine的snowflake已生成，则直接返回
        if (null != snowflake){
            return snowflake;
        }
        //两者都为没传
        if (ObjectUtil.isNull(machineId) && ObjectUtil.isNull(dataCenterId)) {
            snowflake = cn.hutool.core.util.IdUtil.getSnowflake();
        }
        //只传了机器码
        else if (ObjectUtil.isNotNull(machineId) && ObjectUtil.isNull(dataCenterId)) {
            snowflake = cn.hutool.core.util.IdUtil.getSnowflake(machineId);
        }
        //两个都传了过来
        else if (ObjectUtil.isNotNull(machineId) && ObjectUtil.isNotNull(dataCenterId)) {
            snowflake = cn.hutool.core.util.IdUtil.getSnowflake(machineId,dataCenterId);
        }
        //如果都不是，那传参就是错误了，抛出异常
        else {
            log.error("构造雪花id传参错误");
            //todo 加一个错误码枚举
            throw new CreateIdException("构造雪花id传参错误");
        }
        return snowflake;
    }

    public static void main(String[] args) {
        Long id = getId(null,null);
        log.info("雪花id为 {}",id);
    }
}

