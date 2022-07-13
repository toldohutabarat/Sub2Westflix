package com.example.core.di

import androidx.room.Room
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.WestflixDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.repository.RepositoryMovie
import com.example.core.data.source.repository.RepositoryTvShow
import com.example.core.domain.repository.IMovieRepository
import com.example.core.domain.repository.ITvShowRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.Credentials
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        RepositoryMovie(
            get(),
            get(),
            get()
        )
    }
    single<ITvShowRepository> {
        RepositoryTvShow(
            get(),
            get(),
            get()
        )
    }

}

val databaseModule = module {
    factory { get<WestflixDatabase>().westflixDao() }
    single {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes("example".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            WestflixDatabase::class.java,
            "Westflix.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "developers.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/J79FIln3PZG4Z5oVgdKIAn33RfgUitUFkSCTglRiehc=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }

}