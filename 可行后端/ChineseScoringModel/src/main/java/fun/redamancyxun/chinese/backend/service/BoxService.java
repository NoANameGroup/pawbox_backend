package fun.redamancyxun.chinese.backend.service;

import fun.redamancyxun.chinese.backend.entity.Box;

import java.util.List;

/**
 * 建议操作相关接口
 * @author Redamancy
 * @description 建议行为相关接口
 * @createDate 2024-11-9 22:39:04
 */
public interface BoxService {

    // 发送盒子
    Box sendBox(String imageUrl, String content);

    // 收到盒子
    Box receiveBox();

    // 查找盒子
    Box getBoxById(Integer boxId);
}
