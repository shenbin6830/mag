rem 打包
set prj=mag
rem 删除备份application.yml
copy /y src\main\resources\application.yml .\application.bak
del /q src\main\resources\application.yml
call mvn clean package -Dmaven.test.skip=true 
rem 恢复application.yml
copy /y .\application.bak src\main\resources\application.yml

copy /y target\%prj%-0.0.1-SNAPSHOT.jar \opt\%prj%\%prj%.jar
pause