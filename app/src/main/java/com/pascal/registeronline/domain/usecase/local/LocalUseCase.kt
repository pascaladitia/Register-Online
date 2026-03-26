package com.pascal.registeronline.domain.usecase.local

import com.pascal.registeronline.data.local.entity.DraftEntity

interface LocalUseCase {
    suspend fun insertDraft(entity: DraftEntity)
    suspend fun deleteDraft(entity: DraftEntity)
    suspend fun getDraft(): List<DraftEntity>?
    suspend fun clearDraft()
}