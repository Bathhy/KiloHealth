package com.example.kilohealth.feature.profile.presentation

class ProfileContract {
    data class State(
        val isOpenCamera: Boolean = false,
        var isBottomSheet: Boolean = false
    )

    sealed interface Event {
        data object OpenGallery : Event
        data object OpenBottomSheet : Event
        data object OpenCamera : Event
    }

    sealed interface Effect {
        sealed interface Nav : Effect {
            data object openCamera : Nav, Effect
        }
    }
}