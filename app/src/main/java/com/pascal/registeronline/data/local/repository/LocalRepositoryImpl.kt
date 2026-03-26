package com.pascal.registeronline.data.local.repository

import com.pascal.registeronline.data.local.database.AppDatabase
import com.pascal.registeronline.data.local.entity.DraftEntity
import com.pascal.registeronline.data.local.entity.ProfileEntity
import org.koin.core.annotation.Single

@Single
class LocalRepositoryImpl(
    private val database: AppDatabase,
) : LocalRepository {

    // Profile
    override suspend fun getProfileById(id: Long): ProfileEntity? {
        return database.profileDao().getProfileById(id)
    }

    override suspend fun getAllProfiles(): List<ProfileEntity> {
        return database.profileDao().getAllProfiles()
    }

    override suspend fun deleteProfileById(item: ProfileEntity) {
        return database.profileDao().deleteProfile(item)
    }

    override suspend fun insertProfile(item: ProfileEntity) {
        return database.profileDao().insertProfile(item)
    }

    // Drafts
    override suspend fun insertDraft(entity: DraftEntity) {
        database.draftsDao().insertDraft(entity)
    }

    override suspend fun deleteDraft(entity: DraftEntity) {
        database.draftsDao().deleteDraft(entity)
    }

    override suspend fun getDraft(): List<DraftEntity>? {
        return database.draftsDao().getDraftList()
    }

    override suspend fun clearDraft() {
        return database.draftsDao().clearDraftsTable()
    }
}