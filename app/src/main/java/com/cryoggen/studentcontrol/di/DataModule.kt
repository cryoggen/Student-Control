package com.cryoggen.studentcontrol.di

import com.cryoggen.data.repository.StudentControlRepositoryImpl
import com.cryoggen.data.source.local.*
import com.cryoggen.domain.repository.StudentControlRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun StudentsLocalDataSource(studentDatabase: StudentDatabase): StudentsLocalDataSource {
        return StudentsLocalDataSourceImpl(studentDatabase = studentDatabase)
    }

    @Provides
    @Singleton
    fun provideStudentControlRepository(
        StudentControlLocalDataSource: StudentsLocalDataSource
    ): StudentControlRepository {
        return StudentControlRepositoryImpl(
            StudentControlLocalDataSource = StudentControlLocalDataSource,
        )
    }

}