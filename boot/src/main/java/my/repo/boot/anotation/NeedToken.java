package my.repo.boot.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * @author gengzhihao
 * @date 2022/11/11 13:33
 * @description 此注解表示接口需要通过token校验
**/

@Retention(RetentionPolicy.RUNTIME)
public @interface NeedToken {
}
