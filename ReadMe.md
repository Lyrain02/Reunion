# Reunion
This is an android App which engages in helping people find their lost family.
# 安卓页面
## 整体框架流程
以下文件名中的书写格式：/前面是layout 后面是activity      √
+ 首页 activity_initial/initialActivity       √
+ 登录页面 activity_login/loginActivity     √
+ 注册页面 activity_register/registerActivity       √
+ 主页面整体（包括下面的tab navigation）activity_main/MainActivity      √
+ 广场页面：(ui)square文件夹 fragment_square/SquareFragment     √
  + 根据上面的tablayout分为广场A和广场B
  + 广场A页面：fragment_first/FirstFragment       √
  + 广场B界面：fragment_second/SecondActivity         √
+ 发布公告界面 (ui)send文件夹 fragment_send/SendFragment      √
+ 我的信息界面 (ui)myinfo文件夹 fragment_info/MyinfoFragment     √
+ 广场详情页1（jump from广场）activity_a_detail/aDetailActivity       √
+ 广场详情页2 (jump from广场）activity_b_detail/bDetailActivity      √
+ 提交公告1（jump from公告）【未完成】activity_submit1/Submit1Activity
+ 提交公告2（jump from公告）【未做】(文件未创建)
+ 我的信息界面(jump from我的)【未完成】(mydetail文件夹)activity_my_detail/MyDetailActivity      ×
+ 我的寻人信息页面(jump from我的)(myfind文件夹)activity_myfind/MyFindActivity        √
   + 寻人信息详情页面(jump from 我的寻人信息页面) activity_my_find_detail/MyFindDetailActivity √
+ 我的寻亲信息界面(jump from我的)(myrelative文件夹)【未做】
  + 寻亲信息详情页面(jump from 我的寻亲信息页面)【未做】
+ 我提供的有关线索界面(jump from我的)【未做】
+ 匹配结果有关页面(jump from 寻人/寻亲页面) activity_match_result/MatchResultActivity
+ 帮助界面(jump from我的)【未做】
------
## 流程
1. 进入登录界面
2. 进入主广场
   1. 浏览广场1和广场2 点击进入详情页
3. 进入发布公告
    1. 发布寻亲公告/寻人广告
4. 进入我的页面
    1. 修改头像
    2. 修改用户名
    3. 实名认证
-----------

