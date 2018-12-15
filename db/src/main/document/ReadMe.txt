1.创建脚本文件
执行createNewDDLChangeFile.bat创建DDL脚本文件
执行createNewDMLChangeFile.bat创建DML脚本文件

**每个DDL语句一个脚本文件

完整脚本开发规范：http://10.67.1.7/wiki/pages/viewpage.action?pageId=4326085

2.编写脚本
用正斜杠/分割DML语句，形如：
insert into fw_permission (PERMISSION_ID, NAME, DESCRIPTION)
values ('function_financeManagement_liqsettle', '扣款明细导出', '')
/
insert into fw_permission_management (NODE_ID, PARENT_NODE_ID, PERMISSION_ID, NODE_NAME, DISPLAY_ORDER, NOTES)
values ('110062', '100004', 'function_financeManagement_liqsettle', '扣款明细导出', 350, '')
/

**替换;为/的方法：查找正则表达式;\r\n，替换为\r\n/\r\n

3.UNDO语句
UNDO语句编写方法如下：
CREATE TABLE FOO (
FOO_ID INTEGER NOT NULL
,FOO_VALUE VARCHAR(30)
)
/
ALTER TABLE FOO ADD CONSTRAINT PK_FOO PRIMARY KEY (FOO_ID)
/
--//@UNDO
DROP TABLE FOO
/

bat4.更新我自己的开发库
创建环境变量DBDEPLOY_USR，DBDEPLOY_PWD保存数据库用户名和密码
执行updateMyDb.

5.生成汇总脚本
运行createApplyUndoScripts.bat
目标脚本为/DB/target/sql/apply.sql和/DB/target/sql/undo.sql

6.使用标准库覆盖自己的开发库
建立标准库和自己开发库的本地命名：DS.STD、DS.DEV
地址分别是：10.67.1.51:1521/DSSTD、10.67.1.98:1521/DS
运行resetMyDB.bat

7.一键从标准库覆盖个人库并且升级个人库到最新
运行:resetAndUpdate2Last


X. 更新到标准库的测试数据脚本放到 DB/src/main/testData 中, 不能放到 sql 目录下.



