@echo off
cls
title SQL脚本创建
color 0A
cls
echo.
echo.
echo   =========================================================================
echo   ***************************SQL脚本创建工具*******************************
echo   =========================================================================
echo.
echo			1、选择生成文件类型：DDL(0) 、 DML(1)
echo.
echo			2、输入代表sql内容的文件名;
echo.
echo			3、自动生成文件：时间戳_文件名_DDL(DML).sql;
echo.
echo.
:selectFileType
echo.
set fileType=
set /p fileType=[INFO] 请选择文件类型（0/1）:
if /i "%fileType%"=="1" (
	SET fileType=DML
) else if "%fileType%"=="0" (
	SET fileType=DDL
) else (
	echo.
	echo [ERROR] 未知文件类型[%fileType%]，请重新选择！
	goto selectFileType
)
:inputFileName
echo.
set fileName=
set /p fileName=[INFO] 请输入文件名:
if /i "%fileName%"=="" (
	echo.
	echo [ERROR] 文件名不少于5个字符
	goto inputFileName
)
if "%fileName:~4,1%"=="" (
	echo.
	echo [ERROR] 文件名不少于5个字符
	goto inputFileName
)
echo.
echo [INFO] 程序正在初始化. . .
echo.
call mvn dbdeploy:change-script -Ddbchangefile.name=%fileName%_%fileType%
echo.
set choice=
set /p choice=[INFO] 文件创建成功，按Enter键关闭，其他任意键继续创建 . . .
if /i "%choice%"=="" (
	exit
)
goto selectFileType
