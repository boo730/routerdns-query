package com.example.kkb_project.controllor;

import com.example.kkb_project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.*;
import java.util.List;


@RestController
@RequestMapping("/")
public class QueryLogController {
    private final JustQueryLogRepository justQueryLogRepository;
    private final NotupstreamQueryLogRepository notupstreamQueryLogRepository;
    private final CachedQueryLogRepository cachedQueryLogRepository;

    @Autowired
    public QueryLogController(JustQueryLogRepository justQueryLogRepository,
                              NotupstreamQueryLogRepository notupstreamQueryLogRepository,
                              CachedQueryLogRepository cachedQueryLogRepository) {
        this.justQueryLogRepository = justQueryLogRepository;
        this.notupstreamQueryLogRepository = notupstreamQueryLogRepository;
        this.cachedQueryLogRepository = cachedQueryLogRepository;
    }


    @GetMapping("/just")
    public ModelAndView getJustQueryLogs(@RequestParam("date") String dateStr,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "50") int size, Model model) {

        ModelAndView justView = new ModelAndView("just");

        LocalDate date = LocalDate.parse(dateStr);

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        int startIndex = page * size;

        List<JustQueryLog> justQueryLogs = justQueryLogRepository.findByTBetween(startOfDay, endOfDay);

        int endIndex = Math.min(startIndex + size, justQueryLogs.size());
        List<JustQueryLog> pageJustQueryLogs = justQueryLogs.subList(startIndex, endIndex);

        model.addAttribute("logs", pageJustQueryLogs);

        return justView;
    }

    @GetMapping("/notup")
    public ModelAndView getNotupstreamQueryLogs(@RequestParam("date") String dateStr,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "50") int size, Model model) {

        ModelAndView notupstreamView = new ModelAndView("notup");

        LocalDate date = LocalDate.parse(dateStr);

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        int startIndex = page * size;

        List<NotupstreamQueryLog> notupstreamQueryLogs = notupstreamQueryLogRepository.findByTBetween(startOfDay, endOfDay);

        int endIndex = Math.min(startIndex + size, notupstreamQueryLogs.size());
        List<NotupstreamQueryLog> pageNotupstreamQueryLogs = notupstreamQueryLogs.subList(startIndex, endIndex);

        model.addAttribute("logs", pageNotupstreamQueryLogs);

        return notupstreamView;

    }

    @GetMapping("/cached")
    public ModelAndView getCachedQueryLogs(@RequestParam("date") String dateStr,
                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "50") int size, Model model) {

        ModelAndView cachedView = new ModelAndView("cached");

        LocalDate date = LocalDate.parse(dateStr);

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        int startIndex = page * size;

        List<CachedQueryLog> cachedQueryLogs = cachedQueryLogRepository.findByTBetween(startOfDay, endOfDay);

        int endIndex = Math.min(startIndex + size, cachedQueryLogs.size());
        List<CachedQueryLog> pageCachedQueryLogs = cachedQueryLogs.subList(startIndex, endIndex);

        model.addAttribute("logs", pageCachedQueryLogs);

        return cachedView;

    }
}
