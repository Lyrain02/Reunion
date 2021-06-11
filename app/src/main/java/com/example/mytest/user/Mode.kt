package com.example.mytest.user

object Mode {
    const val LOCAL = 1
    const val REMOTE = 2

    val type = Mode.LOCAL //在这里设置本地存储/远程连接

    fun getModeType() :Int = when (Mode.type) {
        Mode.LOCAL -> {
            print("mode is local : ${Mode.LOCAL} ")
            Mode.LOCAL
        }
        Mode.REMOTE -> {
            print("mode is remote: ${Mode.REMOTE}")
            Mode.REMOTE
        }
        else -> throw IllegalArgumentException()
    }

    fun isLocal():Boolean = Mode.type == Mode.LOCAL

}