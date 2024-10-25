package com.example.kilohealth.feature.profile.presentation

class ProfileContract {
    data class State(
        val isOpenCamera: Boolean = false,
        val isBottomSheet: Boolean = false
    )
    sealed interface Event{
        data object openCamera: Event
    }
    sealed interface Effect{
        sealed interface Nav:Effect{
            data object openCamera: Nav,Effect
        }
    }
}