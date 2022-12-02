package com.scientifichamster.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.scientifichamster.base.BaseViewModel
import com.scientifichamster.common.Mapper
import com.scientifichamster.common.Resource
import com.scientifichamster.domain.entity.CommentEntityModel
import com.scientifichamster.domain.usecase.GetPostCommentsUseCase
import com.scientifichamster.presentation.contract.DetailsScreenContract
import com.scientifichamster.presentation.contract.DetailsScreenContract.CommentsState.*
import com.scientifichamster.presentation.contract.DetailsScreenContract.Effect.ShowError
import com.scientifichamster.presentation.contract.DetailsScreenContract.Event.OnFetchPostComments
import com.scientifichamster.presentation.model.CommentUiModel
import com.scientifichamster.presentation.model.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val commentsUseCase: GetPostCommentsUseCase,
    private val commentsMapper: Mapper<CommentEntityModel, CommentUiModel>
) : BaseViewModel<DetailsScreenContract.Event, DetailsScreenContract.State, DetailsScreenContract.Effect>() {

    override fun createInitialState(): DetailsScreenContract.State {
        return DetailsScreenContract.State(
            commentsState = Idle
        )
    }

    override fun handleEvent(event: DetailsScreenContract.Event) {
        when (event) {
            is OnFetchPostComments -> {
                fetchPostComments(event.post)
            }
        }
    }

    /**
     * Fetch post comments
     */
    private fun fetchPostComments(post: PostUiModel?) {
        viewModelScope.launch {
            commentsUseCase.execute(post?.id)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        Resource.Loading -> {
                            setState { copy(commentsState = Loading) }
                        }
                        Resource.Empty -> {
                            setState { copy(commentsState = Idle) }
                        }
                        is Resource.Success -> {
                            setState {
                                copy(
                                    commentsState = Success(
                                        commentsMapper.fromList(it.data)
                                    )
                                )
                            }
                        }
                        is Resource.Error -> {
                            setEffect {
                                ShowError(it.exception.message)
                            }
                        }
                    }
                }
        }
    }
}