package com.scientifichamster.presentation.contract

import com.scientifichamster.base.UiEffect
import com.scientifichamster.base.UiEvent
import com.scientifichamster.base.UiState
import com.scientifichamster.presentation.model.CommentUiModel
import com.scientifichamster.presentation.model.PostUiModel

/**
 * Contract for details screen
 */
class DetailsScreenContract {

    sealed class Event : UiEvent {
        data class OnFetchPostComments(val post: PostUiModel?) : Event()
    }

    data class State(
        val commentsState: CommentsState
    ) : UiState

    sealed class CommentsState {
        object Idle : CommentsState()
        object Loading : CommentsState()
        data class Success(val comments: List<CommentUiModel>) : CommentsState()
    }

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }
}