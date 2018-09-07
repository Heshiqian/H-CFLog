# H-CFLog
H代号作品，Colorful Log ！彩色Log！（Tips: 需要"jansi-1.4"这个jar包，在lib目录下面可以找到）

这是最新版本，不能和Master分支上的版本通用！！！
已做最大修改，Log文件将默认存在运行目录下。Tomcat服务器会存在bin目录下。


基于jansi的封装，使用方法为：

Logger logger = CFLog.logger(//这里填当前类.class，例如 Main.class);

然后就可以使用方法对应输出了；
一共四种颜色，警告为青蓝色，信息显示为蓝色，错误为红色，正常为灰色
输出的内容会保存在本地，
