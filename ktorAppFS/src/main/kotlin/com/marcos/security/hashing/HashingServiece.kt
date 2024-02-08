package com.marcos.security.hashing

interface HashingServiece {
    fun generateSaltHash(value: String, saltLenght: Int = 32):SaltedHash

    fun verify(value:String,saltedHash:SaltedHash):Boolean
}