package fun.redamancyxun.chinese.backend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.redamancyxun.chinese.backend.entity.Box;
import fun.redamancyxun.chinese.backend.entity.User;
import fun.redamancyxun.chinese.backend.exception.EnumExceptionType;
import fun.redamancyxun.chinese.backend.exception.MyException;
import fun.redamancyxun.chinese.backend.mapper.BoxMapper;
import fun.redamancyxun.chinese.backend.mapper.UserMapper;
import fun.redamancyxun.chinese.backend.service.BoxService;
import fun.redamancyxun.chinese.backend.service.UserService;
import fun.redamancyxun.chinese.backend.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 建议操作相关接口
 * @author Redamancy
 * @description 建议行为相关接口
 * @createDate 2024-11-9 22:39:04
 */
@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BoxMapper boxMapper;

    @Autowired
    private SessionUtils sessionUtils;

    /**
     * 发送盒子
     * @return Box
     * @throws MyException 自定义异常
     */
    @Override
    public Box sendBox(String imageUrl, String content) throws MyException {

        // 获取用户信息
        String userId = sessionUtils.getUserId();
        User user = userService.getUserById(userId);
//        sessionUtils.refreshData(user);

        Box box = Box.builder()
                .createTime(LocalDateTime.now())
                .senderId(user.getId())
                .imageUrl(imageUrl)
                .content(content)
                .build();

        if (boxMapper.insert(box) == 0) {
            throw new MyException(EnumExceptionType.INSERT_FAILED);
        }

        return box;
    }

    /**
     * 在数据库中随机收到盒子
     * @return Box
     * @throws MyException 自定义异常
     */
    @Override
    public Box receiveBox() throws MyException {

        // 获取用户信息
        String userId = sessionUtils.getUserId();
        User user = userService.getUserById(userId);
        QueryWrapper<Box> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("rand()");

        // 随机获取一条盒子，不能收到收到过的
        List<Integer> receiveBoxId = JSON.parseObject(user.getReceiveBoxId(), new TypeReference<List<Integer>>(){});
        if (!receiveBoxId.isEmpty()) {
            queryWrapper.notIn("id", receiveBoxId);
        }
        List<Box> boxList = boxMapper.selectList(queryWrapper);
        if (boxList.isEmpty()) {
            throw new MyException(EnumExceptionType.BOX_NOT_EXIST);
        }
        Box box = boxList.get(0);

        // 更新用户收到的盒子
        receiveBoxId.add(box.getId());
        user.setReceiveBoxId(JSON.toJSONString(receiveBoxId));
        if (userMapper.updateById(user) == 0) {
            throw new MyException(EnumExceptionType.UPDATE_FAILED);
        }

        return box;
    }

    /**
     * 根据盒子id查找盒子
     * @return Box
     * @throws MyException 自定义异常
     */
    @Override
    public Box getBoxById(Integer id) throws MyException {

        QueryWrapper<Box> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return boxMapper.selectOne(queryWrapper);
    }

//    /**
//     * 根据用户id查找收到盒子
//     * @return List<Box>
//     * @throws MyException 自定义异常
//     */
//    @Override
//    public List<Box> getReceivedBoxesByUserId(String userId) throws MyException {
//
//        if (userId == null) {
//            throw new MyException(EnumExceptionType.DATA_IS_NULL);
//        }
//        if (userService.getUserById(userId) == null) {
//            throw new MyException(EnumExceptionType.USER_NOT_EXIST);
//        }
//
//        return boxMapper.selectList(new QueryWrapper<Box>().eq("receiver_id", userId));
//    }
}
