# Build stage: openjdk:21-slim 기반에 Gradle 설치
FROM openjdk:21-slim AS build

# 필수 패키지 설치 (wget, unzip)
RUN apt-get update && apt-get install -y wget unzip

# Gradle 버전 지정
ENV GRADLE_VERSION=8.9

# Gradle 설치: 공식 배포판을 다운로드해서 /opt/gradle 에 설치
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -O gradle.zip && \
    unzip gradle.zip && \
    rm gradle.zip && \
    mv gradle-${GRADLE_VERSION} /opt/gradle

# 환경변수 설정: Gradle 실행 파일을 PATH에 추가
ENV PATH="/opt/gradle/bin:${PATH}"

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 파일 복사: build.gradle, settings.gradle, 그리고 gradlew(이미 존재한다면)
COPY build.gradle settings.gradle gradlew gradlew.bat ./
# (만약 gradle/wrapper 폴더가 있다면 COPY gradle/wrapper/ ./gradle/wrapper/ 도 추가)

# 소스 코드 복사
COPY src ./src

# gradlew 파일에 실행 권한 부여 (이미 gradle이 설치되었으므로 wrapper를 굳이 안 써도 됨)
RUN chmod +x gradlew

# 빌드 실행: gradlew를 사용해 bootJar 작업 수행  
# (gradlew가 있으면 gradlew가 내부적으로 Gradle을 자동 다운로드하지만, 
#  여기서는 직접 설치한 Gradle을 사용하므로 gradlew가 없이 gradle 명령어를 사용해도 됨)
RUN gradle clean bootJar

# Runtime stage: 경량 OpenJDK 21 이미지로 최종 JAR 파일만 포함
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
