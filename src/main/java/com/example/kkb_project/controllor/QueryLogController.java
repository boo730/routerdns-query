package com.example.kkb_project.controllor;

import com.example.kkb_project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;

import java.time.*;
import java.util.List;


@RestController
@RequestMapping("/")
public class QueryLogController {
    private final JustQueryLogRepository justQueryLogRepository;
    private final NotupstreamQueryLogRepository notupstreamQueryLogRepository;
    private final CachedQueryLogRepository cachedQueryLogRepository;

    Gson gson = new Gson();

    @Autowired
    public QueryLogController(JustQueryLogRepository justQueryLogRepository,
                              NotupstreamQueryLogRepository notupstreamQueryLogRepository,
                              CachedQueryLogRepository cachedQueryLogRepository) {
        this.justQueryLogRepository = justQueryLogRepository;
        this.notupstreamQueryLogRepository = notupstreamQueryLogRepository;
        this.cachedQueryLogRepository = cachedQueryLogRepository;
        }


    @GetMapping("/just")
    public ModelAndView getJustQueryLogs(@RequestParam("startDate") String startDateStr,
                                         @RequestParam("endDate") String endDateStr, Model model) {
        ModelAndView justView = new ModelAndView("just");


        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);

        List<JustQueryLog> justQueryLogs = justQueryLogRepository.findByTBetween(startOfDay, endOfDay);
        String logsJson = gson.toJson(justQueryLogs);
        model.addAttribute("logs", justQueryLogs);

        // Mustache 템플릿 파일의 경로를 반환
        return justView;
    }

    @GetMapping("/notup")
    public List<NotupstreamQueryLog> getAllNotupstreamQueryLogs() {
        return notupstreamQueryLogRepository.findAll();
    }

    @GetMapping("/cached")
    public List<CachedQueryLog> getAllCachedQueryLogs() {
        return cachedQueryLogRepository.findAll();
    }

    /*
    // 오늘의 시작 시각을 가져오는 메서드
    private Date getStartOfDay() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 오늘의 끝 시각을 가져오는 메서드
    private Date getEndOfDay() {
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
    */
}
