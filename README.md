# openwrtIpkDown

用来按需备份openwrt的固件与软件包

使用教程:

---
## linux

直接下载release中的jar包可以省略1-2步

1. 下载代码:git clone https://github.com/pewee-live/openwrtIpkDown.git

2. cd 到项目根目录,编译构建jar包   gradlew bootJar  .如果提示没有执行权限 chmod +x gradlew

3. 进入 build\libs,启动程序  nohup java -jar openwrt-apk-down-0.0.1-SNAPSHOT.jar > nohup.out 2>&1 &

4. 查看8080端口是否启动成功

5. 下载固件: 
    * curl localhost:8080/downloadAllFirmWare?url=https://openwrt.cc/releases/targets/x86/64/  
    * curl localhost:8080/downloadAllFirmWare?url=https://openwrt.cc/releases/targets/bcm27xx/bcm2711/

6. 下载软件: 
    * curl localhost:8080/downloadAllSoft?url=https://openwrt.cc/snapshots/packages/aarch64_cortex-a72/  
    * curl localhost:8080/downloadAllSoft?url=https://openwrt.cc/snapshots/packages/x86_64/ 

7. 下载目录在项目根目录Download下

---
## windows

直接下载release中的jar包可以省略1-2步

1. 下载代码:git clone https://github.com/pewee-live/openwrtIpkDown.git

2. 到项目根目录打开powershell,编译构建jar包   gradlew.bat bootJar

3. powershell进入 build\libs,启动程序  java -jar openwrt-apk-down-0.0.1-SNAPSHOT.jar

4. 查看8080端口是否启动成功

5. 下载固件: 
    * 浏览器访问 localhost:8080/downloadAllFirmWare?url=https://openwrt.cc/releases/targets/x86/64/  
    * 浏览器访问 localhost:8080/downloadAllFirmWare?url=https://openwrt.cc/releases/targets/bcm27xx/bcm2711/

6. 下载软件: 
    * 浏览器访问 localhost:8080/downloadAllSoft?url=https://openwrt.cc/snapshots/packages/aarch64_cortex-a72/  
    * 浏览器访问 localhost:8080/downloadAllSoft?url=https://openwrt.cc/snapshots/packages/x86_64/ 

7. 下载目录在项目根目录Download下
