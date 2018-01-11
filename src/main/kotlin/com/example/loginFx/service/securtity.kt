package com.example.loginFx.service

import se.simbio.encryption.Encryption


fun encryption(key: String, salt: String) = Encryption.getDefault(key, salt, ByteArray(16))

fun String.encrypt(encryption: Encryption): String  = encryption.encryptOrNull(this)

fun String.decrypt(encryption: Encryption): String = encryption.decryptOrNull(this)