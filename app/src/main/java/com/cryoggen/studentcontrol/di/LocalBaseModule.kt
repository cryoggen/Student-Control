package com.cryoggen.studentcontrol.di

import android.content.Context
import com.cryoggen.data.source.local.StudentDatabase
import com.cryoggen.data.source.local.getDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalBaseModule {

    @Provides
    @Singleton
    fun provideStudentDatabase(@ApplicationContext context: Context): StudentDatabase {
        return getDatabase(context)
    }

}