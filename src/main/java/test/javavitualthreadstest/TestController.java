package test.javavitualthreadstest;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String getThreadName() {
        // 현재 요청을 처리하는 thread의 이름을 반환 blocking 되지 않음
        return Thread.currentThread().toString();
    }

    @GetMapping("/block")
    public String getBlockedResponse() throws InterruptedException {
        // Thread sleep 0.5초
        // 비즈니스 로직 처리에 thread가 blocking 되는 환경 가정
        Thread.sleep(500);
        return "OK";
    }

    @GetMapping("/query")
    public String queryAndReturn() {
        // 0.5초 sleep 후 query 수행
        return jdbcTemplate.queryForList("SELECT SLEEP(0.5);").toString();
    }

}
