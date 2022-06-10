package com.cryoggen.studentcontrol.di


import androidx.lifecycle.ViewModelProvider
import com.cryoggen.domain.repository.StudentControlRepository
import com.cryoggen.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetPracticesUseCase(studentControlRepository: StudentControlRepository): GetPracticesUseCase {
        return GetPracticesUseCase(studentControlRepository)
    }

    @Provides
    fun provideGetStudentsUseCase(studentControlRepository: StudentControlRepository): GetStudentsUseCase {
        return GetStudentsUseCase(studentControlRepository)
    }


    @Provides
    fun provideGetTasksUseCase(studentControlRepository: StudentControlRepository): GetTasksUseCase {
        return GetTasksUseCase(studentControlRepository)
    }

    @Provides
    fun provideInsertStudentsUseCase(studentControlRepository: StudentControlRepository): InsertStudentsUseCase {
        return InsertStudentsUseCase(studentControlRepository)
    }

    @Provides
    fun provideDeleteStudentsUseCase(studentControlRepository: StudentControlRepository): DeleteStudentsUseCase {
        return DeleteStudentsUseCase(studentControlRepository)
    }

}