1.�����ű��ļ�
ִ��createNewDDLChangeFile.bat����DDL�ű��ļ�
ִ��createNewDMLChangeFile.bat����DML�ű��ļ�

**ÿ��DDL���һ���ű��ļ�

�����ű������淶��http://10.67.1.7/wiki/pages/viewpage.action?pageId=4326085

2.��д�ű�
����б��/�ָ�DML��䣬���磺
insert into fw_permission (PERMISSION_ID, NAME, DESCRIPTION)
values ('function_financeManagement_liqsettle', '�ۿ���ϸ����', '')
/
insert into fw_permission_management (NODE_ID, PARENT_NODE_ID, PERMISSION_ID, NODE_NAME, DISPLAY_ORDER, NOTES)
values ('110062', '100004', 'function_financeManagement_liqsettle', '�ۿ���ϸ����', 350, '')
/

**�滻;Ϊ/�ķ���������������ʽ;\r\n���滻Ϊ\r\n/\r\n

3.UNDO���
UNDO����д�������£�
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

bat4.�������Լ��Ŀ�����
������������DBDEPLOY_USR��DBDEPLOY_PWD�������ݿ��û���������
ִ��updateMyDb.

5.���ɻ��ܽű�
����createApplyUndoScripts.bat
Ŀ��ű�Ϊ/DB/target/sql/apply.sql��/DB/target/sql/undo.sql

6.ʹ�ñ�׼�⸲���Լ��Ŀ�����
������׼����Լ�������ı���������DS.STD��DS.DEV
��ַ�ֱ��ǣ�10.67.1.51:1521/DSSTD��10.67.1.98:1521/DS
����resetMyDB.bat

7.һ���ӱ�׼�⸲�Ǹ��˿Ⲣ���������˿⵽����
����:resetAndUpdate2Last


X. ���µ���׼��Ĳ������ݽű��ŵ� DB/src/main/testData ��, ���ܷŵ� sql Ŀ¼��.



