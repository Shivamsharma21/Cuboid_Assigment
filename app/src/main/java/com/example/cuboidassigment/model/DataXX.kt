package com.example.cuboidassigment.model

import com.example.cuboidassigment.model.Users

data class DataXX(
    val Is_active: String,
    val city: Any,
    val comments: Int,
    val country: Any,
    val created_date: String,
    val created_time: String,
    val description: String,
    val is_liked: Boolean,
    val is_public: String,
    val likes: Int,
    val location: String,
    val media_type: String,
    val post_id: String,
    val post_images: List<String>,
    val post_name: String,
    val post_status: Any,
    val post_video: String,
    val reaction: Int,
    val state: Any,
    val status: String,
    val user_id: String,
    val users: Users
)