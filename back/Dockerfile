# jdk 11버전으로 설치할것이다.
FROM openjdk:11-jdk

# 현재 폴더의 모든걸 다 복사할것이다.
COPY ./ ./
# 읽기 권한부여
RUN chmod +x ./gradlew
# Gradle로 빌드를 한다.
#RUN ./gradlew clean build --no-daemon
# 스프링부트로 빌드를한다. 두개는 큰 차이가 없다.
RUN ./gradlew bootJar

EXPOSE 8080

# ENTRYPOINT ["java", "-jar", "./build/libs/liargame-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java", "-jar", "./build/libs/ project--0.0.1-SNAPSHOT.jar"]