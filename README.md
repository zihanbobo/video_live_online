# video_live_online

#### 介绍
本项目为记录实际开发过程中的一次直播需求开发
#### 系统架构
![输入图片说明](https://images.gitee.com/uploads/images/2020/0119/105034_10363187_1732571.png "系统建构.png")
这是腾讯云直播系统架构图，本系统也希望按照这个架构来实现。
#### 软件架构
基于SpringBoot + JPA框架 + Quertz任务调度 + MySQL数据库 + Redis缓存 + WebSocket实时通讯 + Spring Security实现RBAC权限控制 +JWT token校验 + JpaAuditing + FFMPEG推流 + Nginx+Nginx-RTMP-Module开发的视频在线直播功能。打包安装采用的是docker虚拟化。

#### 安装教程

1.  直接启动即可使用
2.  部署完成后使用OBS进行推流测试 ，第一个视频为桌面推流视频，第二个为接入的央视视频流
![输入图片说明](https://images.gitee.com/uploads/images/2019/1212/172803_10befb15_1732571.png "屏幕截图.png")


#### 使用说明
1.  自行安装nginx和nginx-rtmp-module，其中nginx-rtmp-module地址为 https://github.com/arut/nginx-rtmp-module
2.  自行安装ffmpeg，ffmpeg是实现直播功能的核心部分


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
