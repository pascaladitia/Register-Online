package com.pascal.registeronline.domain.usecase.local

import com.pascal.registeronline.data.local.entity.DraftEntity
import com.pascal.registeronline.data.local.repository.LocalRepository
import org.koin.core.annotation.Single

@Single
class LocalUseCaseImpl(
    private val repository: LocalRepository,
) : LocalUseCase {

    override suspend fun insertDraft(entity: DraftEntity) {
        repository.insertDraft(entity)
    }

    override suspend fun deleteDraft(entity: DraftEntity) {
        repository.deleteDraft(entity)
    }

    override suspend fun getDraft(): List<DraftEntity>? {
        return repository.getDraft()
    }

    override suspend fun clearDraft() {
        return repository.clearDraft()
    }
}