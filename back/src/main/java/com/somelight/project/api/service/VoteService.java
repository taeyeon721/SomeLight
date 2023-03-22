package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Vote;
import org.springframework.stereotype.Service;

@Service
public interface VoteService {
    Vote getVoteByArticleIdAndUserId(int articleId, int userId);
    void deleteVoteList(int articleId);
}
