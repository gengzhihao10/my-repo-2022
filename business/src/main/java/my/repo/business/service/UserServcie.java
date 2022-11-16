//package my.repo.business.service;
//
//import lombok.extern.slf4j.Slf4j;
//import my.repo.api.base.RestResponse;
//import my.repo.api.enums.ResponseCodeEnum;
//import my.repo.api.user.input.RestUserCommand;
//import my.repo.api.user.output.UserOutput;
//import my.repo.business.converter.UserConverter;
//import my.repo.common.utils.IDUtil;
//import my.repo.common.utils.RedisUtil;
//import my.repo.infrastructure.DO.UserDO;
//import my.repo.infrastructure.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//
//@Slf4j
//@Component
//public class UserServcie {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserConverter  userConverter;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
////    public  queryUserById(Long id){
////        //todo 完善逻辑，增加出入参的转换
////        userRepository.getById(id);
////        RestUserCommand restUserCommand = new RestUserCommand();
////        RestUserPageQuery restUserPageQuery = new RestUserPageQuery();
////    }
//
//    public RestResponse<UserOutput> insertUser(RestUserCommand restUserCommand){
//
//        UserDO userDO = userConverter.convert(restUserCommand);
//
//
//        //雪花算法生成id
//        userDO.setId(IDUtil.getId(null,null));
//        userDO.setCreateTime(new Date());
//        userDO.setUpdateTime(new Date());
//
//        boolean result = userRepository.save(userDO);
//        if (!result){
//            log.error("新增用户信息失败，用户名为 {}", userDO.getUsername());
//            return RestResponse.fail(ResponseCodeEnum.FAILED);
//        }
//        return RestResponse.ok();
//    }
//
//}
