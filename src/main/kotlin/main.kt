import kotlin.math.roundToInt

fun calculation (card:String, previousAmount:Double, amount: Double){
    if (previousAmount > 600000) println("Максимальная сумма переводов по одной карте - 600000 рублей в месяц." +
            "\n Комиссия в лимитах не учитывается)")
    else {
        when (card) {
            "Vk Pay" -> vkTransaction(previousAmount, amount)
            "Visa" -> visaMirTransaction(amount)
            "Мир" -> visaMirTransaction(amount)
            "Mastercard" -> mastercardMaestroTransaction(previousAmount, amount)
            "Maestro" -> mastercardMaestroTransaction(previousAmount, amount)
        }
    }
}

fun vkTransaction(previousAmount: Double, amount: Double){
    if(amount > 15000) println("Превышена максимальная сумма перевода за один раз (максимум 15000)")
    else if (previousAmount > 40000) println("Превыщена максимальная сумма переводов за месяц (максимум 40000)")
    else println("Сумма учетом комиссии: $amount")
}

fun visaMirTransaction(amount: Double){
    var amountKop = amount * 100
    val commissionPercent = amountKop * 0.0075
    val commission = 3500
    if (commissionPercent >= commission){
        amountKop = (amountKop + commissionPercent).roundToInt() / 100.0
        println("Сумма учетом комиссии: $amountKop")
    }
    else{
        amountKop = (amountKop + commission) / 100
        println("Сумма учетом комиссии: $amountKop")
    }
}

fun mastercardMaestroTransaction(previousAmount: Double,amount: Double){
    var amountKop = amount * 100
    if (previousAmount > 75000) amountKop += amountKop * 0.006 + 2000
    amountKop = amountKop.roundToInt() / 100.0
    println("Сумма учетом комиссии: $amountKop")
}
fun main(){
    val amount = 1000.0
    val card = "Mastercard"
    val previousAmount = 76000.0
    calculation(card, previousAmount, amount)
}