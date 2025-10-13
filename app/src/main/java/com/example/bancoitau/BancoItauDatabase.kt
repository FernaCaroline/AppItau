package com.example.bancoitau

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bancoitau.com.example.bancoitau.TransacaoDao

@Database(
    entities = [
        TransacaoEntity::class,
        AplicacaoEntity::class  // <- inclua sua nova entidade
    ],
    version = 2               // <- suba a versão (se já usou 2, vá para 3, etc.)
)
abstract class BancoItauDatabase : RoomDatabase() {

    abstract fun transacaoDao(): TransacaoDao
    abstract fun aplicacaoDao(): AplicacaoDao

    companion object {
        @Volatile
        private var INSTANCE: BancoItauDatabase? = null

        fun getDatabase(context: Context): BancoItauDatabase {
            val cached = INSTANCE
            if (cached != null) return cached

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BancoItauDatabase::class.java,
                    "banco_itau_database"
                )
                    .fallbackToDestructiveMigration() // ok em DEV
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
