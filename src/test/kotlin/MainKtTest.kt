import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculation_outsideLimits() {
        //arrange
        val card = "Vk Pay"
        val previousAmount = 600001.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Максимальная сумма переводов по одной карте - 600000 рублей в месяц." +
                "\n Комиссия в лимитах не учитывается)", result)
    }

    @Test
    fun calculation_vkPayWithinLimits() {
        //arrange
        val card = "Vk Pay"
        val previousAmount = 10000.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
       assertEquals("Сумма с учетом комиссии: 1000.0", result)
    }

    @Test
    fun calculation_vkPayOutsideLimits_amount() {
        //arrange
        val card = "Vk Pay"
        val previousAmount = 10000.0
        val amount = 16000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Превышена максимальная сумма перевода за один раз (максимум 15000)", result)
    }

    @Test
    fun calculation_vkPayOutsideLimits_previousAmount() {
        //arrange
        val card = "Vk Pay"
        val previousAmount = 41000.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Превыщена максимальная сумма переводов за месяц (максимум 40000)", result)
    }

    @Test
    fun calculation_VisaWithinLimits_minCommission() {
        //arrange
        val card = "Visa"
        val previousAmount = 10000.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Сумма с учетом комиссии: 1035.0", result)
    }

    @Test
    fun calculation_VisaWithinLimits_75percentCommission() {
        //arrange
        val card = "Visa"
        val previousAmount = 10000.0
        val amount = 5000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Сумма с учетом комиссии: 5037.5", result)
    }

    @Test
    fun calculation_MirWithinLimits_minCommission() {
        //arrange
        val card = "Мир"
        val previousAmount = 10000.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Сумма с учетом комиссии: 1035.0", result)
    }

    @Test
    fun calculation_MirWithinLimits_75percentCommission() {
        //arrange
        val card = "Мир"
        val previousAmount = 10000.0
        val amount = 5000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Сумма с учетом комиссии: 5037.5", result)
    }

    @Test
    fun calculation_MastercardWithoutCommission() {
        //arrange
        val card = "Mastercard"
        val previousAmount = 10000.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Сумма с учетом комиссии: 1000.0", result)
    }

    @Test
    fun calculation_MastercardWithCommission() {
        //arrange
        val card = "Mastercard"
        val previousAmount = 76000.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Сумма с учетом комиссии: 1026.0", result)
    }

    @Test
    fun calculation_MaestroWithoutCommission() {
        //arrange
        val card = "Maestro"
        val previousAmount = 10000.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Сумма с учетом комиссии: 1000.0", result)
    }

    @Test
    fun calculation_MaestroWithCommission() {
        //arrange
        val card = "Maestro"
        val previousAmount = 76000.0
        val amount = 1000.0
        //act
        val result = calculation(card, previousAmount, amount)
        //assert
        assertEquals("Сумма с учетом комиссии: 1026.0", result)
    }
}