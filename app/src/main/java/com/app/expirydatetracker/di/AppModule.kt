package com.app.expirydatetracker.di

import android.content.Context
import androidx.room.Room
import com.app.expirydatetracker.storage.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //Room DI
    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        Database::class.java,
        "expiring_items"
    ).fallbackToDestructiveMigration().build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: Database) = db.getDao() // The reason we can implement a Dao for the database
}