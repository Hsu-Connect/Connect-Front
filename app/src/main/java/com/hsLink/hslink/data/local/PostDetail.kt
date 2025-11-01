package com.hsLink.hslink.data.local

data class PostDetail(
    val id: String,
    val authorName: String,
    val authorMajor: String,
    val boardType: String,
    val timeAgo: String,
    val title: String,
    val content: String,
    val isMyPost: Boolean,
    val comments: List<Comment>
)

data class Comment(
    val id: String,
    val authorName: String,
    val timeAgo: String,
    val content: String,
    val isMyComment: Boolean
)