package com.medkissi.authmodule.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `test string contains no number returns false when check for it `(){
        val result = "NoNumber".containsNumber()
        assertThat(result).isFalse()
    }
    @Test
    fun `test string contains number returns true when check for it `(){
        val result = "Num5ber".containsNumber()
        assertThat(result).isTrue()
    }
    @Test
    fun `test string contains no special caracter returns false when check for it `(){
        val result = "Num5ber".containsSpecialChar()
        assertThat(result).isFalse()
    }
    @Test
    fun `test string contains special caracter returns true when check for it `(){
        val result = "NoNum@ber".containsSpecialChar()
        assertThat(result).isTrue()
    }
    @Test
    fun `test string contains an upprcase caracter returns false when check for it `(){
        val result = "num@ber".containsUpperCase()
        assertThat(result).isFalse()
    }
    @Test
    fun `test string contains an uppercase caractere returns false when check for it `(){
        val result = "NoNum@ber".containsUpperCase()
        assertThat(result).isTrue()
    }
}