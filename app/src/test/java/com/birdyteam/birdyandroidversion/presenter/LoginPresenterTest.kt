package com.birdyteam.birdyandroidversion.presenter

import org.junit.Test

import org.junit.Assert.*

/**
 * @author Ilia Ilmenskii created on 07.07.2019
 * @project Android-client
 */
class LoginPresenterTest {

    @Test
    fun checkCorrectness() {
        val presenter = LoginPresenter()
        val correctEmails = arrayOf(
            "gipermonk@bk.ru",
            "anna-aleksandrova@mail.ru",
            "supermonk@gmail.com",
            "yaplakal_@noreply.com"
        )
        val correctPasswords = arrayOf(
            "Multy_game1423",
            "1234321",
            "what_a_fuck_is_this",
            "no_pls_god_no"
        )

        val incorrectEmail = arrayOf(
            "asd",
            "@asd.ru",
            "das.sd",
            "asd@.sd",
            "da@.",
            "@"
        )

        val incorrectPassword = arrayOf(
            "1",
            "123",
            "```````````",
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd",
            "",
            "Multy_game14@23"
        )
        correctEmails.forEachIndexed { index, it ->
            assertTrue(presenter.checkCorrectness(it, correctPasswords[index]))
        }

        correctEmails.forEachIndexed { index, it ->
            assertFalse(presenter.checkCorrectness(it, incorrectPassword[index]))
        }

        incorrectEmail.forEachIndexed { index, it ->
            assertFalse(presenter.checkCorrectness(it, correctPasswords[index % correctPasswords.size]))
        }

        incorrectEmail.forEachIndexed { index, it ->
            assertFalse(presenter.checkCorrectness(it, incorrectPassword[index]))
        }

    }
}