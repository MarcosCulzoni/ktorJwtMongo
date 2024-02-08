package com.marcos.security.hashing

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

class SHA256HashingService : HashingServiece {
    override fun generateSaltHash(value: String, saltLenght: Int): SaltedHash {
        val salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLenght)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$salt$value")
        return SaltedHash(
            hash = hash,
            salt = saltAsHex,
        )

    }

    override fun verify(value: String, saltedHash: SaltedHash): Boolean {
        TODO("Not yet implemented")
    }
}