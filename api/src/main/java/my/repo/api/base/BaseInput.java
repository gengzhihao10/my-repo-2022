package my.repo.api.base;


import java.io.Serializable;

/**
 * @author gengzhihao
 * @date 2022/10/24 9:33
 * @description 一般入参顶层基类
**/

public abstract class BaseInput implements Serializable {

    private static final long serialVersionUID = -8108503875487739539L;

    public abstract void check();
}
