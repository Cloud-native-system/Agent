package cloudNative.Agent.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import cloudNative.Agent.services.AgentServiceInterface;

@RestController
@RequestMapping("/agent")
public class AgentController {
    private final AgentServiceInterface agentService;

    public AgentController(AgentServiceInterface agentService) {
        this.agentService = agentService;
    }

    @PostMapping("")
    public String handleRequest(@RequestBody String prompt) {
        return agentService.request(prompt);
    }

}
