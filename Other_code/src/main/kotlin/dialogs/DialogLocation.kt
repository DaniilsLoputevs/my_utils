package dialogs

var userMoney = 100;

class DialogLocation {
    val personDialog = Replica().apply {
        uuid = "iii"
        text = "Привет, думаю тебя заинтересует возможность заработка, что скажешь?"
        answer(nextReplica = { Replica() })
        answer(showPredicate = { false }, nextReplica = { Replica() })
    }


    val dialog = replica {
        text = "Привет, думаю тебя заинтересует возможность немного поднять деньжат, что скажешь?"
        answer {
            text = "Сейчас меня это не интересует."
            answer {
                text = "Сейчас меня это не интересует."
            }
        }
    }

    val firstBarmanDialog = replica {
        uuid = "first barman dialog"
        text = "Привет, Михей! Давно не виделись! Тебе как обычно или сразу о деньгах?"
        answer ({ userMoney > 100 }, {
            showPredicate = { userMoney > 100 }
            text = "На этот раз Камбэли Красный, пол стакана, а после и новеньком в округе поговрить можно."
            onPlay = { userMoney -= 100}
        })
        answer({ userMoney < 100}, {
            text = "К новостям, я на мели."
        })
    }
}

class Dialog {
    val startReplica = Replica()
}

public fun replica(replica: Replica.() -> Unit): Replica {
    val rsl = Replica()
    replica.invoke(rsl)
    return rsl
}


class Replica {
    var uuid = ""
    var text = ""
    val nextDialogVariants = LinkedHashMap<() -> Boolean, Replica>()
    var showPredicate : () -> Boolean = { true }
    var onPlay: () -> Unit = {}
    val nextReplicas = mutableListOf<Replica>()

    /**
     * @param showPredicate - показывать этот вариант ответа или нет. (Можно написать скрипт-условие), default = true
     */
    fun answer(
        showPredicate: () -> Boolean = { true },
        nextReplica: Replica.() -> Unit
    ) : Replica {
        val rsl = Replica()
        nextReplica.invoke(rsl)
//        nextDialogVariants[showPredicate] = rsl

        nextReplicas += rsl
        this.showPredicate = showPredicate
        return rsl
    }
}