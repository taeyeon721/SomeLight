먼저 설정에 맞는 jdk를 설치합시다.

- sudo apt-get install openjdk-11-jdk

마리아 db를 설치합시다.

- sudo apt-get install mariadb-server

마리아 db를 시작합니다.

- sudo systemctl start mariadb

마리아 db 설정을 합시다.

- sudo mysql_secure_installation

비밀번호 설정 및 각종 설정을 하는데 잘 읽어보시고 설정하십쇼

마리아 db에 접속합니다.

- mysql -u root -p

* 만약 마리아db에 접속을 할 수 없다면, 권한이 없거나 비밀번호가 틀린것이다.

권한이 없을경우
우선 강제 접속한다.

- sudo mysql -u root

다음 권한분여를 한다.

- GRANT ALL ON _._ TO 'root'@'localhost';

변경사항을 저장한다.

- FLUSH PRIVILEGES;

db를 생성합니다.

- CREATE DATABASE <데이터베이스 이름>;

db를 봅시다.

- SHOW DATABASES;

생성확인후 마리아DB를 나갑시다

- exit

백엔드 프로젝트중 gradlew파일이 있는곳으로 이동한다.

해당 폴더에대한 쓰기 권한 부여

- sudo chmod -R 777 /home/S08P22A109/back

jar 파일 생성.

- ./gradlew bootJar

jar 파일 실행.

- nohup java -jar /home/S08P22A109/back/build/libs/[파일명].jar
