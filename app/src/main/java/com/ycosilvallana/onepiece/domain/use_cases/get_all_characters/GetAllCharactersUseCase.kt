package com.ycosilvallana.onepiece.domain.use_cases.get_all_characters

import androidx.paging.PagingData
import com.ycosilvallana.onepiece.data.repository.Repository
import com.ycosilvallana.onepiece.domain.model.Character
import kotlinx.coroutines.flow.Flow

class GetAllCharactersUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<Character>> {
        return repository.getAllCharacters()
    }
}