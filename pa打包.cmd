rem ���
set prj=mag
rem ɾ������application.yml
copy /y src\main\resources\application.yml .\application.bak
del /q src\main\resources\application.yml
call mvn clean package -Dmaven.test.skip=true 
rem �ָ�application.yml
copy /y .\application.bak src\main\resources\application.yml

copy /y target\%prj%-0.0.1-SNAPSHOT.jar \opt\%prj%\%prj%.jar
pause