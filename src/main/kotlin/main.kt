import kotlin.math.roundToInt

fun calculation (card:String, previousAmount:Double, amount: Double): String {
    var totalString = ""
    if (previousAmount > 600000) totalString =("Максимальная сумма переводов по одной карте - 600000 рублей в месяц." +
            "\n Комиссия в лимитах не учитывается)")
    else {
        when (card) {
            "Vk Pay" -> totalString = vkTransaction(previousAmount, amount)
            "Visa" -> totalString = visaMirTransaction(amount)
            "Мир" -> totalString = visaMirTransaction(amount)
            "Mastercard" -> totalString = mastercardMaestroTransaction(previousAmount, amount)
            "Maestro" -> totalString = mastercardMaestroTransaction(previousAmount, amount)
        }
    }
    return totalString
}

fun vkTransaction(previousAmount: Double, amount: Double): String {
    return if(amount > 15000) ("Превышена максимальная сумма перевода за один раз (максимум 15000)")
    else if (previousAmount > 40000) ("Превыщена максимальная сумма переводов за месяц (максимум 40000)")
    else ("Сумма с учетом комиссии: $amount")
}

fun visaMirTransaction(amount: Double): String {
    var amountKop = amount * 100
    val commissionPercent = amountKop * 0.0075
    val commission = 3500
    if (commissionPercent >= commission){
        amountKop = (amountKop + commissionPercent).roundToInt() / 100.0
        return("Сумма с учетом комиссии: $amountKop")
    }
    else{
        amountKop = (amountKop + commission) / 100
        return("Сумма с учетом комиссии: $amountKop")
    }
}

fun mastercardMaestroTransaction(previousAmount: Double,amount: Double): String {
    var amountKop = amount * 100
    if (previousAmount > 75000) amountKop += amountKop * 0.006 + 2000
    amountKop = amountKop.roundToInt() / 100.0
    return("Сумма с учетом комиссии: $amountKop")
}
fun main(){
    val amount = 1000.0
    val card = "Mastercard"
    val previousAmount = 76000.0
    println(calculation(card,previousAmount,amount))
}