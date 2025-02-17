package com.aya.digital.core.security

import java.io.InputStream
import java.io.OutputStream


interface Crypto {
    fun encrypt(rawBytes: ByteArray, outputStream: OutputStream)
    fun decrypt(inputStream: InputStream): ByteArray
}


internal class CryptoImpl(private val cipherProvider: CipherProvider) : Crypto {

    override fun encrypt(rawBytes: ByteArray, outputStream: OutputStream) {
        val cipher = cipherProvider.encryptCipher
        val encryptedBytes = cipher.doFinal(rawBytes)
        with(outputStream) {
            write(cipher.iv.size)
            write(cipher.iv)
            write(encryptedBytes)
        }
    }

    override fun decrypt(inputStream: InputStream): ByteArray {
        val ivSize = inputStream.read()
        val iv = ByteArray(ivSize)
        inputStream.read(iv)
        val encryptedData = inputStream.readBytes()
        val cipher = cipherProvider.decryptCipher(iv)
        return cipher.doFinal(encryptedData)
    }
}