package com.fsck.k9.ui.choosefolder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fsck.k9.Account
import com.fsck.k9.mailstore.DisplayFolder
import com.fsck.k9.mailstore.FolderRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ChooseFolderViewModel(private val folderRepository: FolderRepository) : ViewModel() {
    private val inputFlow = MutableSharedFlow<DisplayMode>(replay = 1)
    private val foldersFlow = inputFlow
        .flatMapLatest { (account, showHiddenFolders) ->
            folderRepository.getDisplayFoldersFlow(account, showHiddenFolders)
        }

    var showHiddenFolders: Boolean = false
        private set

    fun getFolders(): LiveData<List<DisplayFolder>> {
        return foldersFlow.asLiveData()
    }

    fun setShowHiddenFolders(account: Account, show: Boolean) {
        showHiddenFolders = show
        viewModelScope.launch {
            inputFlow.emit(DisplayMode(account, show))
        }
    }
}

private data class DisplayMode(val account: Account, val showHiddenFolders: Boolean)
