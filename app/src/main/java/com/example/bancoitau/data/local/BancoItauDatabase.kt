package com.example.bancoitau.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        TransacaoEntity::class,
        AplicacaoEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class BancoItauDatabase : RoomDatabase() {

    abstract fun transacaoDao(): TransacaoDao
    abstract fun aplicacaoDao(): AplicacaoDao

    companion object {
        @Volatile
        private var INSTANCE: BancoItauDatabase? = null

        fun getDatabase(context: Context): BancoItauDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BancoItauDatabase::class.java,
                    "banco_itau_database"
                )
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
