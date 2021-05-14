package com.example.techmecook.recyclerview.click_listeners

import com.example.techmecook.model.comment.Comment

interface CommentClickListener {
    fun onClick(comment: Comment)
}