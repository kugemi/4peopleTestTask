package com.kugemi.exchangeratestracker.di

import android.app.Application
import androidx.room.Room
import com.kugemi.exchangeratestracker.infrastructure.database.FavoritesDatabase
import com.kugemi.exchangeratestracker.infrastructure.implementation.ServerClient
import com.kugemi.exchangeratestracker.infrastructure.interfaces.IServerClient
import com.kugemi.exchangeratestracker.infrastructure.repositories.implementation.FavoriteRatesRepository
import com.kugemi.exchangeratestracker.infrastructure.repositories.implementation.RemoteExchangeRatesRepository
import com.kugemi.exchangeratestracker.infrastructure.repositories.interfaces.IExchangeRatesRepository
import com.kugemi.exchangeratestracker.infrastructure.repositories.interfaces.IFavoriteRatesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providesServerClient(): IServerClient {
        return ServerClient()
    }

    @Provides
    fun provideExchangeRatesRepository(serverClient: IServerClient): IExchangeRatesRepository {
        return RemoteExchangeRatesRepository(serverClient)
    }

    @Provides
    fun providesRoom(application: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            application,
            FavoritesDatabase::class.java,
            "FavoritesDatabase"
        )
            .build()
    }

    @Provides
    fun provideFavoritesRepository(database: FavoritesDatabase): IFavoriteRatesRepository {
        return FavoriteRatesRepository(database)
    }
}