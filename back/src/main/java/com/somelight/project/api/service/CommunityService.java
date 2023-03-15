package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Story;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommunityService {
    List<Story> getUserStories(int user_id);
    Story registerStory(String email, String content, int result);
}
