package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarTController {
    private final AvatarService avatarService;

    AvatarTController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }
    @GetMapping("/getAvatars")
    public List<Avatar> getAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        return avatarService.findAllAvatars(pageNumber, pageSize);
    }

}
