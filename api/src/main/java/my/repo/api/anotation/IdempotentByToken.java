package my.repo.api.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author gengzhihao
 * @date 2022/12/7 9:51
 * @description 根据token进行幂等校验
**/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IdempotentByToken {
}
