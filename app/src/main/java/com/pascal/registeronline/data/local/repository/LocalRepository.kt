package com.pascal.registeronline.data.local.repository

import com.pascal.registeronline.data.local.entity.DraftEntity
import com.pascal.registeronline.data.local.entity.ProfileEntity


interface LocalRepository {
    suspend fun getProfileById(id: Long): ProfileEntity?
    suspend fun getAllProfiles(): List<ProfileEntity>
    suspend fun deleteProfileById(item: ProfileEntity)
    suspend fun insertProfile(item: ProfileEntity)

    suspend fun insertDraft(entity: DraftEntity)
    suspend fun deleteDraft(entity: DraftEntity)
    suspend fun getDraft(): List<DraftEntity>?
    suspend fun clearDraft()
}