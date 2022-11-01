package my.repo.common.utils;

import cn.hutool.core.util.ObjectUtil;
import my.repo.common.exception.InputIsNullException;

public class InputCheckUtil {


    public static void checkInput(Object input,String message){
        if (ObjectUtil.isEmpty(input)){
            throw new InputIsNullException(message);
        }
    }
}
