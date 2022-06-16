package com.cryoggen.studentcontrol.di


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
    fun provideGetTasksUseCase(studentControlRepository: StudentControlRepository): GetTasksUseCase {
        return GetTasksUseCase(studentControlRepository)
    }

    @Provides
    fun provideInsertStudentsUseCase(studentControlRepository: StudentControlRepository): InsertStudentsUseCase {
        return InsertStudentsUseCase(studentControlRepository)
    }

    @Provides
    fun provideDeleteStudentsUseCase(studentControlRepository: StudentControlRepository): DeleteStudentUseCase {
        return DeleteStudentUseCase(studentControlRepository)
    }


    @Provides
    fun provideDeletePracticeUseCase(studentControlRepository: StudentControlRepository): DeletePracticeUseCase {
        return DeletePracticeUseCase(studentControlRepository)
    }

    @Provides
    fun provideDeleteTaskUseCase(studentControlRepository: StudentControlRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(studentControlRepository)
    }

    @Provides
    fun provideGetStudentsUseCase(studentControlRepository: StudentControlRepository): GetStudentsUseCase {
        return GetStudentsUseCase(studentControlRepository)
    }

    @Provides
    fun provideInsertPracticeUseCase(studentControlRepository: StudentControlRepository): InsertPracticeUseCase {
        return InsertPracticeUseCase(studentControlRepository)
    }

    @Provides
    fun provideInsertStudentControlUseCase(studentControlRepository: StudentControlRepository): InsertStudentControlUseCase {
        return InsertStudentControlUseCase(studentControlRepository)
    }

    @Provides
    fun provideInsertTasksUseCase(studentControlRepository: StudentControlRepository): InsertTasksUseCase {
        return InsertTasksUseCase(studentControlRepository)
    }

}