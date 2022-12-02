package com.scientifichamster.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.scientifichamster.base.BaseViewModel
import com.scientifichamster.common.Mapper
import com.scientifichamster.common.Resource
import com.scientifichamster.domain.entity.PostEntityModel
import com.scientifichamster.domain.usecase.GetPostsUseCase
import com.scientifichamster.presentation.contract.HomeScreenContract
import com.scientifichamster.presentation.contract.HomeScreenContract.Effect.ShowError
import com.scientifichamster.presentation.contract.HomeScreenContract.PostsState.*
import com.scientifichamster.presentation.model.PostUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postsUseCase: GetPostsUseCase,
    private val postMapper: Mapper<PostEntityModel, PostUiModel>
) : BaseViewModel<HomeScreenContract.Event, HomeScreenContract.State, HomeScreenContract.Effect>() {

    override fun createInitialState(): HomeScreenContract.State {
        return HomeScreenContract.State(
            postState = Idle,
            selectedPost = null
        )
    }

    override fun handleEvent(event: HomeScreenContract.Event) {
        TODO("Not yet implemented")
    }

    /**
     * Fetch posts
     */
    private fun fetchPosts() {
        viewModelScope.launch {
            postsUseCase.execute(null)
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        Resource.Loading -> {
                            setState { copy(postState = Loading) }
                        }
                        Resource.Empty -> {
                            setState { copy(postState = Idle) }
                        }
                        is Resource.Success -> {
                            setState { copy(postState = Success(posts = postMapper.fromList(it.data))) }
                        }
                        is Resource.Error -> {
                            setEffect { ShowError(message = it.exception.message) }
                        }
                    }
                }
        }
    }

    /**
     * Set selected post item
     */
    private fun setSelectedItem(post: PostUiModel?) {
        setState { copy(selectedPost = post) }
    }
}