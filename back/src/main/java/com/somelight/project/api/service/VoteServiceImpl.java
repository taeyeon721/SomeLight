package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Vote;
import com.somelight.project.db.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService{
    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote getVoteByArticleIdAndUserId(int articleId, int userId) {
        Vote vote = voteRepository.findByArticleIdAndUserId(articleId, userId).orElse(null);
        return vote;
    }
}