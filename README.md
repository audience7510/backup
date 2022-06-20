# mysql_backup
整个备份操作，是对mysqldump命令的操作，具体命令如下：  
mysqldump -uroot -proot --host=127.0.0.1 --port=3306 --skip-extended-insert --skip-lock-tables --skip-add-locks --set-gtid-purged=off test | gzip > /usr/local/sqlbackup/test.sql.gz  
释义：-u后面跟用户名  
-p后面跟密码  
--host跟ip  
--port跟端口  
--skip-extended-insert一条数据对应一条insert语句导出，目的是防止一条sql语句数据太大，在导入的时候可能回出现mysql server连接丢失或者超时  
--skip-lock-tables跳过锁定表。执行数据库备份时，MySQL 默认锁定库中所有表，以确保在备份过程中数据不会更改。
但实际操作的时候锁定会导致卡死，所以跳过锁定，也可能存在数据量过大，备份时间过长，一直锁表，导致业务sql阻塞。不锁表在备份时会造成数据不一致。
由于我不需要强一致性，所以加了这个参数。  
--skip-add-locks跳过加锁，与--skip-lock-tables作用类似。  
--set-gtid-purged=off该参数作用：https://www.cnblogs.com/ybyqjzl/p/12428039.html