package my.repo.api.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author gengzhihao
 * @date 2022/11/11 13:33
 * @description 此注解表示接口需要通过token校验
**/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedToken {
}
