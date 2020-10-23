## 下载依赖包

``` shell
grape install com.devskiller jfairy 0.6.4
grape install org.slf4j slf4j-simple 1.7.25
```

下载的依赖包默认在~/.groovy/grapes下

## 目录结构

独立运行环境目录结构如下

``` shell
httpbuilder
 │  env.bat <-- 环境变量设置批处理
 │  srcript.groovy
 ├─repo     <-- 本地依赖仓库(-Dgrape.root所指定的目录)
 │  └─grapes 
 └─README.md<-- 说明文档
 ├─jre      <-- jre运行环境
 └─README.md<-- 说明文档
```

## 脚本内容

``` groovy
@Grapes([
   @Grab('com.devskiller:jfairy:0.6.4'),
   @Grab('org.slf4j:slf4j-simple:1.7.25')])

import com.devskiller.jfairy.Fairy
import com.devskiller.jfairy.producer.text.TextProducer

TextProducer text = Fairy.create(Locale.default).textProducer()

println "* Lorem ipsum *"
println text.loremIpsum()

println "* Localised text *"
println text.text()

println "* Latin words *"
println text.latinWord(1)
println text.latinWord()
println text.latinWord(190)

println "* Words *"
println text.word(1)
println text.word()
println text.word(190)

println "* Latin sentences *"
println text.latinSentence(1)
println text.latinSentence()
println text.latinSentence(190)

println "* Sentences *"
println text.sentence()
println text.sentence(3)
println text.sentence(190)

println "* Paragraph *"
println text.paragraph()
println text.paragraph(190)
println text.limitedTo(10).paragraph(190)

println "* Random string *"
println text.randomString(10)
println text.randomString(20)
```



## 环境变量设置脚本

``` shell
@echo off

REM
REM ================================================================
REM 创建快捷方式
REM ================================================================
REM
:CreateShorcut

set linkfile=%~dp0%~n0.lnk
IF EXIST "%linkfile%" goto SetDevEnv

set temp_js_file=%TEMP%\%~n0.js
SET _K_LinkFile=%linkfile%
SET _K_TargetPath=%comspec%
SET _K_Arguments=/k ""%~f0""
SET _K_Description=%~n0
SET _K_WorkDir=%~dp0

if exist "%temp_js_file%" del /f "%temp_js_file%"
echo var oWS = new ActiveXObject("WScript.Shell"); >> "%temp_js_file%"
echo var oEnv = oWS.Environment("Process");        >> "%temp_js_file%"
echo var linkfile = oEnv("_K_LinkFile");           >> "%temp_js_file%"
echo var oLink = oWS.CreateShortcut(linkfile);     >> "%temp_js_file%"
echo oLink.TargetPath = oEnv("_K_TargetPath");     >> "%temp_js_file%"
echo oLink.Arguments = oEnv("_K_Arguments");       >> "%temp_js_file%"
echo oLink.Description = oEnv("_K_Description");   >> "%temp_js_file%"
echo oLink.WindowStyle = 1;                        >> "%temp_js_file%"
echo oLink.WorkingDirectory = oEnv("_K_WorkDir");  >> "%temp_js_file%"
echo oLink.Save();                                 >> "%temp_js_file%"
cscript "%temp_js_file%"
del /f "%temp_js_file%"

set temp_js_file=
SET _K_LinkFile=
SET _K_TargetPath=
SET _K_Arguments=
SET _K_Description=
SET _K_WorkDir=

echo 请使用快捷方式 "%linkfile%" 来启动命令行工具
set linkfile=
pause
exit /b 0


REM
REM ================================================================
REM 设置环境变量
REM ================================================================
REM
:SetDevEnv

set JAVA_HOME=jre
set GROOVY_HOME=groovy
set PATH=%PATH%;%JAVA_HOME%\bin;%GROOVY_HOME%\bin;

:Done
```

## 执行命令

运行env.bat产生命令行快捷方式env.lnk，双击打开命令行快捷方式，执行如下命令

``` shell
groovy -Dgrape.root=repo srcipt.groovy
```



