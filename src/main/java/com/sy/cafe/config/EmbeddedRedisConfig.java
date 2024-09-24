package com.sy.cafe.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.embedded.RedisServer;

@Slf4j
@Configuration
public class EmbeddedRedisConfig {
    private RedisServer redisServer;
    @Value("${spring.redis.port}")
    private int port;

    @PostConstruct
    public void redisServer() throws IOException {
        redisServer = new RedisServer(isRedisRunning() ? findAvailablePort() : port);
        try{
            redisServer.start();
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @PreDestroy
    public void stopRedis(){
        if(redisServer != null){
            redisServer.stop();
        }
    }

    // Embedded Redis가 현재 실행중인지 확인
    private boolean isRedisRunning() throws IOException {
        return isRunning(executeGrepProcessCommand(port));
    }

    // 현재 서버에서 사용 가능한 포트 조회
    public int findAvailablePort() throws IOException {

        for (int port = 10000; port <= 65535; port++) {
            Process process = executeGrepProcessCommand(port);
            if (!isRunning(process)) {
                log.info("Redis Port : " + port);
                return port;
            }
        }
        throw new IllegalArgumentException("Not Found Available port: 10000 ~ 65535");
    }


    // 해당 port를 사용 중인 프로세스 확인하는 sh 실행 (window, mac/linux 다 가능)
    private Process executeGrepProcessCommand(int port) throws IOException {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        if(os.contains("win")){
            String command = String.format("netstat -nao | find \"LISTEN\" | find \"%d\"", port);
            String[] shell = {"cmd.exe", "/y", "/c", command};
            return Runtime.getRuntime().exec(shell);
        }else if(os.contains("linux") || os.contains("mac")) {
            String command = String.format("netstat -nat | grep LISTEN|grep %d", port);
            String[] shell = {"/bin/sh", "-c", command};
            return Runtime.getRuntime().exec(shell);
        } else{
            throw new RuntimeException("해결되지 않은 OS 입니다. executeGrepProcessCommand() os 명령어 코드를 추가해주세요.");
        }
    }

    // 해당 Process가 현재 실행 중인지 확인
    private boolean isRunning(Process process) {
        String line;
        StringBuilder pidInfo = new StringBuilder();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            while ((line = input.readLine()) != null) {
                pidInfo.append(line);
            }

        } catch (Exception ignored) {
        }

        return !StringUtils.hasText(pidInfo.toString());
    }
}

