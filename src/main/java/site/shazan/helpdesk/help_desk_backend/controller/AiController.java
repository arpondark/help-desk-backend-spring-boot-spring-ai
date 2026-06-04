package site.shazan.helpdesk.help_desk_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.shazan.helpdesk.help_desk_backend.service.AiService;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AiController {
    private final AiService service;

    @RequestMapping("/response")
    public ResponseEntity<String> getResponse(@RequestBody String queary) {
        return ResponseEntity.ok(service.getResponseFromAssistant(queary));
    }
}
