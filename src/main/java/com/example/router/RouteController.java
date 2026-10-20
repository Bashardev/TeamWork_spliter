package com.example.router;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final MessageRouter router;

    public RouteController(MessageRouter router) {
        this.router = router;
    }

    @PostMapping("/route")
    public RouteResponse route(@RequestBody RouteRequest request) {
        Category category = router.categorize(request.message());
        return new RouteResponse(category.name(), category.teamEmail());
    }
}
