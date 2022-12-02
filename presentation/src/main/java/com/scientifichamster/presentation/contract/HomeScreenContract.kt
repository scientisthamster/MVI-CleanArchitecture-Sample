package com.scientifichamster.presentation.contract

import com.scientifichamster.base.UiEffect
import com.scientifichamster.base.UiEvent
import com.scientifichamster.base.UiState
import com.scientifichamster.presentation.model.PostUiModel

/**
 * Contract for the main screen
 */
class HomeScreenContract {

    sealed class Event : UiEvent {
        object OnFetchPosts : Event()
        data class OnPostItemClicked(val post: PostUiModel) : Event()
    }

    data class State(
        val postState: PostsState,
        val selectedPost: PostUiModel? = null
    ) : UiState

    sealed class PostsState {
        object Idle : PostsState()
        object Loading : PostsState()
        data class Success(val posts: List<PostUiModel>): PostsState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}