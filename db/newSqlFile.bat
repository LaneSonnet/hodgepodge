@echo off
cls
title SQL�ű�����
color 0A
cls
echo.
echo.
echo   =========================================================================
echo   ***************************SQL�ű���������*******************************
echo   =========================================================================
echo.
echo			1��ѡ�������ļ����ͣ�DDL(0) �� DML(1)
echo.
echo			2���������sql���ݵ��ļ���;
echo.
echo			3���Զ������ļ���ʱ���_�ļ���_DDL(DML).sql;
echo.
echo.
:selectFileType
echo.
set fileType=
set /p fileType=[INFO] ��ѡ���ļ����ͣ�0/1��:
if /i "%fileType%"=="1" (
	SET fileType=DML
) else if "%fileType%"=="0" (
	SET fileType=DDL
) else (
	echo.
	echo [ERROR] δ֪�ļ�����[%fileType%]��������ѡ��
	goto selectFileType
)
:inputFileName
echo.
set fileName=
set /p fileName=[INFO] �������ļ���:
if /i "%fileName%"=="" (
	echo.
	echo [ERROR] �ļ���������5���ַ�
	goto inputFileName
)
if "%fileName:~4,1%"=="" (
	echo.
	echo [ERROR] �ļ���������5���ַ�
	goto inputFileName
)
echo.
echo [INFO] �������ڳ�ʼ��. . .
echo.
call mvn dbdeploy:change-script -Ddbchangefile.name=%fileName%_%fileType%
echo.
set choice=
set /p choice=[INFO] �ļ������ɹ�����Enter���رգ������������������ . . .
if /i "%choice%"=="" (
	exit
)
goto selectFileType
